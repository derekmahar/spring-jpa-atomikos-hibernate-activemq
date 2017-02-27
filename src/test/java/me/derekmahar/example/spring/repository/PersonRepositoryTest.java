package me.derekmahar.example.spring.repository;

import me.derekmahar.example.spring.configuration.LocalTransactionManagerConfiguration;
import me.derekmahar.example.spring.configuration.ModelConfiguration;
import me.derekmahar.example.spring.model.Person;
import me.derekmahar.example.spring.model.PersonFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LocalTransactionManagerConfiguration.class, ModelConfiguration.class})
@Transactional
public class PersonRepositoryTest {

	@Autowired
	private PersonFactory personFactory;

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void savePerson() {
		final Person savedPerson = personRepository.save(personFactory.createPerson(null, "Derek", "Mahar"));
		assertTrue(personRepository.exists(savedPerson.getId()));
	}
	
}
