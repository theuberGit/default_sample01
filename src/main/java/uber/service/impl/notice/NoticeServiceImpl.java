package uber.service.impl.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartRequest;

import uber.dao.notice.NoticeDao;
import uber.dto.notice.Notice;
import uber.service.face.common.FilesService;
import uber.service.face.notice.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired private NoticeDao noticeDao;
	@Autowired private FilesService filesService;
	
	@Override
	public List<Notice> findAll(Notice notice) {
		return noticeDao.findAll(notice);
	}

	@Override
	public Notice findNotice(Notice notice) {
		return noticeDao.findNotice(notice);
	}

	@Override
	public void insertNotice(Notice notice, MultipartRequest multipartRequest) {
		int masterIdx = filesService.findFileSequence();
		notice.setIdx(masterIdx);
		notice.setRegId("master");
		noticeDao.insertNotice(notice);
		filesService.fileUpload(multipartRequest, "file", masterIdx, 10 * 1024 * 1024, "jpg|png|jpeg|gif|pdf");
	}
	

}
