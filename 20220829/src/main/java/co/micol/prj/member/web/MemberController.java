package co.micol.prj.member.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.vo.MemberVO;


@Controller
public class MemberController {
	@Autowired
	private MemberService service; // dao를 통해 Repository에서 결과를 가져온다.
	
	@GetMapping("/memberSelectList.do") // 값을 전달 안해도 결과볼려면 get, 값을 던지고 싶으면 => post
	private String memberList(Model model) { // Model => spring fraimwork가 제공하는 인테페이스
		// service 객체를 호출해서 값을 가져오게 처리 
		model.addAttribute("members", service.memberSelectList());
		return "member/memberSelectList";
	}
	
	@RequestMapping("/memberSelect.do")
	public String memberSelect(MemberVO vo, Model model) {
		model.addAttribute("member", service.memberSelect(vo));
		return "member/memberSelect";
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberVO vo, Model model) {
		// insert 처리
		int n = service.memberInsert(vo);
		if(n != 0) {
			model.addAttribute("message", "정상적으로 추가었습니다.");
		}else {
			model.addAttribute("message", "멤버를 추가하지 못했습니다");
		}
		return "member/memberInsert";
	}
	
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(MemberVO vo, Model model) {
		int n = service.memberDelete(vo);
		if(n != 0) {
			model.addAttribute("message", "정상적으로 변경되었습니다.");
		}else {
			model.addAttribute("message", "멤버를 변경하지 못했습니다");
		}
		return "member/memberInsert";
	}
	
	@RequestMapping("/memberDelete.do")
	public String memberDelete(MemberVO vo, Model model) {
		int n = service.memberDelete(vo);
		if(n != 0) {
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
		}else {
			model.addAttribute("message", "멤버삭제를 하지 못했습니다");
		}
		return "member/memberInsert";
	}
	
/*	@PostMapping("/memberLogin.do")
	public String memberLogin(MemberVO vo, HttpSession session, Model model) {
		vo = service.memberSelect(vo); // vo = id, password
		String viewPage = "member/memberMessage"; // 실패했을 때 가는 페이지
		if(vo == null) {
			model.addAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
		}else {
			session.setAttribute("id", vo.getMemberId());
			session.setAttribute("name", vo.getMemberName());
			session.setAttribute("author", vo.getMemberAuthor());
			model.addAttribute("message", vo.getMemberName()+ "님 환영합니다.");
			viewPage = "home/home"; // redirect:home.do
		}
		return viewPage; 
	} */
	
	
	@PostMapping("/memberLogin.do") // ModelAndView 객체사용시 권장하지 않음.
	public ModelAndView memberLogin(MemberVO vo, HttpSession session, ModelAndView mv) {
		vo = service.memberSelect(vo); // 로그인 처리
		if(vo == null) {
			mv.setViewName("member/memberMessage"); // 보여줄 페이지정의
			mv.addObject("message", "아이디 또는 패스워즈가 일치하지 않습니다."); // 넘겨줄 데이터
		}else {
			session.setAttribute("id", vo.getMemberId());
			session.setAttribute("name", vo.getMemberName());
			session.setAttribute("author", vo.getMemberAuthor());
			mv.setViewName("home/home"); // 보여줄 페이지정의
	
		}
		return mv;
	}
	
	@GetMapping("/memberLoginForm.do") // 단순한 form호출은 Get가능.405
	public String memberLoginForm() {
		return "memberLoginForm";
	}
	
	
}
