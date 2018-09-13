package uber.dto.common;

import java.util.ArrayList;
import java.util.List;

public class Files {
	
	private String originalFileName;
	private String savedFileName;
	private Integer masterIdx;
	private Integer fileIdx;
	private String type;
	private List<Integer> fileIdxs = new ArrayList<Integer>();

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

	public Integer getMasterIdx() {
		return masterIdx;
	}

	public void setMasterIdx(Integer masterIdx) {
		this.masterIdx = masterIdx;
	}

	public Integer getFileIdx() {
		return fileIdx;
	}

	public List<Integer> getFileIdxs() {
		return fileIdxs;
	}

	public void setFileIdxs(List<Integer> fileIdxs) {
		this.fileIdxs = fileIdxs;
	}

	public void setFileIdx(Integer fileIdx) {
		this.fileIdx = fileIdx;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Files [originalFileName=" + originalFileName + ", savedFileName=" + savedFileName + ", masterIdx="
				+ masterIdx + ", fileIdx=" + fileIdx + ", type=" + type + ", fileIdxs=" + fileIdxs + "]";
	}
	
}
