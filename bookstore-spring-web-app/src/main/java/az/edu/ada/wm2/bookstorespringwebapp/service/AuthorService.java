package az.edu.ada.wm2.bookstorespringwebapp.service;

import az.edu.ada.wm2.bookstorespringwebapp.model.Author;
import az.edu.ada.wm2.bookstorespringwebapp.model.Book;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {

    Page<Author> list(Integer pageNo);

    Author save(Author author);

    Author getById(Long id);

    List<Author> getAuthorByNamesAnd(String firstName, String lastName);

    List<Author> getAuthorByNamesOr(String firstName, String lastName);

    List<Book> getBooksByAuthorId(Long id);

    List<Book> getBooksByAuthorIdNot(Long id);

    void deleteById(Long id);
    void updateAuthor(Author author);

}
