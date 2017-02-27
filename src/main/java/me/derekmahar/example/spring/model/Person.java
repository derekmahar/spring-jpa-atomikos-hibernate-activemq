package me.derekmahar.example.spring.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "person", schema = "human")
public class Person implements Serializable {

	private static final long serialVersionUID = 7961317590751458807L;

	private String firstName;
		
	@Id
	@GeneratedValue(generator = "uuid-gen")
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	private UUID id;
	
	private String lastName;

	public Person() {
	}

	public Person(String firstName, UUID id, String lastName) {
		this.firstName = firstName;
		this.id = id;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public UUID getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
