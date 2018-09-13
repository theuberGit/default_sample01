package uber.dao.notice;

import java.util.List;

import uber.dto.notice.Notice;

public interface NoticeDao {
	
	public List<Notice> findAll(Notice notice);
	
	public Notice findNotice(Notice notice);
	
	public void insertNotice(Notice notice);

}
