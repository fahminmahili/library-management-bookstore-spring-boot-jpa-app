package az.edu.ada.wm2.bookstorespringwebapp.repository;
import az.edu.ada.wm2.bookstorespringwebapp.model.Author;
import az.edu.ada.wm2.bookstorespringwebapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {



    Iterable<Book> findByAuthorsId(Long id);

    @Query("select c from Book c where c not in " +
            "(select c from Book c left join c.authors ath where ath.id = :id)")
    Iterable<Book> findByAuthorsIdNot(Long id);

    @Query("select c from Book c where lower(c.description) like %:keyword%")
    Iterable<Book> getAllDetectiveBooksUsingJPAQuery(@Param("keyword") String keyword);

    @Query(value = "select * from books where description like '%Detective%'", nativeQuery = true)
    Iterable<Book> getAllDetectiveBooksUsingNativeQuery();

}