package org.test.sample01.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.sample01.dao.board.BoardDao;
import org.test.sample01.dto.board.Board;

@Service
public class BoardService {

	@Autowired private BoardDao boardDao;
	
	public List<Board> findAll(Board board){
		return boardDao.findAll(board);
	}
}
