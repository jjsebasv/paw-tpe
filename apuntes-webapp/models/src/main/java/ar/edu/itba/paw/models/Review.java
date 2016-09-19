package ar.edu.itba.paw.models;

public class Review {

	private int reviewid;
	private int fileid;
	private long userid;
	private double ranking;
	private String review;
	
	public Review(int reviewid, int fileid, long userid, double ranking, String review) {
		super();
		this.reviewid = reviewid;
		this.fileid = fileid;
		this.userid = userid;
		this.ranking = ranking;
		this.review = review;
	}
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	public double getRanking() {
		return ranking;
	}
	public void setRanking(double ranking) {
		this.ranking = ranking;
	}
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}	
}
