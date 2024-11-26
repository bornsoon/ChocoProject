package com.choco.heart.controller;

import java.util.Map;
import java.util.HashMap;

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

	@GetMapping("/insert")
	public String insertHeart(@RequestParam("heartId") int heartId, HttpSession session, RedirectAttributes redirectAttributes) {
        String sessionId = (String) session.getAttribute("usersId"); // 세션에서 사용자 ID 가져오기
        Heart heart = new Heart();
        heart.setHeartId(heartId);
        heart.setUsersId(sessionId);
        try {
            heartService.insertHeart(heart);
            log.info("좋아요 성공");
        } catch (RuntimeException e) {
            log.error("좋아요 추가 중 오류 발생: {}", e.getMessage());
        }
        return "redirect:/board/" + heartId;
    }
	
	@GetMapping("/delete")
	public String deleteHeart(@RequestParam("heartId") int heartId, HttpSession session) {
		String sessionId = (String) session.getAttribute("usersId");
		Map<String, Object> heartMap = new HashMap<String, Object>();
		heartMap.put("heartId", heartId);
		heartMap.put("usersId", sessionId);
		try {
			heartService.deleteHeart(heartMap);
			log.info("좋아요 삭제 성공");
        } catch (RuntimeException e) {
            log.error("좋아요 삭제 중 오류 발생: {}", e.getMessage());
        }
		return "redirect:/board/" + heartId;
    }
        
    @GetMapping("/count")
    public String getHeartCount(@RequestParam("boardId") int boardId, Model model) {
        int count = heartService.getHeartCount(boardId);
        model.addAttribute("heartCount", count);
        return "heart/count"; // 좋아요 개수를 보여줄 뷰
    }
}
    
	