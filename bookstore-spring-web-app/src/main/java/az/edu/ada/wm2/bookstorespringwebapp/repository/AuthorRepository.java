package az.edu.ada.wm2.bookstorespringwebapp.repository;
import az.edu.ada.wm2.bookstorespringwebapp.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Page<Author> findAll (Pageable pageable);
    Iterable<Author> findByFirstNameAndLastName(String firstName, String lastName);

    Iterable<Author> findByFirstNameOrLastName(String firstName, String lastName);

}