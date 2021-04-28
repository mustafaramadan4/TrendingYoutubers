package pm4.model;

import java.math.BigDecimal;

public class BadLocationTags {
	protected int badLocationTagId;
	protected int locationId;
	protected int tagId;
	protected int likes;
	protected int dislikes;
	protected BigDecimal dislikePercentage;
	public BadLocationTags(int badLocationTagId, int locationId, int tagId, int likes, int dislikes,
			BigDecimal dislikePercentage) {
		
		this.badLocationTagId = badLocationTagId;
		this.locationId = locationId;
		this.tagId = tagId;
		this.likes = likes;
		this.dislikes = dislikes;
		this.dislikePercentage = dislikePercentage;
	}
	public BadLocationTags(int locationId, int tagId, int likes, int dislikes, BigDecimal dislikePercentage) {
	
		this.locationId = locationId;
		this.tagId = tagId;
		this.likes = likes;
		this.dislikes = dislikes;
		this.dislikePercentage = dislikePercentage;
	}
	public int getBadLocationTagId() {
		return badLocationTagId;
	}
	public void setBadLocationTagId(int badLocationTagId) {
		this.badLocationTagId = badLocationTagId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
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

