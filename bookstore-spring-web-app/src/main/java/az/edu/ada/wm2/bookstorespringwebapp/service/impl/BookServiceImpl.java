package az.edu.ada.wm2.bookstorespringwebapp.service.impl;
import az.edu.ada.wm2.bookstorespringwebapp.model.Book;
import az.edu.ada.wm2.bookstorespringwebapp.repository.BookRepository;
import az.edu.ada.wm2.bookstorespringwebapp.service.BookService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepo;

    public BookServiceImpl (BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }


    @Override
    public List<Book> list() {
        return (List<Book>) bookRepo.findAll();
    }


    @Override
    public Book getById(Long id) {
        return bookRepo.findById(id).orElse(null);
    }


    @Override
    public Book save(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public List<Book> getAllDetectiveBooks(String keyword) {
        return (List<Book>) bookRepo.getAllDetectiveBooksUsingJPAQuery(keyword);
    }
}
