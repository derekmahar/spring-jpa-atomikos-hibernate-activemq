package me.derekmahar.example.spring.messenger;

import me.derekmahar.example.spring.configuration.LocalTransactionManagerConfiguration;
import me.derekmahar.example.spring.configuration.ModelConfiguration;
import me.derekmahar.example.spring.model.Person;
import me.derekmahar.example.spring.model.PersonFactory;
import me.derekmahar.example.spring.service.PersonService;
import java.util.UUID;
import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LocalTransactionManagerConfiguration.class, ModelConfiguration.class})
public class PersonMessengerTest {

	@Autowired
	private BrokerService broker;

	@Autowired
	private PersonFactory personFactory;

	@Autowired
	private PersonService personService;

	private void checkPartnerIds(Person[] partners, UUID[] ids) {
		assertEquals(partners[0].getId(), ids[0]);
		assertEquals(partners[1].getId(), ids[1]);
	}

	private Person[] createPartners() {
		final Person[] partners = {personFactory.createPerson(UUID.randomUUID(), "Luke", "Skywalker"), personFactory.
			createPerson(UUID.randomUUID(), "Han", "Solo")};
		return partners;
	}

	@Test
	public void sendAndReceivePartners() {
		final Person[] partners = createPartners();
		personService.sendPeople(partners);
		UUID[] ids = personService.receivePeople(2);
		checkPartnerIds(partners, ids);
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
