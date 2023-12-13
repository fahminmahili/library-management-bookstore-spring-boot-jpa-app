package az.edu.ada.wm2.bookstorespringwebapp.service;

import az.edu.ada.wm2.bookstorespringwebapp.model.Book;

import java.util.List;

public interface BookService {

    List<Book> list();

    Book getById(Long id);

    Book save(Book book);

    void deleteById(Long id);

    void updateBook(Book book);

    List<Book> getAllDetectiveBooks(String keyword);

}
