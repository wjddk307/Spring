package co.micol.prj.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.micol.prj.member.service.MemberService;
@Controller
public class MemberController {
	@Autowired
	private MemberService dao; // memberService를 dao로 자동주입 
	
	@RequestMapping("/memberList.do")
	public String memberList(Model model) { // Model=> 처리할 페이지 전달
		model.addAttribute("members", dao.memberSelectList()); //멤버목록 결과를 members에 담는다.
		return "member/memberList";
	}
		
}
