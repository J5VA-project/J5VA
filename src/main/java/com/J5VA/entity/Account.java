package com.J5VA.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

	@Id
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String address;
	private String phone;
	private Date hire_date;
	private double salary;
	private Boolean gender;
	private Date birthdate;
	private String image;

	@JsonIgnore
	@OneToMany(mappedBy = "authorize", fetch = FetchType.EAGER)
	List<Authorized> authorities;

	@JsonIgnore
	@OneToMany(mappedBy = "favorite_acc")
	List<Favorite> favorites;

	@JsonIgnore
	@OneToMany(mappedBy = "order_acc")
	List<Orders> orders;
}
