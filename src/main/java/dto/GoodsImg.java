package dto;

public class GoodsImg {
	private int goodsCode;
	private String filename;
	private String originName;
	private String contentType;
	private Long filesize;
	private String createdate;
	@Override
	public String toString() {
		return "GoodsImg [goodsCode=" + goodsCode + ", filename=" + filename + ", originName=" + originName
				+ ", contentType=" + contentType + ", filesize=" + filesize + ", createdate=" + createdate + "]";
	}
	public int getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getFilesize() {
		return filesize;
	}
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
}
