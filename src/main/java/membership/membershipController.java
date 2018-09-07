package membership;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/membership")
public class membershipController {

	@RequestMapping(value="/login")
	public void login() {
		//return "login";
	}
	
}
