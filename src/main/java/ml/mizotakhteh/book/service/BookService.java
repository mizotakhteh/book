package ml.mizotakhteh.book.service;

import ml.mizotakhteh.book.domain.Book;
import ml.mizotakhteh.book.event.model.Event;
import ml.mizotakhteh.book.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final StreamBridge streamBridge;

    public BookService(BookRepository bookRepository, StreamBridge streamBridge) {
        this.bookRepository = bookRepository;
        this.streamBridge = streamBridge;
    }

    public Flux<Book> findAll() {
        log.debug("Request to get all Books");
        return bookRepository.findAll();
    }

    public Mono<Book> save(Book book) {
        log.debug("Request to save Book : {}", book);

        Message message = MessageBuilder
                .withPayload(new Event(Event.Type.CREATE, book.id(), book))
                .build();
        return bookRepository.save(book)
                .doOnNext(createdBook ->
                        streamBridge.send("book", MessageBuilder
                                .withPayload(new Event(Event.Type.CREATE, createdBook.id(), createdBook))
                                .build()));
    }
}
