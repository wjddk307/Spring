package co.micol.prj.notice.web;



import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService ns;
	
	// contextpath를 찾는용도. (webapp)
	@Autowired
	private ServletContext servletContext;
	
	@PostMapping("/noticeSelect.do")
	public String noticeSelect(NoticeVO vo, Model model) {
//		vo.setNoticeId(2); // 강제로 하나의 래코드를 선택하기 위해 만든것
		model.addAttribute("n", ns.noticeSelect(vo));
		ns.noticeHitUpdate(vo); // 조회수 증가 먼저 실행하고 돌려줌.
		return "notice/noticeSelect";
	}
	
	@GetMapping("/noticeSelectList.do")
	public String noticeSelectList(Model model) {
		model.addAttribute("notices", ns.noticeSelectList());
		return "notice/noticeSelectList";
	}
	
	@PostMapping("/noticeInsert.do") // jsp에서 file이라는 이름으로 올라오면 MultipartFile로 사용하겠다.
	public String noticeInsert(NoticeVO vo, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		// File Upload 처리해야함
		String saveFolder = servletContext.getRealPath("/fileUpload"); // 저장할 공간 폴더명
		File sfile = new File(saveFolder); // 물리적으로 저장할 위치 
		String oFileName = file.getOriginalFilename(); // 넘어온 file의 오리지날 fileName
		if(!oFileName.isEmpty()) {
		// 파일명 충돌방지를 위한 파일 별명 만들기 (file 확장자 붙이기)
		String sFileName = UUID.randomUUID().toString() + oFileName.substring(oFileName.lastIndexOf("."));
		
	    file.transferTo(new File(sfile, sFileName)); // 파일을 물리적 위치에 저장한다.
	    vo.setNoticeAttach(oFileName);
	    vo.setNoticeAttachDir(saveFolder + File.separator +sFileName); // ex) fileUpload/273747.txt
	  }
		
		ns.noticeInsert(vo);
		return "redirect:noticeSelectList.do";
	}
	
	@RequestMapping("/noticeUpdate.do")
	public String noticeUpdate(NoticeVO vo) {
		vo.setNoticeWriter("권소정");
		vo.setNoticeId(26);
		ns.noticeUpdate(vo);
		return "redirect:noticeSelectList.do";
	}
	
	// @RequestParm("key") String key
	@RequestMapping("/noticeSearch.do") // ajax로 처리
	public String noticeSearch(NoticeVO vo, String key, String val, Model model) {
		key = "3";
		val = "권소정";
		model.addAttribute("notices", ns.noticeSearch(key, val));
		
		return "notice/noticeSearch";
	}
	
	@GetMapping("/noticeForm.do")
	public String noticeInsertForm() {
		return "notice/noticeForm";
	}
	
	@ResponseBody // 호출한 페이지로 결과를 돌려보내준다.
	@RequestMapping(value = "/ajaxNoticeSelect.do", produces ="application/text; charset=UTF-8")
	public String ajaxNoticeSelect(HttpServletResponse response) {
		String message = "ajax Test를 한번 해봅니다.";
		return message;
	}
	
	@GetMapping("/ajaxTest.do")
	public String ajaxTest() {
		return "notice/ajaxTest";
	}

}
