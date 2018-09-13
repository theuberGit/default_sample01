package uber.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uber.dto.Member;

@Service("securityService")
public class PassLoginService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		//String show_password = passwordEncoder.encode("guest");

		Member member = new Member();
		
		member.setUsername(username);
		member.setPassword(passwordEncoder.encode(username));
		member.getAuthorities();
		//System.out.println(show_password);
		
		return member;
	}
}
