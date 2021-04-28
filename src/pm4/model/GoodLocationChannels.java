package pm4.model;

import java.math.BigDecimal;

public class GoodLocationChannels {
	protected int goodLocationChannelId;
	protected int locationId;
	protected int channelId;
	protected int likes;
	protected int dislikes;
	protected BigDecimal likePercentage;
	public GoodLocationChannels(int goodLocationChannelId, int locationId, int channelId, int likes, int dislikes,
			BigDecimal likePercentage) {
		
		this.goodLocationChannelId = goodLocationChannelId;
		this.locationId = locationId;
		this.channelId = channelId;
		this.likes = likes;
		this.dislikes = dislikes;
		this.likePercentage = likePercentage;
	}
	public GoodLocationChannels(int locationId, int channelId, int likes, int dislikes, BigDecimal likePercentage) {
		
		this.locationId = locationId;
		this.channelId = channelId;
		this.likes = likes;
		this.dislikes = dislikes;
		this.likePercentage = likePercentage;
	}
	public int getGoodLocationChannelId() {
		return goodLocationChannelId;
	}
	public void setGoodLocationChannelId(int goodLocationChannelId) {
		this.goodLocationChannelId = goodLocationChannelId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
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
	
	
	
}

