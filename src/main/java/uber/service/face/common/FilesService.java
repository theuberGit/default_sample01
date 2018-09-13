package uber.service.face.common;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import uber.dto.common.Files;

public interface FilesService {
	
public Integer findFileSequence();
	
	public List<Files> findAll(Files files);
	
	public Files findFileByFileIdx(Integer idx);
	
	public List<Files> findFilesByMasterIdx(Integer masterIdx);
	
	public List<Files> findFilesByMasterIdxAndTypeForUpload(Integer masterIdx, String type);
	
	public void insertFile(Files files);
	
	public void updateMasterIdx(Files files);
	
	public void deleteFileByFileIdx(Integer fileIdx);
	
	public boolean deleteRealFilesByFileIdx(Integer idx);
	
	public boolean deleteRealFilesAndDataByFileIdx(Integer idx);
	
	public boolean deleteRealFilesAndDataByFileMasterIdx(Integer masterIdx);
	
	public File convertMultiPartFileToFile(MultipartFile multipartFile);
	
	public void fileUpload(MultipartRequest multipartRequest, String inputFileName, Integer masterIdx);
	
	public void fileUpload(MultipartRequest multipartRequest, String inputFileName, Integer masterIdx, long fileSize, String allowType);
	
	public Files filePhotoUpload(MultipartRequest multipartRequest);
	
	/**
	 * 파일을 교체할 경우 파일을 삭제하고 다시 삽입. 
	 * 처리 후에 masterIdx가 반환되므로 해당 게시물의 masterIdx에 갱신이 필요.
	 * 
	 * @param multipartRequest
	 * @param inputFileName 
	 * @param masterIdx
	 * @param fileIdxs
	 * @return
	 */
	public void deleteFileAndFileUpload(MultipartRequest multipartRequest, String inputFileName, Integer masterIdx, List<Integer> fileIdxs);
	
	public void deleteFileAndFileUpload(MultipartRequest multipartRequest, String inputFileName, Integer masterIdx, List<Integer> fileIdxs, long fileSize, String allowType);
	
	public HashMap<String, List<Integer>> getFileIdx(MultipartRequest multipartRequest, List<Integer> fileIdxs);

	public void deleteRealFilesAndDataByDelIdxs(List<Integer> delIdxs);
	

}
