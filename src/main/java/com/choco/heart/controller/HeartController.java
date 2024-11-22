package com.choco.heart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choco.heart.model.Heart;
import com.choco.heart.service.HeartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/heart")
public class HeartController {

	@Autowired
	HeartService heartService;
	
	@GetMapping("/check")
	public String checkHeart(@RequestParam("boardId") int boardId, HttpSession session, Model model) {
        String sessionId = (String) session.getAttribute("usersId"); // 세션에서 사용자 ID 가져오기
        
        if (sessionId == null) {
        model.addAttribute("error", "로그인이 필요합니다.");
        return "error"; // 로그인 요구 페이지로 이동
    }

    int result = heartService.checkHeart(boardId, sessionId);
    model.addAttribute("heartExists", result > 0); // 좋아요 여부 전달
    return "heart/check"; // 결과를 렌더링할 뷰
}

	@PostMapping("/insert")
	public String insertHeart(@RequestParam("boardId") int boardId, HttpSession session, RedirectAttributes redirectAttributes) {
        String sessionId = (String) session.getAttribute("usersId"); // 세션에서 사용자 ID 가져오기
        Heart heart = new Heart();
        heart.setHeartId(boardId);
        heart.setUsersId(sessionId);
        
        try {
            heartService.insertHeart(heart);
            redirectAttributes.addFlashAttribute("message", "좋아요가 추가되었습니다!");
        } catch (RuntimeException e) {
            log.error("좋아요 추가 중 오류 발생: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", "좋아요 추가 중 오류가 발생했습니다.");
        }
        return "redirect:/heart";
    }
	
	@GetMapping("/delete")
	public String deleteHeart(Model model, @Param("heartId") int heartId, @Param("usersId") String userId) {
		model.addAttribute("boardId", boardId);
		return "heart/delete"; // 삭제 확인을 위한 뷰
	}
	
	@PostMapping("/delete")
    public String deleteHeart(@RequestParam("boardId") int boardId, HttpSession session, RedirectAttributes redirectAttributes) {
        String sessionId = (String) session.getAttribute("usersId"); // 세션에서 사용자 ID 가져오기

        try {
            heartService.deleteHeart(boardId, sessionId);
            redirectAttributes.addFlashAttribute("message", "좋아요가 삭제되었습니다!");
        } catch (RuntimeException e) {
            log.error("좋아요 삭제 중 오류 발생: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", "좋아요 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/heart";
	}
        
    @GetMapping("/count")
    public String getHeartCount(@RequestParam("boardId") int boardId, Model model) {
        int count = heartService.getHeartCount(boardId);
        model.addAttribute("heartCount", count);
        return "heart/count"; // 좋아요 개수를 보여줄 뷰
    }
}
    
	