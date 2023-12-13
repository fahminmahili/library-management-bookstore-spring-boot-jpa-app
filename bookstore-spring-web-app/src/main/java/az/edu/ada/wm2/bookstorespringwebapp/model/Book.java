package az.edu.ada.wm2.bookstorespringwebapp.model;
import java.time.LocalDate;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="BOOKS")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String isbn;

    private String description;
//    private LocalDate publicationDate;

    private String category;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();


    public Book(String title, String isbn, String description,  String category, Double price) {
        this.title = title;
        this.isbn = isbn;
        this.description = description;
//        this.publicationDate = publicationDate;
        this.category = category;
    }

//    public Book(String title, Double price) {
//        this(title, title, null, null, null, price);
//    }


    @Override
    public String toString() {
        return "Book: " + this.title + ": " + this.description;
    }
}
