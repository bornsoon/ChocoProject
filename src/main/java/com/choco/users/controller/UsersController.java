package com.choco.users.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choco.users.model.Users;
import com.choco.users.service.UsersService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsersController {

	@Autowired
	UsersService usersService;

	@PostMapping("/findid")
	public String findId(@RequestParam("usersName") String usersName,
			@RequestParam("usersNickname") String usersNickname,
			@RequestParam("usersBirthdate") String usersBirthdate, Model model, RedirectAttributes redirectAttrs) {
		
		String findId = usersService.findId(usersName, usersNickname, usersBirthdate);
		if (findId != null) {
			model.addAttribute("findId", findId);
			return "thymeleaf/choco/users/findid2";
		} else {
			redirectAttrs.addFlashAttribute("message", "입력하신 정보로 아이디를 찾을 수 없습니다.");
			return "redirect:/findid";
		}	
	}
	
	@PostMapping("/findpwd")
	public String findPwd(@RequestParam("usersId") String usersId,
			@RequestParam("usersName") String usersName,
			@RequestParam("usersBirthdate") String usersBirthdate, Model model, RedirectAttributes redirectAttrs) {
		
		String findPwd = usersService.findPwd(usersId, usersName, usersBirthdate);
		if (findPwd != null) {
			model.addAttribute("findPwd", findPwd);
			return "thymeleaf/choco/users/findpwd2";
		} else {
			redirectAttrs.addFlashAttribute("message", "입력하신 정보로 비밀번호를 찾을 수 없습니다.");
			return "redirect:/findpwd";
		}	
	}
	

	@PostMapping("/main_login")
	public String loginUsers(@RequestParam("usersId") String usersId, @RequestParam("usersPwd") String usersPwd, 
			               HttpSession session, RedirectAttributes redirectAttrs) {	
		
		Users users = usersService.loginUsers(usersId, usersPwd);
				
		if (users != null) {
			session.setMaxInactiveInterval(600); //10분
			session.setAttribute("userid", usersId);
			
			// return "redirect:/main_login";
			return "thymeleaf/choco/users/findpwd2";
		}
		else {
			session.invalidate();
			redirectAttrs.addFlashAttribute("message", "아이디 또는 패스워드가 잘못되었습니다.");
			
			return "thymeleaf/choco/users/findpwd2";
			// return "redirect:/main_login";
		}
		
	}
	
	/*
	@GetMapping("/check-duplicate")
    public @ResponseBody <Map<String, Boolean>> checkId(@RequestParam("usersId") String usersId) {
        boolean exists = usersService.checkId(usersId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
    */
	
	
	
	@PostMapping("/signup")
	public String insertUsers(Users users, RedirectAttributes redirectAttributes) {
		
		try {
			usersService.insertUsers(users);
			redirectAttributes.addFlashAttribute("message", 
					users.getUsersName()+"님 회원가입이 완료되었습니다.");
			
		}
		catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/main_login";
	}
	
	
	
	
	
	@GetMapping("/users")
	public String users(Model model) {
		
		model.addAttribute("count", usersService.getUsersCount());
		
		return "thymeleaf/choco/users/users";
	}
	
	@GetMapping("/main_login")
	public String users2(Model model) {
		
		model.addAttribute("count", usersService.getUsersCount());
		
		return "thymeleaf/choco/users/main_login";
	}
	
	@GetMapping("/findid")
	public String users3(Model model) {
		
		model.addAttribute("count", usersService.getUsersCount());
		
		return "thymeleaf/choco/users/findid";
	}
	
	@GetMapping("/findpwd")
	public String users4(Model model) {
		
		model.addAttribute("count", usersService.getUsersCount());
		
		return "thymeleaf/choco/users/findpwd";
	}
	
	@GetMapping("/signup")
	public String users5(Model model) {
		
		model.addAttribute("count", usersService.getUsersCount());
		
		return "thymeleaf/choco/users/signup";
	}
	
	
	@GetMapping("/users/{usersId}")
	public String usersName(@PathVariable("usersId") String usersId, Model model) {
		
		model.addAttribute("name", usersService.getUsersName(usersId));
		
		return "thymeleaf/choco/users/users_detail";
	}
	
	
}
