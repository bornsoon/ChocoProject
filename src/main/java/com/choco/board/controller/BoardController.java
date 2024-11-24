package com.choco.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.choco.reply.model.Reply;
import com.choco.reply.service.ReplyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	AttachService attachService;
	
	@Autowired
	ReplyService replyService;
	
	@GetMapping(value={"", "/"})
	public String boardList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);

		return "thymeleaf/choco/board/board";
	}
	
	@GetMapping("/category/{boardCategory}")
	public String boardListByCategory(@PathVariable("boardCategory") String boardCategory, Model model, RedirectAttributes redirectAttributes) {
		List<Board> boardList = boardService.getBoardList(boardCategory);
		model.addAttribute("boardList", boardList);	
		model.addAttribute("boardCategory", boardCategory);	

		return "thymeleaf/choco/board/board";
	}
	
	@GetMapping("/{boardId}")
	public String getBoardInfo(@PathVariable("boardId") int boardId, Model model) {
		Board board = boardService.getBoardInfo(boardId);
		model.addAttribute("board", board);
		List<Reply> replyList = replyService.getReplyByBoardId(boardId);
		Reply reply = new Reply();
		reply.setBoardId(boardId);
		log.info("보드:" + reply.getBoardId());
		model.addAttribute("replyList", replyList);
		model.addAttribute("reply", reply);
		
		return "thymeleaf/choco/board/board_info";
	}
	
	@GetMapping("/create")
	public String createBoard(Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("board", new Board());	
		
		return "thymeleaf/choco/board/board_form";
	}
	
	@PostMapping("/create")
	public String createBoard(@ModelAttribute Board board, HttpServletRequest request,
			                  @RequestParam("boardCategory") String boardCategory,
							  @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String usersId = (String)session.getAttribute("usersId");
		board.setBoardCategory(boardCategory);
		// !!!!파일 작업은 꼭 try로 감싸주기!!!!
		try {
			board.setUsersId(usersId);
			int boardId = boardService.createBoardId();
			board.setBoardId(boardId);
			boardService.createBoard(board);
			if (file != null && !file.isEmpty()) {	
				log.info("파일명: " + file.getOriginalFilename());  
				Attach attach = new Attach();
				attach.setBoardId(boardId);
				attach.setAttachName(file.getOriginalFilename());
				attach.setAttachFile(file.getBytes());
				attachService.insertAttach(attach);
			}
			log.info("게시글 등록 성공");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			log.error("게시글 등록 오류: ", ex.getMessage());
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