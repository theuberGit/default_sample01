package uber.service.face.notice;

import java.util.List;

import org.springframework.web.multipart.MultipartRequest;

import uber.dto.notice.Notice;

public interface NoticeService {
	
	public List<Notice> findAll(Notice Notice);
	
	public Notice findNotice(Notice notice);
	
	public void insertNotice(Notice notice, MultipartRequest multipartRequest);
}
