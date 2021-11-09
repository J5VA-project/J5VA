package com.J5VA.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="foodcategory")
public class foodCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodcategory_id;
	@NotBlank(message = "food Category Name not null!")
	private String foodcategory_name;
	@NotBlank(message = "description not null!")
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "foodCategory")
	List<foodDetail> foodDetails;

}