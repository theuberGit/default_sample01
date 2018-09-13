package uber.service.impl.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import uber.common.CustomException;
import uber.dao.common.FilesDao;
import uber.dto.common.Files;
import uber.service.face.common.FilesService;
import util.FileUpload;

@Service
public class FilesServiceImpl implements FilesService{
	
	@Autowired FilesDao filesDao;
	
	@Value("#{applicationProperties['file.upload.path']}")
	private String filePath;
	
	@Override
	public List<Files> findAll(Files files) {
		return filesDao.findAll(files);
	}

	@Override
	public Files findFileByFileIdx(Integer idx) {
		return filesDao.findFileByFileIdx(idx);
	}

	@Override
	public List<Files> findFilesByMasterIdx(Integer masterIdx) {
		return filesDao.findFilesByMasterIdx(masterIdx);
	}
	
	/*
	 * MasterIdx, Type으로 file 검색
	 * req
	 */
	@Override
	public List<Files> findFilesByMasterIdxAndTypeForUpload(Integer masterIdx, String type) {
		Files files = new Files();
		files.setMasterIdx(masterIdx);
		files.setType(type);
		List<Files> list = filesDao.findFilesByMasterIdxAndType(files);
		if(list.isEmpty()){
			list.add(new Files());
		}
		return list;
	}

	@Override
	public void insertFile(Files files) {
		filesDao.insertFile(files);
	}

	@Override
	public void updateMasterIdx(Files files) {
		filesDao.updateMasterIdx(files);
	}

	@Override
	public void deleteFileByFileIdx(Integer fileIdx) {
		filesDao.deleteFileByFileIdx(fileIdx);
	}

	@Override
	public Integer findFileSequence() {
		return filesDao.findFileSequence();
	}

	private void fileUpload(List<MultipartFile> files, Integer masterIdx, List<Integer> fileIdxs) {
		fileUploadLogic(files, null, 0, masterIdx, fileIdxs);
	}

	private void fileUpload(List<MultipartFile> files, Integer masterIdx,
			List<Integer> fileIdxs, long fileSize, String allowType) {
		fileUploadLogic(files, allowType, fileSize, masterIdx, fileIdxs);
	}

