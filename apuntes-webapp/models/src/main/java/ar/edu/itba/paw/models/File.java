package ar.edu.itba.paw.models;

public class File {
	private long fileid;
	private String name;
	// TODO
	// private User user;
	private long userid;
	private byte[] data;
	
	public File(long fileid, String name, long userid, byte[] data) {
		this.fileid = fileid;
		this.name = name;
		this.userid = userid;
		this.data = data;
	}
	
	public long getFileid() {
		return fileid;
	}
	public void setFileid(long fileid) {
		this.fileid = fileid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}

}
