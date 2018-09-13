package uber.controller.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartRequest;

import uber.dto.notice.Notice;
import uber.service.face.common.FilesService;
import uber.service.face.notice.NoticeService;

@Controller
@RequestMapping(value="/notice")
public class NoticeController {
	
	@Autowired private NoticeService noticeService;
	@Autowired private FilesService filesService;
	
	@RequestMapping(value="/list")
	public String list(Model model,Notice notice) {
		model.addAttribute("list", noticeService.findAll(notice));
		return "/notice/list";
	}
	
	@RequestMapping(value="/register")
	public String register() {
		
		return "/notice/register";
	}
	
	@RequestMapping(value="/register-proc", method = RequestMethod.POST)
	public String registerProc(Notice notice, MultipartRequest multipartRequest) {
		noticeService.insertNotice(notice, multipartRequest);
		return "redirect:list";
	}
	
	@RequestMapping(value="/modify")
	public String modify(Notice notice, MultipartRequest multipartRequest) {
		
		return "/notice/modify";
	}
	
	@RequestMapping(value="/view")
	public String view(Model model, Notice notice) {
		Notice item = noticeService.findNotice(notice);
		model.addAttribute("item", item);
		model.addAttribute("file", filesService.findFilesByMasterIdxAndTypeForUpload(item.getIdx(), "file"));
		return "/notice/view";
	}

}
