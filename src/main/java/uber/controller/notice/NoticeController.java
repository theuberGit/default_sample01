package uber.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/notice")
public class NoticeController {
	
	@RequestMapping(value="/list")
	public String list() {
		
		return "/notice/list";
	}
	
	@RequestMapping(value="register")
	public String register() {
		
		return "/notice/register";
	}
	
	@RequestMapping(value="modify")
	public String modify() {
		
		return "/notice/modify";
	}
	
	@RequestMapping(value="view")
	public String view() {
		
		return "/notice/view";
	}

}
