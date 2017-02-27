package me.derekmahar.example.spring.transaction;

import java.util.UUID;
import me.derekmahar.example.spring.configuration.GlobalTransactionManagerConfiguration;
import me.derekmahar.example.spring.configuration.ModelConfiguration;
import me.derekmahar.example.spring.messenger.PersonMessenger;
import me.derekmahar.example.spring.model.Person;
import me.derekmahar.example.spring.model.PersonFactory;
import me.derekmahar.example.spring.repository.PersonRepository;
import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GlobalTransactionManagerConfiguration.class, ModelConfiguration.class})
public class PersonRepositoryAndMessengerTest {

	@Autowired
	private BrokerService broker;

	@Autowired
	private PersonFactory personFactory;

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	private PersonMessenger personMessenger;
	
	@Test
	public void storeAndSendPersonThenRollback() {
		final Person savedPerson = this.transactionTemplate.execute(new TransactionCallback<Person>() {
			public Person doInTransaction(TransactionStatus ts) {
				final Person person = personFactory.createPerson(UUID.randomUUID(), "Luke", "Skywalker");
				final Person savedPerson = personRepository.save(person);
				personMessenger.sendPerson(person);
				ts.setRollbackOnly();
				return savedPerson;
			}
		});

		assertFalse(personRepository.exists(savedPerson.getId()));
		assertEquals(0, personMessenger.getOutgoingPersonCount());
	}
	
	@Before
	public void startBroker() throws Exception {
		broker.start();
	}

	@After
	public void stopBroker() throws Exception {
		broker.stop();
	}

}