	/*
	 * 파일 업로드 
	 * 
	 */
	@Transactional(value = "transactionManager", rollbackFor = Exception.class)
	private Files fileUploadLogic(List<MultipartFile> files, String allowType, long fileSize, Integer masterIdx,
			List<Integer> fileIdxs) {
		Files filesItem = new Files();
		
		for (int idx = 0, size = files.size(); idx < size; idx++) {
			MultipartFile file = files.get(idx);
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
				validationFileUpload(file, allowType, fileSize, fileType);//file type 및 size 체크
				String replaceName = replaceFileName();//파일 이름 변환
				try {
					FileUpload.fileUpload(file, filePath, replaceName);//실제 file path에 upload
					filesItem.setOriginalFileName(fileName);
					filesItem.setSavedFileName(replaceName);
					filesItem.setMasterIdx(masterIdx);
					filesItem.setType(file.getName());
					if (fileIdxs == null || fileIdxs.isEmpty() || fileIdxs.get(idx) == -1) {//새로 올리는 file인 경우, db에 file 정보 insert
						filesDao.insertFile(filesItem);
					} else {//기존 file을 수정하는 경우, 해당 fileIdx로 update
						filesItem.setFileIdx(fileIdxs.get(idx));
						filesDao.updateFile(filesItem);
					}
				} catch (IOException e) {
					e.printStackTrace();
					throw new CustomException("fail to file Upload : IOException");
				}
			}
		}
		return filesItem;
	}

	private boolean deleteRealFile(Files filesItem) {
		File file = new File(filePath + "/" + filesItem.getSavedFileName());
		if (file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}

	/*
	 *  file 경로에 있는 실제 file 삭제
	 *  @param(fileIdx)
	 */
	@Override
	public boolean deleteRealFilesByFileIdx(Integer idx) {
		Files filesItem = filesDao.findFileByFileIdx(idx);
		if (filesItem == null)
			return false;
		return deleteRealFile(filesItem);
	}

	/*
	 * 실제 file 및 db 삭제
	 * @param(fileIdx)
	 */
	@Override
	public boolean deleteRealFilesAndDataByFileIdx(Integer idx) {
		Files filesItem = filesDao.findFileByFileIdx(idx);
		if (filesItem == null)
			return false;
		filesDao.deleteFileByFileIdx(filesItem.getFileIdx());
		return deleteRealFile(filesItem);
	}

	/*
	 * 실제 file 및 db 삭제
	 * @param(masterIdx)
	 */
	@Override
	public boolean deleteRealFilesAndDataByFileMasterIdx(Integer masterIdx) {
		List<Files> fileList = filesDao.findFilesByMasterIdx(masterIdx);
		if (fileList.isEmpty())
			return false;
		for (Files filesItem : fileList) {
			filesDao.deleteFileByFileIdx(filesItem.getFileIdx());
			deleteRealFile(filesItem);
		}
		return true;
	}

	@Override
	public File convertMultiPartFileToFile(MultipartFile multipartFile) {
		File convFile = new File(filePath + "/" + multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(convFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convFile;
	}

	@Override
	public void deleteFileAndFileUpload(MultipartRequest multipartRequest, String inputFileName, Integer masterIdx,
			List<Integer> fileIdxs, long fileSize, String allowType) {
		for (int i = 0, size = multipartRequest.getFiles(inputFileName).size(); i < size; i++) {
			if(multipartRequest.getFiles(inputFileName) != null && fileIdxs != null){
				if (!multipartRequest.getFiles(inputFileName).get(i).isEmpty() && !fileIdxs.isEmpty() && fileIdxs.get(i) != null) {
					deleteRealFilesByFileIdx(fileIdxs.get(i));
				}
			}
		}
		fileUpload(multipartRequest.getFiles(inputFileName), masterIdx, fileIdxs, fileSize, allowType);
	}

	@Override
	public void deleteFileAndFileUpload(MultipartRequest multipartRequest, String inputFileName, Integer masterIdx,
			List<Integer> fileIdxs) {
		deleteFileAndFileUpload(multipartRequest, inputFileName, masterIdx, fileIdxs, 0, null);
	}
	
	@Override
	public void fileUpload(MultipartRequest multipartRequest, String inputFileName, Integer masterIdx) {
		fileUpload(multipartRequest.getFiles(inputFileName), masterIdx, null, 0, null);
	}

	@Override
	public void fileUpload(MultipartRequest multipartRequest, String inputFileName, Integer masterIdx, long fileSize,
			String allowType) {
		fileUpload(multipartRequest.getFiles(inputFileName), masterIdx, null, fileSize, allowType);
	}

	/*
	 * 파일이름 > 현재시간(nanoTime) + 랜덤 숫자로 변환
	 */
	private String replaceFileName() {
		Random random = new Random(System.nanoTime());
		StringBuffer createFileName = new StringBuffer();
		createFileName.append(String.valueOf(System.nanoTime()));
		createFileName.append(String.valueOf(random.nextLong()));
		String copyFileName = createFileName.toString();
		String replaceName = copyFileName + ".tmp";
		return replaceName;
	}

	/*
	 * 파일 타입 및 용량 체크
	 */
	private void validationFileUpload(MultipartFile file, String allowType, long fileSize, String fileType) {
		if (allowType == null || allowType.indexOf(fileType.toLowerCase()) < 0) {
			throw new CustomException("Don't allow this file type.");
		}
		if (fileSize != 0 && file.getSize() > fileSize) {
			throw new CustomException("Don't excess file size.");
		}
	}

	//파일의 순서를 유지하기 위함
	@Override
	public HashMap<String, List<Integer>> getFileIdx(MultipartRequest multipartRequest, List<Integer> fileIdxs){
		HashMap<String, List<Integer>> result = new HashMap<String, List<Integer>>();
		List<Integer> fileSizes = new ArrayList<Integer>();//file 개수
		List<String> fileNames = new ArrayList<String>();//각 file의 name
		Iterator<String> itr = multipartRequest.getFileMap().keySet().iterator();//multipart의 fileMap
		while(itr.hasNext()){
			String temp = itr.next();//fileMap에서 하나씩 꺼내어
			fileNames.add(temp);//fileName에 순차적으로 추가
			fileSizes.add(multipartRequest.getFiles(temp).size());//file개수 증가
		}
		for(int i = 0, size = fileSizes.size(); i < size; i++){
			List<Integer> fileIdx = new ArrayList<Integer>();
			for(int j = 0, fileSize = fileSizes.get(i); j < fileSize; j++ ){
				if(fileIdxs.size() > 0){
					fileIdx.add(fileIdxs.remove(0));//remove() > file position 반환
				}
			}
			result.put(fileNames.get(i), fileIdx);//result Map에 (파일이름, 파일인덱스) 반환
		}
		return result;
	}

	@Override
	public Files filePhotoUpload(MultipartRequest multipartRequest) {
		return fileUploadLogic(multipartRequest.getFiles("Filedata"), "jpg|png|gif|bmp", 0, 0, null);
	}

	@Override
	public void deleteRealFilesAndDataByDelIdxs(List<Integer> delIdxs) {
		if(delIdxs != null && !delIdxs.isEmpty()){
			for(int i = 0; i< delIdxs.size(); i++){
				filesDao.deleteFileByFileIdx(delIdxs.get(i));
			}
		}
	}

}
