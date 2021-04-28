package pm4.model;

import java.math.BigDecimal;

public class Tags {
	protected int tagId;
	protected String tag;
	protected int likes;
	protected int dislikes;
	protected BigDecimal likePercentage;
	protected int views;
	protected int commentCount;
	
	// constructor 1: constructor with all files
	public Tags(int tagId, String tag, int likes, int dislikes, BigDecimal likePercentage, int views,
			int commentCount) {
		super();
		this.tagId = tagId;
		this.tag = tag;
		this.likes = likes;
		this.dislikes = dislikes;
		this.likePercentage = likePercentage;
		this.views = views;
		this.commentCount = commentCount;
	}
	
	// constructor 2: constructor for primary key
	public Tags(int tagId) {
		super();
		this.tagId = tagId;
	}
	
	// constructor 3: constructor without primary key
	public Tags(String tag, int likes, int dislikes, BigDecimal likePercentage, int views, int commentCount) {
		super();
		this.tag = tag;
		this.likes = likes;
		this.dislikes = dislikes;
		this.likePercentage = likePercentage;
		this.views = views;
		this.commentCount = commentCount;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public BigDecimal getLikePercentage() {
		return likePercentage;
	}

	public void setLikePercentage(BigDecimal likePercentage) {
		this.likePercentage = likePercentage;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	
	
}

