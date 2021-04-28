package pm4.model;

public class VideosChannels{
	
	protected int videosChannelsId;
	protected int videoId;
	protected int channelId;
	public VideosChannels(int videosChannelsId, int videoId, int channelId) {
		
		this.videosChannelsId = videosChannelsId;
		this.videoId = videoId;
		this.channelId = channelId;
	}
	public VideosChannels(int videoId, int channelId) {
		
		this.videoId = videoId;
		this.channelId = channelId;
	}
	public int getVideosChannelsId() {
		return videosChannelsId;
	}
	public void setVideosChannelsId(int videosChannelsId) {
		this.videosChannelsId = videosChannelsId;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	
	
	
	
	
	
}
