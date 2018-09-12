package uber.service.face;

import java.util.List;
import uber.dto.notice.Notice;

public interface NoticeService {
	
	public List<Notice> findAll(Notice Notice);
}
