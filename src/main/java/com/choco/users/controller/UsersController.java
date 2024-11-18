package com.choco.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.choco.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsersController {

	@Autowired
	UsersService usersService;
	
	@GetMapping("/users")
	public String users(Model model) {
		
		model.addAttribute("count", usersService.getUsersCount());
		
		return "thymeleaf/choco/users/users";
	}
	
	@GetMapping("/users/{usersId}")
	public String usersName(@PathVariable("usersId") String usersId, Model model) {
		
		model.addAttribute("name", usersService.getUsersName(usersId));
		
		return "thymeleaf/choco/users/users_detail";
	}
}
