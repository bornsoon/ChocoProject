package com.choco.users.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
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

import com.choco.board.model.Board;
import com.choco.board.service.BoardService;
import com.choco.pet.Service.PetService;
import com.choco.pet.model.Pet;
import com.choco.users.model.Users;
import com.choco.users.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
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
	BoardService boardService;

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
			session.setMaxInactiveInterval(600); //10분
			session.setAttribute("usersId", usersId);
			
			return "redirect:/home";
		}
		else {
			session.invalidate();
			redirectAttrs.addFlashAttribute("message", "아이디 또는 패스워드가 잘못되었습니다.");
			
			return "redirect:/main_login";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {	
		session.invalidate();
		return "redirect:/home";
	}
	
	/*@GetMapping("/mypage/{usersId}")
	public String mypage(@PathVariable("usersId") String usersId, Model model) {
	
		model.addAttribute("usersId", usersService.getUsersId(usersId));
		
		return "thymeleaf/choco/mypage";
	}*/
	
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		
		return "thymeleaf/choco/mypage";
	}
	
	@GetMapping("/mypage-act")
	public String mypageact(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String usersId = (String)session.getAttribute("usersId");
		List<Board> boardList = boardService.getBoardListByUsersId(usersId);
		model.addAttribute("boardList", boardList);
		model.addAttribute("act", true);
		return "thymeleaf/choco/mypage-act";
	}
	
	@GetMapping("/mypage-act/heart")
	public String mypageactHeart(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String usersId = (String)session.getAttribute("usersId");
		List<Board> boardList = boardService.getBoardListByUsersHeart(usersId);
		model.addAttribute("boardList", boardList);
		model.addAttribute("act", false);
		return "thymeleaf/choco/mypage-act";
	}
	
	@GetMapping("/signup")
	public String insertUsersAndPet() {
		return "thymeleaf/choco/users/signup";
	}
	
	@PostMapping("/signup")
	public String insertUsersAndPet(Users users, Pet pet, Model model, RedirectAttributes redirectAttrs) {
		
		log.info("회원가입 try 진입");
		
		try {
			
			// 파일 데이터를 byte[]로 변환
			// 이미지 처리 로직
	        if (users.getUsersProfile() != null && !users.getUsersProfile().isEmpty()) {
	            users.setUsersProfileBytes(users.getUsersProfile().getBytes());
	            log.info("사용자 프로필 이미지 처리 완료");
	        }
	        
	        if (pet.getPetProfile() != null && !pet.getPetProfile().isEmpty()) {
	            pet.setPetProfileBytes(pet.getPetProfile().getBytes());
	            log.info("펫 프로필 이미지 처리 완료");
	        }
	        
	        pet.setUsersId(users.getUsersId());
	        
	        int petId = petService.createPetId();
			pet.setPetId(petId);
	        
			log.info("try문에서 getBytes() 지나옴");
			log.info("Birthdate: " + users.getUsersBirthdate());
			log.info("Profile Bytes: " + Arrays.toString(users.getUsersProfileBytes()));
			log.info("Users: " + users.toString());
			
			
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
	
	
	@GetMapping("/signup/IdCheck")
	@ResponseBody
	public String IdCheck(@RequestParam("id") String usersId) {
		System.out.println("id: " + usersId);
		
		return usersService.IdCheck(usersId);
	}
	
	@GetMapping("/signup/NicknameCheck")
	@ResponseBody
	public String NicknameCheck(@RequestParam("nickname") String usersNickname) {
		System.out.println("nickname: " + usersNickname);
		
		return usersService.NicknameCheck(usersNickname);
	}
	
	@GetMapping("/signup/EmailCheck")
	@ResponseBody
	public String EmailCheck(@RequestParam("email") String usersEmail) {
		System.out.println("email: " + usersEmail);
		
		return usersService.EmailCheck(usersEmail);
	}
	
	@GetMapping("/getAllUsersIds")
    public List<String> getAllUsersIds() {
        return usersService.getAllUsersIds(); // USERS_ID 리스트 반환
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
	
	
	@GetMapping("/revise")
	public String reviseUsersAndPet(HttpSession session, Model model) {
	    String usersId = (String) session.getAttribute("usersId");
	    
	    if (usersId == null) {
	        return "redirect:/main_login";
	    }
	    
	    try {
	    	Users users = usersService.getUsersInfo(usersId);
	    	model.addAttribute("usersList", users);
	    
	    	// 사용자 프로필 이미지 처리
	    	if (users.getUsersProfileBytes() != null) {
	    		String usersProfileBase64 = Base64.getEncoder().encodeToString(users.getUsersProfileBytes());
	    		model.addAttribute("usersProfileImage", "data:image/jpeg;base64," + usersProfileBase64);
	    	}
	    	
	    	Pet pet = petService.getPetInfo(usersId);
	    	model.addAttribute("petList", pet);
	    	
	    	// 펫 프로필 이미지 처리
	    	if (pet != null && pet.getPetProfileBytes() != null) {
	    		String petProfileBase64 = Base64.getEncoder().encodeToString(pet.getPetProfileBytes());
	    		model.addAttribute("petProfileImage", "data:image/jpeg;base64," + petProfileBase64);
	    	}
	    	
	    	return "thymeleaf/choco/users/revise";
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return "redirect:/error";
	    }
	    
	    
	    
	}
	
	@PostMapping("/revise")
	public String reviseUsersAndPet(HttpSession session, Users users, Pet pet, Model model,
	        RedirectAttributes redirectAttrs) throws IOException {
	    
		try {
	        log.info("회원정보 수정 시작");
	        
	        // 이미지 처리 로직
	        if (users.getUsersProfile() != null && !users.getUsersProfile().isEmpty()) {
	            users.setUsersProfileBytes(users.getUsersProfile().getBytes());
	            log.info("사용자 프로필 이미지 처리 완료");
	        }
	        
	        if (pet.getPetProfile() != null && !pet.getPetProfile().isEmpty()) {
	            pet.setPetProfileBytes(pet.getPetProfile().getBytes());
	            log.info("펫 프로필 이미지 처리 완료");
	        }
	        
	        log.info("회원정보 수정 서비스 호출 전");
	        usersService.reviseUsersAndPet(users, pet);
	        log.info("회원정보 수정 서비스 호출 완료");
	        
	        
	        log.info("회원이름 출력: " + users.getUsersName());
	        redirectAttrs.addFlashAttribute("message", users.getUsersName()+"님의 정보가 수정되었습니다.");
	        log.info("회원정보 수정 완료 메시지 설정");
	        
	        return "redirect:/mypage";
	        
	        
	    } catch(Exception e) {
	        log.error("회원정보 수정 중 오류 발생: ", e);
	        redirectAttrs.addFlashAttribute("errorMessage", "회원수정 정보가 올바르지 않습니다. 다시 입력해주세요. (" + e.getMessage() +")");
	        
		    return "redirect:/revise";
	    }
	    
	    
	}
	
	@GetMapping("/delete")
	public String withdrawUser(HttpSession session, RedirectAttributes redirectAttrs) {
	    String usersId = (String) session.getAttribute("usersId");
	    
	    if (usersId == null) {
	        return "redirect:/main_login";
	    }
	    
	    try {
	        usersService.deleteUsersAndPet(usersId);
	        session.invalidate(); // 세션 삭제
	        redirectAttrs.addFlashAttribute("message", "회원탈퇴가 완료되었습니다.");
	        return "redirect:/home";
	        
	    } catch (Exception e) {
	        redirectAttrs.addFlashAttribute("errorMessage2", "회원탈퇴 처리 중 오류가 발생했습니다.");
	        return "redirect:/revise";
	    }
	}
	
	
	@GetMapping("/users/{usersId}")
	public String usersName(@PathVariable("usersId") String usersId, Model model) {
		
		model.addAttribute("name", usersService.getUsersName(usersId));
		
		return "thymeleaf/choco/users/users_detail";
	}
	
	
}
