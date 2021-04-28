package pm4.model;

import java.math.BigDecimal;

public class BadLocationChannels {
	protected int badLocationChannelId;
	protected int locationId;
	protected int channelId;
	protected int likes;
	protected int dislikes;
	protected BigDecimal dislikePercentage;
	public BadLocationChannels(int badLocationChannelId, int locationId, int channelId, int likes, int dislikes,
			BigDecimal dislikePercentage) {
		
		this.badLocationChannelId = badLocationChannelId;
		this.locationId = locationId;
		this.channelId = channelId;
		this.likes = likes;
		this.dislikes = dislikes;
		this.dislikePercentage = dislikePercentage;
	}
	public BadLocationChannels(int locationId, int channelId, int likes, int dislikes, BigDecimal dislikePercentage) {
		
		this.locationId = locationId;
		this.channelId = channelId;
		this.likes = likes;
		this.dislikes = dislikes;
		this.dislikePercentage = dislikePercentage;
	}
	public int getBadLocationChannelId() {
		return badLocationChannelId;
	}
	public void setBadLocationChannelId(int badLocationChannelId) {
		this.badLocationChannelId = badLocationChannelId;
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
	public BigDecimal getDislikePercentage() {
		return dislikePercentage;
	}
	public void setDislikePercentage(BigDecimal dislikePercentage) {
		this.dislikePercentage = dislikePercentage;
	}
	
	
	
	
}

