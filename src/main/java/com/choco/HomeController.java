package com.choco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.choco.board.model.Board;
import com.choco.board.service.BoardService;

@Controller
public class HomeController {

	@Autowired
	BoardService boardService;
	
	@GetMapping(value={"", "/", "/home"})
	public String index(Model model) {
		List<Board> boardList = boardService.getBoardListByHeart(6);
		model.addAttribute("boardList", boardList);	

		return "thymeleaf/choco/home";
	}
}
