package me.derekmahar.example.spring.model;

import java.util.UUID;

public class PersonFactory {

	public Person createPerson(UUID id, String firstName, String lastName) {
		return new Person(firstName, id, lastName);
	}

}
