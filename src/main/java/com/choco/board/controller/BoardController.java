package com.choco.board.controller;

import java.io.File;
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
import com.choco.heart.service.HeartService;
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
	
	@Autowired
	HeartService heartService;
	
	@GetMapping(value={"", "/"})
	public String boardList(Model model, @RequestParam(value = "sort", required = false) String sort,
			                HttpSession session, RedirectAttributes redirectAttributes) {
		
		if (sort == null || sort.equals("latest")) {
			List<Board> boardList = boardService.getBoardList();
			model.addAttribute("boardList", boardList);
		} else if (sort.equals("popular")) {
			List<Board> boardList = boardService.getBoardListByHeart();
			model.addAttribute("boardList", boardList);
		}   
		model.addAttribute("sort", sort);
		
		return "thymeleaf/choco/board/board";
	}
	
	@GetMapping("/category/{boardCategory}")
	public String boardListByCategory(@PathVariable("boardCategory") String boardCategory, Model model,
			                          @RequestParam(value = "sort", required = false) String sort,
			                          RedirectAttributes redirectAttributes) {
		
		if (sort == null || sort.equals("latest")) {
			List<Board> boardList = boardService.getBoardList(boardCategory);
			model.addAttribute("boardList", boardList);	
		} else if (sort.equals("popular")) {
			List<Board> boardList = boardService.getBoardListByHeart(boardCategory);
			model.addAttribute("boardList", boardList);
		}
		model.addAttribute("boardCategory", boardCategory);
		model.addAttribute("sort", sort);

		return "thymeleaf/choco/board/board";
	}
	
	@GetMapping("/{boardId}")
	public String getBoardInfo(@PathVariable("boardId") int boardId,
			                   HttpServletRequest request, Model model) {
		
		Board board = boardService.getBoardInfo(boardId);
		model.addAttribute("board", board);
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("usersId");
		
		List<Reply> replyList = replyService.getReplyByBoardId(boardId);
		Attach attach = attachService.getAttachFile(boardId);
		int heartSum = heartService.getHeartCount(boardId);
		int heartId = boardId;
		int heartCheck = heartService.checkHeart(heartId, sessionId);
		
		Reply reply = new Reply();
		reply.setBoardId(boardId);
		log.info("보드:" + reply.getBoardId());
		model.addAttribute("replyList", replyList);
		model.addAttribute("reply", reply);
		model.addAttribute("attach", attach);
		model.addAttribute("heartSum", heartSum);
		model.addAttribute("heartCheck", heartCheck);
		
		return "thymeleaf/choco/board/board_detail";
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
				String attachName = file.getOriginalFilename();
				attach.setAttachName(file.getOriginalFilename());
				String attachDir = "C:/labs_web/workspace/ChocoProject/src/main/resources/static/attach/" + attachName;
				file.transferTo(new File(attachDir));
//				attach.setAttachFile(file.getBytes());
				attachService.insertAttach(attach);
			}
			redirectAttributes.addFlashAttribute("message", "게시글이 등록되었습니다.");
			log.info("게시글 등록 성공");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			log.error("게시글 등록 오류: ", ex.getMessage());
		}
		return "redirect:/board";
	}
	
	@GetMapping("/update")
	public String updateBoard(@RequestParam("boardId") int boardId, Model model,
			                  HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Board board = boardService.getBoardInfo(boardId);
		//List<Attach> attachList = attachService.getAttachList(boardId);
		//Attach attach = attachService.getAttachFile(boardId);
		String usersId = board.getUsersId();
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("usersId");
		if (sessionId.equals(usersId)) {
			model.addAttribute("board", board);
			//model.addAttribute("attach", attach);
			model.addAttribute("update", true);
			return "thymeleaf/choco/board/board_form";
		} else {
			return "redirect:/board";
		}
	}
	
	@PostMapping("/update")
	public String updateBoard(@ModelAttribute Board board, HttpServletRequest request, //@ModelAttribute Attach attach,
							  @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			boardService.updateBoard(board);
			if (file != null && !file.isEmpty()) {	
				Attach attach = new Attach();
				attach.setBoardId(board.getBoardId());
				String attachName = file.getOriginalFilename();
				attach.setAttachName(file.getOriginalFilename());
				attachService.updateAttach(attach);
				String attachDir = "C:/labs_web/workspace/ChocoProject/src/main/resources/static/attach/" + attachName;
				file.transferTo(new File(attachDir));
			}
			redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
			log.info("게시글 수정 성공");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			log.error("게시글 수정 오류: ", ex.getMessage());
		}
		return "redirect:/board";
	}
	
	@GetMapping("/delete")
	public String deleteBoard(@RequestParam("boardId") int boardId, HttpServletRequest request,
			                  RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("usersId");
		Board board = boardService.getBoardInfo(boardId);
		String usersId = board.getUsersId();
		if (sessionId.equals(usersId)) {
			boardService.deleteBoard(boardId);
		}
		redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
		return "redirect:/board";
	}

}