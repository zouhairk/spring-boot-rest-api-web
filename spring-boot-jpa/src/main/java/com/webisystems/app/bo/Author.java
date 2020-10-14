package com.webisystems.app.bo;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Basic
	@Size(min = 3, max = 40)
	private String name;

	@Basic
	@Past
	private LocalDate birthday;

	public Author(@Size(min = 3, max = 40) String name, @Past LocalDate birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
	}
	
	

}
