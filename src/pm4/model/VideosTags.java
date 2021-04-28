package pm4.model;

public class VideosTags {
	protected int videosTagsId;
	protected int videoId;
	protected int tagId;
	
	public VideosTags(int videosTagsId, int videoId, int tagId) {
		
		this.videosTagsId = videosTagsId;
		this.videoId = videoId;
		this.tagId = tagId;
	}

	public VideosTags(int videoId, int tagId) {
		
		this.videoId = videoId;
		this.tagId = tagId;
	}

	public int getVideosTagsId() {
		return videosTagsId;
	}

	public void setVideosTagsId(int videosTagsId) {
		this.videosTagsId = videosTagsId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	
	
	
}

