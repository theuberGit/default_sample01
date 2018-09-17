package uber.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class loginController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String signin() {
		
		String show_password = passwordEncoder.encode("guest");
		
		System.out.println(show_password);
		return "login";
	}
	
	@RequestMapping(value="/loginFailure", method = RequestMethod.GET)
	public String loginFailure(@RequestParam String errornum, RedirectAttributes redirectAttributes) {
		String errorMsg = "로그인에 실패하였습니다.";
		System.out.println(errorMsg);
		redirectAttributes.addFlashAttribute("msg", errorMsg);
		return "redirect:login";
	}
}


