package com.J5VA.controller.user;

import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.J5VA.entity.Account;
import com.J5VA.service.MailerService;
import com.J5VA.service.ParamService;
import com.J5VA.service.AccountService;

@Controller
public class RegisterController {
	@Autowired
	AccountService accountService;
	@Autowired
	AccountService customerService;
	@Autowired
	ParamService paramService;
	@Autowired
	MailerService mailer;
	@Autowired
	HttpServletRequest request;

	// dang ky tai khoan khach hang
	@RequestMapping("/home/register")
	public String registerForm(Model model) {
		Account custo = new Account();
		model.addAttribute("custo", custo);
		return "user/body/register";
	}

	@PostMapping("/register/validate")
	public String registerValid(Model model, @Valid @ModelAttribute("custo") @RequestBody Account custo,
			@RequestParam("fullname") String fullname, Errors errors) throws MessagingException {
		String username = paramService.getString("username", "");
		String password = paramService.getString("password", "");
		String email = paramService.getString("email", "");

		if (accountService.checkByUsername(username) == true) {
			model.addAttribute("error", "Username existed!");
		} else {
			if (accountService.checkByEmail(email) == true) {
				model.addAttribute("error", "Email existed!");
			} else {
				model.addAttribute("custo", custo);
				custo.setHire_date(new Date());
				customerService.create(custo);
				mailer.send(email, "Account Register",
						"Dear. " + fullname
								+ ", Wellcome to J5VA Restaurant, hope you like my website, and then my username: "
								+ username + ", password: " + password);
				model.addAttribute("message", "Register success!");
			}
		}
		return "forward:/home/register";
	}
}
