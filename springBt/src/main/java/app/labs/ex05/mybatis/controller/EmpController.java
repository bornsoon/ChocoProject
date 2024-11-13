package app.labs.ex05.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.labs.ex05.mybatis.model.Emp;
import app.labs.ex05.mybatis.service.EmpService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmpController {

	@Autowired
	EmpService empService;
	
	@GetMapping(value = {"/hr", "/hr/"})
	public String home(Model model) {
		
		model.addAttribute("serverTime",  "서버시간");
		
		return "thymeleaf/mybatis/home";
	}
	
	@GetMapping("/hr/count")
	public String empCount(@RequestParam(value="deptid", required=false, defaultValue="0") int deptid, Model model) {
		if(deptid==0) {
			model.addAttribute("count", empService.getEmpCount());
		}
		else {
			model.addAttribute("count", empService.getEmpCount(deptid));
		}
		
		return "thymeleaf/mybatis/hr/count";
	}

	@GetMapping("/hr/list")
	public String getAllEmps(Model model) {
		List<Emp> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
		
		return "thymeleaf/mybatis/hr/list";
	}

	@GetMapping("/hr/{empId}") // 다수의 경로변수가 생성될 수 있어 이름을 꼭 부여한다.(미지정 오류 발생)
	public String getEmpInfo(@PathVariable("empId") int employeeId, Model model) {
		Emp emp = empService.getEmpInfo(employeeId);
		model.addAttribute("emp", emp);
		
		return "thymeleaf/mybatis/hr/view";
	}

	@GetMapping("/hr/insert")
	public String insertEmp(Model model) {
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		
		return "thymeleaf/mybatis/hr/insertform";
	}
	
	@PostMapping("/hr/insert")
	public String insertEmp(Emp emp, RedirectAttributes redirectAttributes) {
		try {
			empService.insertEmp(emp);
			redirectAttributes.addFlashAttribute("message", 
					emp.getEmployeeId()+"번 사원정보가 입력되었습니다.");
		}
		catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/hr/list";
	}
	
	@GetMapping("/hr/update")
	public String updateEmp(@RequestParam("empid") int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		
		return "thymeleaf/mybatis/hr/updateform";
	}
	
	@PostMapping("/hr/update")
	public String updateEmp(Emp emp, RedirectAttributes redirectAttributes) {
		try {
			empService.updateEmp(emp);
			redirectAttributes.addFlashAttribute("message",
					emp.getEmployeeId() + "번 사원정보가 수정되었습니다.");
		}
		catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/hr/" + emp.getEmployeeId();
	}
	
	@GetMapping("/hr/delete")
	public String deleteEmp(@RequestParam("empid") int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		
		return "thymeleaf/mybatis/hr/deleteform";
	}

	@PostMapping("/hr/delete")
	public String deleteEmp(@RequestParam("empid") int empid, @RequestParam("email") String email, Model model, RedirectAttributes redirectAttrs) {
		try {
			int deletedRow = empService.deleteEmp(empid, email);
			if(deletedRow>0) {
				redirectAttrs.addFlashAttribute("message",
						empid + "번 사원정보가 삭제되었습니다.");
				
				return "redirect:/hr/list";
			}
			else {
				model.addAttribute("message", "사번과 이메일 주소가 다릅니다.");
				model.addAttribute("emp", empService.getEmpInfo(empid));
				
				return "thymeleaf/mybatis/hr/deleteform";
			}					
		}
		catch(RuntimeException e) {
			redirectAttrs.addFlashAttribute("message", e.getMessage());
			
			return "redirect:/hr/list";
		}
	}
	/*
	@ExceptionHandler({RuntimeException.class})
	public ModelAndView runtimeException(HttpServletRequest request, Exception ex) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url",  request.getRequestURI());
		mav.addObject("exception", ex);
		mav.setViewName("thymeleaf/mybatis/error/runtime");
		return mav;
	}
	*/
	@GetMapping("/hr/json")
	public @ResponseBody List<Emp> getEmpJSONList() {
		return empService.getEmpList();
	}
	
	@GetMapping("/hr/json/{employeeId}")
	public @ResponseBody Emp getEmpJSONInfo(@PathVariable int employeeId) {
		return empService.getEmpInfo(employeeId);
	}
	
	@GetMapping("/hr/login")
	public String loginEmp(Model model) {	
		return "thymeleaf/mybatis/hr/loginform";
	}
	
	@PostMapping("/hr/login")
	public String loginEmp(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, 
			               HttpSession session, RedirectAttributes redirectAttrs) {	
		
		Emp emp = empService.loginEmp(firstName, lastName);
				
		if (emp != null) {
			session.setMaxInactiveInterval(600); //10분
			session.setAttribute("userid", firstName);
			
			return "redirect:/hr/list";
		}
		else {
			session.invalidate();
			redirectAttrs.addFlashAttribute("message", "아이디 또는 패스워드가 잘못되었습니다.");
			
			return "redirect:/hr/login";
		}
		
	}
	
	@GetMapping("/hr/logout")
	public String logioutEmp(HttpSession session, Model model) {	
		session.invalidate();
		return "redirect:/hr/list";
	}	
}//end class