package me.derekmahar.example.spring.repository;

import me.derekmahar.example.spring.model.Person;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
