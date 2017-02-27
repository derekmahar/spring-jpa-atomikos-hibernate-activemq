package me.derekmahar.example.spring.messenger;

import me.derekmahar.example.spring.model.Person;
import java.util.Enumeration;
import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsOperations;

public class PersonMessenger {

	private final JmsOperations jmsOperations;

	public PersonMessenger(JmsOperations jmsOperations) {
		this.jmsOperations = jmsOperations;
	}

	public int getOutgoingPersonCount() {
		return this.jmsOperations.browse(new BrowserCallback<Integer>() {
			public Integer doInJms(Session session, QueueBrowser browser) throws JMSException {
				int count = 0;
				final Enumeration queueEnumeration = browser.getEnumeration();
				while (queueEnumeration.hasMoreElements()) {
					count++;
					queueEnumeration.nextElement();
				}
				return count;
			}
		});
	}

	public Person receivePerson() {
		return (Person) this.jmsOperations.receiveAndConvert();
	}

	public void sendPerson(final Person person) {
		this.jmsOperations.convertAndSend(person);
	}
}
