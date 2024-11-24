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

import com.choco.pet.Service.PetService;
import com.choco.pet.model.Pet;
import com.choco.users.dao.UsersRepository;
import com.choco.users.model.Users;
import com.choco.users.service.UsersService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsersController {

	@Autowired
	PetService petService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	UsersRepository usersRepository;
	

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
	

	@GetMapping("/main_login")
	public String loginUsers(Model model) {	
		return "thymeleaf/choco/users/main_login";
	}
	
	@PostMapping("/main_login")
	public String loginUsers(@RequestParam("usersId") String usersId, @RequestParam("usersPwd") String usersPwd, 
			               HttpSession session, RedirectAttributes redirectAttrs) {	
		log.info("main_login");
		Users users = usersService.loginUsers(usersId, usersPwd);
				
		if (users != null) {
			session.setMaxInactiveInterval(3600); //10분
			session.setAttribute("usersId", usersId);
			
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
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {	
		session.invalidate();
		return "redirect:/home";
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model) {	
		return "thymeleaf/choco/mypage";
	}
	
	
	@GetMapping("/checkId")
	@ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkId(@RequestParam("usersId") String usersId) {
		boolean exists = usersService.checkId(usersId); // 서비스에서 중복 여부 확인
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response); // JSON 응답 반환
    }
	
	@GetMapping("/signup")
	public String insertUsersAndPet() {
		return "thymeleaf/choco/users/signup";
	}
	
	@PostMapping("/signup")
	public String insertUsersAndPet(Users users, Pet pet, Model model, RedirectAttributes redirectAttrs) {
		
		log.info("회원가입 try 진입");
		
		try {
			
			int petId = petService.createPetId();
			pet.setPetId(petId);
			
			// 파일 데이터를 byte[]로 변환
	        if (users.getUsersProfile() != null && !users.getUsersProfile().isEmpty()
	        		&& pet.getPetProfile() != null && !pet.getPetProfile().isEmpty()) {
	        	
	        	users.setUsersProfileBytes(users.getUsersProfile().getBytes());
	        	pet.setPetProfileBytes(pet.getPetProfile().getBytes());
	        }
	        
	        pet.setUsersId(users.getUsersId());
	        
			log.info("try문에서 getBytes() 지나옴");
			
			usersService.insertUsersAndPet(users, pet);
			log.info("insertUsersAndPet 지나옴");
			
			redirectAttrs.addFlashAttribute("message", users.getUsersName()+"님 회원가입이 완료되었습니다.");
			
			return "redirect:/main_login";
			
		}
		
		catch(Exception e) {
			redirectAttrs.addFlashAttribute("errorMessage", "회원가입 정보가 일치하지 않습니다. 다시 입력해주세요. (" + e.getMessage() +")");
			
			return "redirect:/signup";
		}
		
	}
	
	
	@GetMapping("idCheck")
	public boolean idCheck(@RequestParam(value="inputId") String inputId) {
		
		boolean isChecked = usersRepository.getIdCheck(inputId);
		
		return isChecked;
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
	
	
	
	
	
	@GetMapping("/users/{usersId}")
	public String usersName(@PathVariable("usersId") String usersId, Model model) {
		
		model.addAttribute("name", usersService.getUsersName(usersId));
		
		return "thymeleaf/choco/users/users_detail";
	}
	
	
}