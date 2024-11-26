package com.choco.reply.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choco.board.service.BoardService;
import com.choco.reply.model.Reply;
import com.choco.reply.service.ReplyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/reply")
public class ReplyController {
		
	@Autowired
	ReplyService replyService;
	
	// 댓글 저장
	@PostMapping("/create")
	public String createReply(@ModelAttribute Reply reply, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String usersId = (String)session.getAttribute("usersId");;
	    reply.setUsersId(usersId);
	    log.info("사용자 아이디:" + usersId);
	    log.info("게시글 아이디:" + reply.getBoardId());

	    try {       
	        replyService.createReply(reply); // 댓글 저장
	        redirectAttributes.addFlashAttribute("message", usersId + "님의 댓글이 등록되었습니다.");
	        log.info("댓글 등록 성공: userId = {}, boardId = {}", usersId, reply.getBoardId());
	    } catch (Exception ex) {
	        redirectAttributes.addFlashAttribute("message", ex.getMessage());
	        log.error("댓글 등록 실패: {}", ex.getMessage());
	    }

	    return "redirect:/board/" + reply.getBoardId(); // 댓글 등록 후 게시글로 리다이렉트
	}
	
	// 댓글 수정
	@PostMapping("/update")
	public String updateReply(@RequestBody Reply reply, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String usersId = (String)session.getAttribute("usersId");;
	    reply.setUsersId(usersId);

	    try {
	        replyService.updateReply(reply); // 댓글 수정 내용 저장
	        redirectAttributes.addFlashAttribute("message", usersId + "님의 댓글이 수정되었습니다.");
	        log.info("댓글 수정 성공: userId = {}, boardId = {}", usersId, reply.getBoardId());
	    } catch (Exception ex) {
	        redirectAttributes.addFlashAttribute("message", ex.getMessage());
	        log.error("댓글 수정 실패: {}", ex.getMessage());
	    }

	    return "redirect:/board/" + reply.getBoardId(); // 댓글 수정 후 게시글로 리다이렉트
	}
	
	// 댓글 삭제
	@PostMapping("/delete")
	public String deleteReply(@RequestBody int replyId, HttpServletRequest request, RedirectAttributes redirectAttributes) {

	    try {
	        replyService.deleteReply(replyId); // replyId로 삭제
	        log.info("댓글 삭제 성공");
	    } catch (Exception ex) {
	        log.error("댓글 삭제 실패: {}", ex.getMessage());
	    }

	    return "redirect:/board/{boardId}"; // 댓글 삭제 후 게시글로 리다이렉트
	}
	
    @GetMapping("/user/{usersId}")
    public String getReplyByUsersId(@PathVariable String usersId, Model model) {
        log.info("댓글 목록 조회 요청: userId = {}", usersId);
		List<Reply> ReplyList = replyService.getReplyByUsersId(usersId);
		model.addAttribute("replyList", ReplyList);
		return "thymeleaf/choco/board/replyform2.html";
    }	
}
