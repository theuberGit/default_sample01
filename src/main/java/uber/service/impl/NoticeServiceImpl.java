package uber.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uber.dao.notice.NoticeDao;
import uber.dto.notice.Notice;
import uber.service.face.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired private NoticeDao noticeDao;
	
	@Override
	public List<Notice> findAll(Notice notice) {
		return noticeDao.findAll(notice);
	}
	
	

}
