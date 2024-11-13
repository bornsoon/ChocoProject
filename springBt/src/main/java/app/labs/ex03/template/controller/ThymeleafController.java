package app.labs.ex03.template.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.labs.ex03.template.service.TemplateBasicService;
import app.labs.ex03.template.user.UserAccount;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("thy")
public class ThymeleafController {
	@Autowired
	TemplateBasicService defaultTemplateBasicService;
	
	@GetMapping("")
	public String index(Model model) {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		String today = now.format(formatter);
		
		model.addAttribute("today", today);
		
		return "thymeleaf/helloworld";
	}
	
	@GetMapping("basic")
	public String basic(HttpServletRequest request, 
			            HttpSession session, Model model) {
		List<String> list = defaultTemplateBasicService.getList();
		Map<String, String> map = defaultTemplateBasicService.getMap();
		UserAccount userAccount = defaultTemplateBasicService.getUserAccount();
		
		model.addAttribute("str", "안녕하세요.");
		model.addAttribute("msg", "<b>EL Test</b>");
		model.addAttribute("num", 3.14);
		
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		model.addAttribute("user", userAccount);
		
		session.setAttribute("msg", "EL Test");
		request.setAttribute("a", 10);
		
		return "thymeleaf/basic";
	}
}







