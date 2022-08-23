package co.micol.prj.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemberService service; // dao를 통해 Repository에서 결과를 가져온다.
	
	@RequestMapping("/memberSelectList.do")
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
	
	
}
