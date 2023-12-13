package az.edu.ada.wm2.bookstorespringwebapp.model;
import lombok.*;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;
import org.aspectj.bridge.IMessage;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "AUTHORS")


public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String gender;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();



    public Author(String firstName, String lastName, String email,  String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Author: " + firstName + ", " + lastName;
    }

}
