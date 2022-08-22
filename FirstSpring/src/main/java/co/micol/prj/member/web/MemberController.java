package co.micol.prj.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller /*member 관리 Controller*/
public class MemberController {
	
	@GetMapping("memberList.do") /*전달인자가 없으면 Get방식*/
	public String memberList() {
		return "member/memberList";
	}

	@RequestMapping("memberSearch.do")
	public String memberSearch() {
		return "member/memberSearch";
	}
	
	@RequestMapping("memberInsert.do")
	public String memberInsert() {
		return "member/memberInsert";
	}
}
