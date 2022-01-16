package ml.mizotakhteh.book.resource.dto;

import javax.validation.constraints.NotNull;

public record RegisterBookDto(

        @NotNull
        String name,

        @NotNull
        Long isbn,

        String pictureAddress

) {
}