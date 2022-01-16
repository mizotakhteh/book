package ml.mizotakhteh.book.resource;

import ml.mizotakhteh.book.resource.dto.BookDto;
import ml.mizotakhteh.book.resource.dto.RegisterBookDto;
import ml.mizotakhteh.book.resource.mapper.BookMapper;
import ml.mizotakhteh.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/books")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private final BookService bookService;

    private final BookMapper bookMapper;

    public BookResource(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    /**
     * {@code GET /books} : get all the books.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of books in body.
     */
    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<BookDto> getAllBooksAsStream() {
        log.debug("REST request to get all Notifications as a stream");
        // TODO: pagination
        return bookService.findAll().map(book -> bookMapper.entityToDto(book));
    }

    @PostMapping
    public Mono<ResponseEntity<BookDto>> createBook(@Valid @RequestBody RegisterBookDto registerBookDto) {
        log.debug("REST request to save Book : {}", registerBookDto);
        return bookService
                .save(bookMapper.registerDtoToEntity(registerBookDto))
                .map(book -> ResponseEntity
                        .created(URI.create("/books/" + book.id()))
                        .body(bookMapper.entityToDto(book))
                );
    }
}
