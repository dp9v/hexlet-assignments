package exercise.controller;

import exercise.dto.PersonDto;
import exercise.model.Person;
import exercise.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    // Автоматически заполняем значение поля
    private final PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping
    public void createPerson(@RequestBody PersonDto personDto) {
        personRepository.save(new Person(personDto));
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable long id) {
        personRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void deletePerson(@PathVariable long id, @RequestBody PersonDto personDto) {
        var person = new Person(personDto);
        person.setId(id);
        personRepository.save(person);
    }
    // END
}
