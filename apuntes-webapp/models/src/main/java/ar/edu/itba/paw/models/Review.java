package ar.edu.itba.paw.models;

public class Review {

	private int reviewid;
	private int fileid;
	private int userid;
	private double ranking;
	private String review;
	
	public Review(int reviewid, int fileid, int userid, double ranking, String review) {
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
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
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
