package com.choco.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PostController {

	
	@GetMapping(value = {"/post", "/post/"})
	public String post(Model model) {
		
		model.addAttribute("serverTime",  "서버시간");
		
		return "thymeleaf/choco/post/post_detail";
	}

}