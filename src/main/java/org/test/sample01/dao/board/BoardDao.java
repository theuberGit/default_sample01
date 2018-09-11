package org.test.sample01.dao.board;

import java.util.List;

import org.test.sample01.dto.board.Board;

public interface BoardDao {

	public List<Board> findAll(Board board);
}
