package uber.dto.notice;

import java.sql.Date;

public class Notice {
	
	private Integer idx;
	private String title;
	private String content;
	private String regId;
	private Date regDate;
	private String uptId;
	private Date uptDate;
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getUptId() {
		return uptId;
	}
	public void setUptId(String uptId) {
		this.uptId = uptId;
	}
	public Date getUptDate() {
		return uptDate;
	}
	public void setUptDate(Date uptDate) {
		this.uptDate = uptDate;
	}
	
	@Override
	public String toString() {
		return "Notice [idx=" + idx + ", title=" + title + ", content=" + content + ", regId=" + regId + ", regDate="
				+ regDate + ", uptId=" + uptId + ", uptDate=" + uptDate + "]";
	}
	
}
