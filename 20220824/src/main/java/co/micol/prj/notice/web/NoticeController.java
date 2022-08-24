package co.micol.prj.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.vo.NoticeVO;

@Controller
public class NoticeController {
	@Autowired // Container에 등록 되어있는것만 가져옴
	private NoticeService service; // dao를 통해 Repository에서 결과를 가져온다.
	
	@RequestMapping("/noticeList.do")
	private String noticeList(Model model) { // Model => spring fraimwork가 제공하는 인테페이스
		// service 객체를 호출해서 값을 가져오게 처리 
		model.addAttribute("notices", service.noticeSelectList());
		return "notice/noticeList";
	}
	
	@RequestMapping("/noticeSelectList.do")
	public ModelAndView noticeSelectList(ModelAndView mv) { // ModelAndView에 객체 담음
		mv.addObject("notices", service.noticeSelectList()); // service에 결과를 담고
		mv.setViewName("notice/noticeList"); // view 선택
		return mv; // model&view 리턴
	}
	
	@RequestMapping("/noticeForm.do") // 글쓰기 폼 호출
	public String noticeForm() {
		return "notice/noticeForm";
	}
	
	@PostMapping("/noticeInsert.do") // 글 등록
	public String noticeInsert(NoticeVO vo, MultipartFile mfile) { // vo에는 text, mfile에는 file객체 / fileUpload 해야함.
		// 요부분에 첨부파일 처리
		System.out.println(vo.getNotice_writer());
		System.out.println(vo.getNotice_title());
		service.noticeInsert(vo); //  글내용 등록
		return "redirect:noticeList.do"; // 글 목록으로 돌아가기 => redirect붙으면 handerMapper로 보냄
	}
	
	@RequestMapping("/noticeSelect.do")
	public String noticeSelect(NoticeVO vo, Model model) {
		model.addAttribute("notice", service.noticeSelect(vo));
		return "notice/noticeSelect";
	}
	
	@RequestMapping("/noticeUpdate.do") // 글 등록
	public String noticeUpdate(NoticeVO vo, Model model) {
		int n = service.noticeInsert(vo);
		if(n != 0) {
			model.addAttribute("message", "정상적으로 변경되었습니다.");
		}else {
			model.addAttribute("message", "변경하지 못했습니다.");
		}
		return "notice/noticeUpdate";
	}
	
	@RequestMapping("/noticeDelete.do")
	public String noticeDelete(NoticeVO vo, Model model) {
		int n = service.noticeDelete(vo);
		if(n != 0) {
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
		}else {
			model.addAttribute("message", "삭제하지 못했습니다.");
		}
		return "notice/noticeDelete";
	}
	
	
	

}
