package com.J5VA.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.J5VA.entity.Account;
import com.J5VA.entity.Favorite;
import com.J5VA.service.AccountService;
import com.J5VA.service.FavoriteService;

@RestController
@RequestMapping("/rest/favorite")
public class FavoriteRestController {
	@Autowired
	FavoriteService favoriteService;
	@Autowired
	AccountService accountService;
	@Autowired
	HttpServletRequest request;

	@GetMapping("")
	public List<Favorite> getAll() {
		return favoriteService.findAll();
	}

	@GetMapping("/{id}")
	public List<Favorite> getAllById(@PathVariable List<Integer> id) {
		return favoriteService.findAllById(id);
	}

	@PostMapping
	public Favorite create(@RequestBody Favorite product) {
		return favoriteService.create(product);
	}

	@PutMapping("/{id}")
	public Favorite update(@PathVariable("id") Integer id, @RequestBody Favorite product) {
		return favoriteService.update(product);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		favoriteService.delete(id);
	}

	@GetMapping("/list")
	public List<Favorite> list(Model model) {
		Account account = accountService.findByUsername(request.getRemoteUser());
		System.out.println("Account:"+account);
		List<Favorite> favorite = favoriteService.findAllByAccount(account);
		model.addAttribute("favorite", favorite);

		return favorite;
	}
}
