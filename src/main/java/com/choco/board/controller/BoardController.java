package com.choco.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choco.attach.model.Attach;
import com.choco.attach.service.AttachService;
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
	AttachService attachService;
	
	@GetMapping(value={"", "/"})
	public String boardList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		
		try {
			List<Board> boardList = boardService.getBoardList();
			model.addAttribute("boardList", boardList);	
			session.setAttribute("usersId", "lhl576");
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
		
		return "thymeleaf/choco/board/board_info";
	}
	
	@GetMapping("/create")
	public String createBoard(Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("board", new Board());	
		
		return "thymeleaf/choco/board/board_form";
	}
	
	@PostMapping("/create")
	public String createBoard(Board board, HttpServletRequest request,
							  @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String usersId = (String)session.getAttribute("usersId");;
		
		board.setUsersId(usersId);
	    int boardId = board.getBoardId();
		
		// !!!!파일 작업은 꼭 try로 감싸주기!!!!
		try {
			if (file != null && !file.isEmpty()) {
				log.info(file.getOriginalFilename());  
				Attach attach = new Attach();
				attach.setAttachName(file.getOriginalFilename());
				attach.setAttachFile(file.getBytes());
				attach.setBoardId(boardId);
				attachService.insertAttach(boardId);
			}
			boardService.createBoard(board);
			redirectAttributes.addFlashAttribute("message", usersId + "님의 게시글이 등록되었습니다.");

		}
		catch(Exception ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}
		return "redirect:/board";
	}
	
	@GetMapping("/update")
	public String updateBoard(@RequestParam("boardId") int boardId,  Model model,
			                  HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Board board = boardService.getBoardInfo(boardId);
		String usersId = board.getUsersId();
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("usersId");
		if (sessionId.equals(usersId)) {
			boardService.deleteBoard(boardId);
			model.addAttribute("board", board);
			return "thymeleaf/choco/board/board_form";
		} else {
			return "redirect:/board";
		}
	}
	
	@GetMapping("/delete")
	public String deleteBoard(@RequestParam("boardId") int boardId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("usersId");
		Board board = boardService.getBoardInfo(boardId);
		String usersId = board.getUsersId();
		if (sessionId.equals(usersId)) {
			boardService.deleteBoard(boardId);
		}
		return "redirect:/board";
	}

}