package pm4.model;

public class VideosLocations {
	protected int videosLocationsId;
	protected int videoId;
	protected int locationId;
	public VideosLocations(int videosLocationsId, int videoId, int locationId) {
		
		this.videosLocationsId = videosLocationsId;
		this.videoId = videoId;
		this.locationId = locationId;
	}
	public VideosLocations(int videoId, int locationId) {
		
		this.videoId = videoId;
		this.locationId = locationId;
	}
	public int getVideosLocationsId() {
		return videosLocationsId;
	}
	public void setVideosLocationsId(int videosLocationsId) {
		this.videosLocationsId = videosLocationsId;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	
	
	
	
}


