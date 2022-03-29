package org.corodiak.capstonelibrary.entity;

import javax.persistence.*;

@Entity
@Table
public class TestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(
		length = 60,
		nullable = false
	)
	private String name;

}
