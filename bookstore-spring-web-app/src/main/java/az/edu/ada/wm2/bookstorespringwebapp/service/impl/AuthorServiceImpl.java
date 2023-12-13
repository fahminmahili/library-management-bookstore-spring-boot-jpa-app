package az.edu.ada.wm2.bookstorespringwebapp.service.impl;
import az.edu.ada.wm2.bookstorespringwebapp.model.Book;
import az.edu.ada.wm2.bookstorespringwebapp.model.Author;
import az.edu.ada.wm2.bookstorespringwebapp.repository.BookRepository;
import az.edu.ada.wm2.bookstorespringwebapp.repository.AuthorRepository;
import az.edu.ada.wm2.bookstorespringwebapp.service.AuthorService;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepo;

    private BookRepository bookRepo;

    public AuthorServiceImpl(AuthorRepository authorRepo, BookRepository bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }



    @Override
    public Page<Author> list(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return authorRepo.findAll(pageable);
    }



    @Override
    public Author save(Author author) {
        return authorRepo.save(author);
    }



    @Override
    public Author getById(Long id) {
        return authorRepo.findById(id).orElse(null);
    }


    public List<Author> getAuthorByNamesAnd(String firstName, String lastName) {
        return (List<Author>) authorRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Author> getAuthorByNamesOr(String firstName, String lastName) {
        return (List<Author>) authorRepo.findByFirstNameOrLastName(firstName, lastName);
    }



    @Override
    public List<Book> getBooksByAuthorId(Long id) {
        return (List<Book>) bookRepo.findByAuthorsId(id);
    }

    @Override
    public List<Book> getBooksByAuthorIdNot(Long id) {
        return (List<Book>) bookRepo.findByAuthorsIdNot(id);
    }

    @Override
    public void deleteById(Long id) {
        authorRepo.deleteById(id);
    }

    @Override
    public void updateAuthor(Author author) {
    }
}
