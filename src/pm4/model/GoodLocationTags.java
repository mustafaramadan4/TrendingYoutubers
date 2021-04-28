package pm4.model;

import java.math.BigDecimal;

public class GoodLocationTags {
	protected int goodLocationTagId;
	protected int locationId;
	protected int tagId;
	protected int likes;
	protected int dislikes;
	protected BigDecimal likePercentage;
	public GoodLocationTags(int goodLocationTagId, int locationId, int tagId, int likes, int dislikes,
			BigDecimal likePercentage) {
		
		this.goodLocationTagId = goodLocationTagId;
		this.locationId = locationId;
		this.tagId = tagId;
		this.likes = likes;
		this.dislikes = dislikes;
		this.likePercentage = likePercentage;
	}
	public GoodLocationTags(int locationId, int tagId, int likes, int dislikes, BigDecimal likePercentage) {
		
		this.locationId = locationId;
		this.tagId = tagId;
		this.likes = likes;
		this.dislikes = dislikes;
		this.likePercentage = likePercentage;
	}
	public int getGoodLocationTagId() {
		return goodLocationTagId;
	}
	public void setGoodLocationTagId(int goodLocationTagId) {
		this.goodLocationTagId = goodLocationTagId;
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
	public BigDecimal getLikePercentage() {
		return likePercentage;
	}
	public void setLikePercentage(BigDecimal likePercentage) {
		this.likePercentage = likePercentage;
	}
	
	
	
	
}

