package app.labs.ex03.template.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.labs.ex03.template.service.TemplateBasicService;
import app.labs.ex03.template.user.UserAccount;

@Controller
public class JspController {
	@Autowired
	TemplateBasicService defaultTemplateBasicService;
	
	@GetMapping("jsp")
	public String index(Model model) {
		List<String> list = defaultTemplateBasicService.getList();
		Map<String, String> map = defaultTemplateBasicService.getMap();
		UserAccount userAccount = defaultTemplateBasicService.getUserAccount();
		
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		model.addAttribute("user", userAccount);		
		
		return "basic";
	}
	
}
