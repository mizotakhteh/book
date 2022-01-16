package ml.mizotakhteh.book.resource.dto;

import javax.validation.constraints.NotNull;

public record BookDto(

        @NotNull
        String id,

        @NotNull
        String name,

        @NotNull
        Long isbn,

        String pictureAddress

) {
}