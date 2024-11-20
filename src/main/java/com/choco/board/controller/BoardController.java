package com.choco.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choco.board.model.Board;
import com.choco.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping(value={"", "/"})
	public String boardList(Model model, RedirectAttributes redirectAttributes) {
		
		try {
			List<Board> boardList = boardService.getBoardList();
			model.addAttribute("boardList", boardList);			
		}
		catch(Exception ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}
		return "thymeleaf/choco/board/board";
	}
	
	@GetMapping("/category/{boardCategory}")
	public String boardCategoryList(@PathVariable("boardCategory") String boardCategory, Model model, RedirectAttributes redirectAttributes) {
		
		try {
			List<Board> boardList = boardService.getBoardList(boardCategory);
			model.addAttribute("boardList", boardList);	
			model.addAttribute("boardCategory", boardCategory);	
		}
		catch(Exception ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}
		return "thymeleaf/choco/board/board";
	}
	
	@GetMapping("/{boardId}")
	public String getBoardInfo(@PathVariable("boardId") int boardId, Model model) {
		Board board = boardService.getBoardInfo(boardId);
		model.addAttribute("board", board);
		
		return "thymeleaf/choco/board/board_form";
	}
	
	@GetMapping("/new")
	public String newBoard(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		model.addAttribute("board", new Board());
		
		session.setAttribute("userid", "lhl576");
		
		return "thymeleaf/choco/board/board_form";
	}
	
	@PostMapping("/add")
	public String submitBoard(Board board, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String usersId = (String)session.getAttribute("userid");
		board.setUsersId(usersId);
		
		try {
			boardService.createBoard(board);
			redirectAttributes.addFlashAttribute("message", usersId + "님의 게시글이 등록되었습니다.");
			log.info("test");

		}
		catch(Exception ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}
		return "redirect:/board";
	}

}