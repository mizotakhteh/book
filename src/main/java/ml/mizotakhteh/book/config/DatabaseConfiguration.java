package ml.mizotakhteh.book.config;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories("ml.mizotakhteh.book.repository")
@EnableMongock
public class DatabaseConfiguration {
}