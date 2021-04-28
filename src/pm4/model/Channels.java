package pm4.model;

import java.math.BigDecimal;

public class Channels {
	protected int channelId;
	protected String channel;
	protected int likes;
	protected int dislikes;
	protected BigDecimal likePercentage;
	protected int views;
	protected int commentCount;
	
	// constructor 1: full constructor
	public Channels(int channelId, String channel, int likes, int dislikes, BigDecimal likePercentage, int views,
			int commentCount) {
		super();
		this.channelId = channelId;
		this.channel = channel;
		this.likes = likes;
		this.dislikes = dislikes;
		this.likePercentage = likePercentage;
		this.views = views;
		this.commentCount = commentCount;
	}
	
	
	// constructor 2: For primary key
	public Channels(int channelId) {
		super();
		this.channelId = channelId;
	}



	// constructor 3: without primary key
	public Channels(String channel, int likes, int dislikes, BigDecimal likePercentage, int views, int commentCount) {
		super();
		this.channel = channel;
		this.likes = likes;
		this.dislikes = dislikes;
		this.likePercentage = likePercentage;
		this.views = views;
		this.commentCount = commentCount;
	}


	public int getChannelId() {
		return channelId;
	}


	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}


	public String getChannel() {
		return channel;
	}


	public void setChannel(String channel) {
		this.channel = channel;
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
