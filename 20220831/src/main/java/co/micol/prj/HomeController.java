package co.micol.prj;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {		
		return "home";
	}
	
	// 메소드 오버로딩 => 동일한 역할을 하는 동일한 메소드 전달인자객체 틀리게 해서 사용가능
	@GetMapping("/home.do")
	public String home(Model model) {
		return "home";
	}
	
}
