package com.J5VA.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.J5VA.entity.food;

public interface foodService {

	int count();
	
	void delete(food entity);

	food findById(Integer id);

	food save(food entity);

	Page<food> searchFood(Integer pageNumber, String key);

//	Page<food> listAll(int pageNumber);

	List<food> findAll();

	food create(food product);

	food update(food product);

	void delete(Integer id);
	

}
