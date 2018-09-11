package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/membership")
public class loginController {

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String signin() {
		
		return "/membership/login";
	}
}
