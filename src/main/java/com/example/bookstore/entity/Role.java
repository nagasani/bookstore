package com.example.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public ERole getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(ERole name) {
		this.name = name;
	}

}
