package me.derekmahar.example.spring.service;

import me.derekmahar.example.spring.messenger.PersonMessenger;
import me.derekmahar.example.spring.model.Person;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

public class PersonService {

	private final PersonMessenger personMessenger;

	public PersonService(PersonMessenger personMessenger) {
		this.personMessenger = personMessenger;
	}

	@Transactional
	public UUID[] receivePeople(int numberOfPeople) {
		final UUID[] ids = new UUID[numberOfPeople];

		for (int count = 0; count < numberOfPeople; count++) {
			final Person person = personMessenger.receivePerson();
			ids[count] = person.getId();
		}

		return ids;
	}

	@Transactional
	public void sendPeople(Person[] people) {
		for (final Person person : people) {
			personMessenger.sendPerson(person);
		}
	}

	@Transactional
	public UUID[] sendAndReceivePeople(Person[] people) {
		this.sendPeople(people);
		return this.receivePeople(2);
	}
}
