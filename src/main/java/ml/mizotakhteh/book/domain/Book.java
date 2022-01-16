package ml.mizotakhteh.book.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;

@Document
public record Book(

        @MongoId
        String id,

        @NotNull
        String name,

        @NotNull
        @Indexed(unique = true) Long isbn,
        String pictureAddress

) {
}
