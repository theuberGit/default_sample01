package uber.controller.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import uber.dto.notice.Notice;
import uber.service.face.NoticeService;

@Controller
@RequestMapping(value="/notice")
public class NoticeController {
	
	@Autowired private NoticeService noticeService;
	
	@RequestMapping(value="/list")
	public String list(Model model,Notice notice) {
		model.addAttribute("list", noticeService.findAll(notice));
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
