package com.J5VA.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	

@RequestMapping("/home/login")
	public String runControll(Model model) {
		return "/home/login";
	}
}
