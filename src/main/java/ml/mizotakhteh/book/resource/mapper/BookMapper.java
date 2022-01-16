package ml.mizotakhteh.book.resource.mapper;

import ml.mizotakhteh.book.domain.Book;
import ml.mizotakhteh.book.resource.dto.BookDto;
import ml.mizotakhteh.book.resource.dto.RegisterBookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book registerDtoToEntity(RegisterBookDto bookDto);
    BookDto entityToDto(Book book);
}
