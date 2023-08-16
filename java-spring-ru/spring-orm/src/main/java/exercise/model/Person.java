package exercise.model;

import exercise.dto.PersonDto;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Entity
@NoArgsConstructor
public class Person {

    public Person(PersonDto person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // BEGIN
    private String firstName;

    private String lastName;
    // END
}
