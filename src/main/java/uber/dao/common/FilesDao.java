package uber.dao.common;

import java.util.List;

import uber.dto.common.Files;

public interface FilesDao {
	
	public List<Files> findAll(Files files);
	
	public Files findFileByFileIdx(Integer idx);
	
	public List<Files> findFilesByMasterIdx(Integer masterIdx);
	
	public List<Files> findFilesByMasterIdxAndType(Files files);
	
	public void insertFile(Files files);
	
	public void updateFile(Files files);
	
	public void updateMasterIdx(Files files);
	
	public void deleteFileByFileIdx(Integer fileIdx);
	
	public Integer findFileSequence();

}
