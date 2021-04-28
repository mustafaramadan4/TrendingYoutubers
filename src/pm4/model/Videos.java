package pm4.model;


public class Videos {
	protected int videoId;
	protected String title;
	protected int views;
	protected int likes;
	protected int dislikes;
	protected int commentCount;
	protected String link;
	protected String description;
	
	public Videos(int videoId, String title, int views, int likes, int dislikes, int commentCount, String link,
			String description) {
		
		this.videoId = videoId;
		this.title = title;
		this.views = views;
		this.likes = likes;
		this.dislikes = dislikes;
		this.commentCount = commentCount;
		this.link = link;
		this.description = description;
	}

	public Videos(String title, int views, int likes, int dislikes, int commentCount, String link, String description) {
		
		this.title = title;
		this.views = views;
		this.likes = likes;
		this.dislikes = dislikes;
		this.commentCount = commentCount;
		this.link = link;
		this.description = description;
	}

	public Videos(String title) {
		
		this.title = title;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
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

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
