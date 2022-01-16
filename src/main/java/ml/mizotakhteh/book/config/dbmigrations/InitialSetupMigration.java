package ml.mizotakhteh.book.config.dbmigrations;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ml.mizotakhteh.book.domain.Book;
import ml.mizotakhteh.book.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog(order = "001")
public class InitialSetupMigration {
    @ChangeSet(order = "001", id = "seedDataBase", author = "Hasan")
    public void seedDataBase(BookRepository bookRepository) {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book(null,
                "Java 17 for Absolute Beginners - Learn the Fundamentals of Java Programming - Apress (2022)",
                978_1_4842_7080_6L,
                "https://covers.zlibcdn2.com/covers299/books/6d/ac/41/6dac4191cde5101efb9d9774347698c5.jpg"));
        books.add(new Book(null,
                "Java: The Complete Reference, 12th Edition (2021)",
                978_1_2604_6342_2L,
                "https://covers.zlibcdn2.com/covers/books/84/3e/bf/843ebfa6fc316f0002244b2b765589de.jpg"));
        books.add(new Book(null,
                "More Java 17 An In-Depth Exploration of the Java Language and Its Features (2021)",
                978_1_4842_7135_3L,
                "https://covers.zlibcdn2.com/covers299/books/ab/fe/a8/abfea8109fabe4f5960e8960c2175f7a.jpg"));
        books.add(new Book(null,
                "Microservices with Spring Boot and Spring Cloud - Build resilient and scalable microservices using Spring Cloud, Istio, and Kubernetes-Packt (2021)",
                978_1_4842_7135_3L,
                "https://covers.zlibcdn2.com/covers299/books/2c/1c/ea/2c1ceac5cf6e2070c9823c97265a3afc.jpg"));
        bookRepository.saveAll(books).blockLast();
    }
}
