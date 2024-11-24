package com.choco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.choco.board.model.Board;
import com.choco.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@Autowired
	BoardService boardService;
	
	@GetMapping(value={"", "/", "/home"})
	public String home(Model model) {
		List<Board> boardList = boardService.getBoardListByHeart(6);
		model.addAttribute("boardList", boardList);
		log.info("11111");
		System.out.println("1" + boardList.get(0).getAttachFile());
		System.out.println(boardList.get(5).getAttachFile());
		
		return "thymeleaf/choco/home";
	}
	
	@GetMapping("/calc")
    public String calc() {
        return "thymeleaf/choco/calc/calc";
    }
    
    @GetMapping("/calc/age")
    public String calc_age() {
        return "thymeleaf/choco/calc/calc_age";
    }
    
    @GetMapping("/calc/recommend-calorie")
    public String calc_calorie() {
        return "thymeleaf/choco/calc/calc_recommend_calorie";
    }
}
