package ml.mizotakhteh.book.repository;

import ml.mizotakhteh.book.domain.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
