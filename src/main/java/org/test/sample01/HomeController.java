package org.test.sample01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.test.sample01.dto.board.Board;
import org.test.sample01.service.board.BoardService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Value("#{applicationProperties['file.upload.path']}") 
	private String filePath;
	@Autowired private BoardService boardService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, Board board) {
		
		System.out.println(filePath);
		System.out.println(boardService.findAll(board));
		
		return "home";
	}
	
}
