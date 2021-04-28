package pm4.tools;


import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.util.List;

import pm4.dal.*;
import pm4.model.*;


public class Inserter {
	
	public static void main(String[] args) throws SQLException {
		
		
		VideosDao videosDao = VideosDao.getInstance();
		
		
		TagsDao tagsDao = TagsDao.getInstance();
		
		LocationsDao locationsDao = LocationsDao.getInstance();
		
		ChannelsDao channelsDao = ChannelsDao.getInstance();
		
		VideosTagsDao videosTagsDao = VideosTagsDao.getInstance();
		
		VideosChannelsDao videosChannelsDao = VideosChannelsDao.getInstance();
		
		VideosLocationsDao videosLocationsDao = VideosLocationsDao.getInstance();
		
		GoodLocationChannelsDao goodLocationChannelsDao = GoodLocationChannelsDao.getInstance();
		
		BadLocationChannelsDao badLocationChannelsDao = BadLocationChannelsDao.getInstance();
		
		GoodLocationTagsDao goodLocationTagsDao = GoodLocationTagsDao.getInstance();
		
		BadLocationTagsDao badLocationTagsDao = BadLocationTagsDao.getInstance();
		
		//CREATE
		
		String test="test7";
		Videos testVideo=new Videos(test,0,0,0,0,"","");
		videosDao.create(testVideo);
		System.out.println("Create Video Success");
		System.out.println();
		
		BigDecimal b = new BigDecimal(10.5, MathContext.DECIMAL64);
		Tags testTag=new Tags("test1",1,2,b,3,4);
		tagsDao.create(testTag);
		System.out.println("Create Tag Success");
		System.out.println();
		
		
		Locations testLocation=new Locations(10,"testLocation");
		locationsDao.create(testLocation);
		System.out.println("Create Location Success");
		System.out.println();
		
		BigDecimal b2 = new BigDecimal(10.5, MathContext.DECIMAL64);
		Channels testChannel=new Channels("testChannel",1,2,b2,3,4);
		channelsDao.create(testChannel);
		System.out.println("Create Channel Success");
		System.out.println();
		
		VideosTags testVideosTags=new VideosTags(100000,1,1);
		videosTagsDao.create(testVideosTags);
		System.out.println("Create VideosTags Success");
		System.out.println();
		
		
		VideosChannels testVideosChannels=new VideosChannels(100000,1,1);
		videosChannelsDao.create(testVideosChannels);
		System.out.println("Create VideosChannels Success");
		System.out.println();
		
		VideosLocations testVideosLocations=new VideosLocations(10,1,1);
		videosLocationsDao.create(testVideosLocations);
		System.out.println("Create VideosLocations Success");
		System.out.println();
		
		BigDecimal b3 = new BigDecimal(10.5, MathContext.DECIMAL64);
		GoodLocationChannels testGoodLocationChannels=new GoodLocationChannels(1,10,1,2,b3);
		goodLocationChannelsDao.create(testGoodLocationChannels);
		System.out.println("Create GoodLocationChannels Success");
		System.out.println();
		
		
		BigDecimal b4 = new BigDecimal(10.5, MathContext.DECIMAL64);
		BadLocationChannels testBadLocationChannels=new BadLocationChannels(1,11,1,2,b4);
		badLocationChannelsDao.create(testBadLocationChannels);
		System.out.println("Create BadLocationChannels Success");
		System.out.println();
		
//		BigDecimal b5 = new BigDecimal(10.5, MathContext.DECIMAL64);
//		GoodLocationTags testGoodLocationTags=new GoodLocationTags(1000000,1,11,1,1,b5);
//		goodLocationTagsDao.create(testGoodLocationTags);
//		System.out.println("Create GoodLocationTags Success");
//		System.out.println();
		
		
		//READ
		
		
		Videos v1 = videosDao.getVideoFromTitle("Secrets of Dairy: Larson Dairy Farm");
		
		Videos v2 = videosDao.getVideoFromTitle("test7");
		
		System.out.format("Reading Video: v:%s t:%s "
				+ "v:%s l:%s "
				+ "d:%s c:%s "
				+ "l:%s d:%s\n",
				v1.getVideoId(),v1.getTitle(),
				v1.getViews(),v1.getLikes(),
				v1.getDislikes(),v1.getCommentCount(),
				v1.getLink(),v1.getDescription()
				);
		
		System.out.println("Read Videos Success");
		System.out.println();
		
		Tags t1= tagsDao.getTagFromTags("test1");
		
		System.out.format("Reading Tag: t:%s t:%s l:%s d:%s l:%s v:%s "
				+ "c:%s\n",
				t1.getTagId(),t1.getTag(),t1.getLikes(),t1.getDislikes(),
				t1.getLikePercentage(),t1.getViews(),t1.getCommentCount()
				);
		
		System.out.println("Read Tags Success");
		System.out.println();
		
		Locations l1=locationsDao.getLocationFromLocations("testLocation");
		System.out.format("Reading Location: l:%s "
				+ "l:%s\n",
				l1.getLocationId(),l1.getLocation()
				);
		
		System.out.println("Read Locations Success");
		System.out.println();
		
		Channels ch1= channelsDao.getChannelFromChannels("testChannel");
		
		System.out.format("Reading Channel: c:%s c:%s l:%s d:%s l:%s v:%s "
				+ "c:%s\n",
				ch1.getChannelId(),ch1.getChannel(),ch1.getLikes(),ch1.getDislikes(),
				ch1.getLikePercentage(),ch1.getViews(),ch1.getCommentCount()
				);
		
		System.out.println("Read Channels Success");
		System.out.println();
		
		
		
		List<String> tList1 = videosTagsDao.getVideosTagsFromVideo("21 Savage - Bank Account (Official Music Video)");
		for(String t : tList1) {
			System.out.format("Looping Tags For Video "+"21 Savage - Bank Account (Official Music Video)"+": t:%s  \n",
				t);
		}
		
		List<String> tList2 = videosTagsDao.getVideosTagsFromTag("Rap");
		for(String t : tList2) {
			System.out.format("Looping Tags For Video: t:%s "+"with tag"+" Rap"+" \n",
				t);
		}
		
		System.out.println("Read Tags For Video Success");
		System.out.println();
		
		
		List<String> cList1 = videosChannelsDao.getVideosChannelsFromVideo("21 Savage - Bank Account (Official Music Video)");
		for(String c : cList1) {
			System.out.format("Looping Channels For Video "+"21 Savage - Bank Account (Official Music Video)"+": c:%s  \n",
				c);
		}
		
		List<String> cList2 = videosChannelsDao.getVideosChannelsFromChannel("Sugar Pine 7");
		for(String c : cList2) {
			System.out.format("Looping Channels For Video: t:%s "+"with channel"+" Sugar Pine 7"+" \n",
				c);
		}
		
		System.out.println("Read Channels For Video Success");
		System.out.println();
		
		List<String> loList1 = videosLocationsDao.getVideosLocationsFromVideo("21 Savage - Bank Account (Official Music Video)");
		for(String lo : loList1) {
			System.out.format("Looping Locations For Video "+"21 Savage - Bank Account (Official Music Video)"+": l:%s  \n",
				lo);
		}
//		
//		List<String> loList2 = videosLocationsDao.getVideosLocationsFromLocation("CA");
//		for(String lo : loList2) {
//			System.out.format("Looping Locations For Video: t:%s "+"with Location"+" CA"+" \n",
//				lo);
//		}
//		
		System.out.println("Read Locations For Video Success");
		System.out.println();
		
		
		List<GoodLocationChannels> glcList1 = goodLocationChannelsDao.getGoodLocationChannelsForChannel("Sugar Pine 7");
		for(GoodLocationChannels glc : glcList1) {
			System.out.format("Looping GoodLocationChannels: g:%s l:%s c:%s l:%s d:%s lp:%s "
					+ "\n",
					glc.getGoodLocationChannelId(),glc.getLocationId(),glc.getChannelId(),glc.getLikes(),
					glc.getDislikes(),glc.getLikePercentage()
					);
		}
		
		System.out.println("Read GoodLocationChannels For Video Success");
		System.out.println();
		
		List<BadLocationChannels> blcList1 = badLocationChannelsDao.getBadLocationChannelsForChannel("AGAKHAN EXPOSE");
		for(BadLocationChannels blc : blcList1) {
			System.out.format("Looping BadLocationChannels: d:%s l:%s c:%s l:%s d:%s dp:%s "
					+ "\n",
					blc.getBadLocationChannelId(),blc.getLocationId(),blc.getChannelId(),blc.getLikes(),
					blc.getDislikes(),blc.getDislikePercentage()
					);
		}
		
		System.out.println("Read BadLocationChannels For Video Success");
		System.out.println();
		
		
		List<GoodLocationTags> gltList1 = goodLocationTagsDao.getGoodLocationTagsForTag("\\\"idubbbztv\\\"");
		for(GoodLocationTags glt : gltList1) {
			System.out.format("Looping GoodLocationTags: g:%s l:%s t:%s l:%s d:%s lp:%s "
					+ "\n",
					glt.getGoodLocationTagId(),glt.getLocationId(),glt.getTagId(),glt.getLikes(),
					glt.getDislikes(),glt.getLikePercentage()
					);
		}
		
		System.out.println("Read GoodLocationTags For Video Success");
		System.out.println();
		
		
		List<BadLocationTags> bltList1 = badLocationTagsDao.getBadLocationTagsForTag(" Mueller\\'s Probe and Trump\\'s Lying\\\"");
		for(BadLocationTags blt : bltList1) {
			System.out.format("Looping BadLocationTags: d:%s l:%s t:%s l:%s d:%s dp:%s "
					+ "\n",
					blt.getBadLocationTagId(),blt.getLocationId(),blt.getTagId(),blt.getLikes(),
					blt.getDislikes(),blt.getDislikePercentage()
					);
		}
		
		System.out.println("Read BadLocationTags For Video Success");
		System.out.println();
		
		//UPDATE
		
		videosDao.updateViews(v1, v1.getViews()+1);
		System.out.println("Update Video Success");
		System.out.println();
		
		tagsDao.updateView(t1, t1.getViews()+1);
		System.out.println("Update Tag Success");
		System.out.println();
		
//		locationsDao.updateLocation(l1, "UpdateLocation");
//		System.out.println("Update Location Success");
//		System.out.println();
		
		channelsDao.updateView(ch1, ch1.getViews()+1);
		System.out.println("Update Channel Success");
		System.out.println();
		
		goodLocationChannelsDao.updateLike(testGoodLocationChannels, testGoodLocationChannels.getLikes()+1);
		System.out.println("Update GoodLocationChannel Success");
		System.out.println();
		
		badLocationChannelsDao.updateLike(testBadLocationChannels, testBadLocationChannels.getLikes()+1);
		System.out.println("Update BadLocationChannel Success");
		System.out.println();
		
//		goodLocationTagsDao.updateLike(testGoodLocationTags, testGoodLocationTags.getLikes()+1);
//		System.out.println("Update GoodLocationTag Success");
//		System.out.println();
		
		//DELETE
		
		videosDao.delete(v2);
		System.out.println("Delete Video Success");
		System.out.println();
		
		tagsDao.delete(t1);
		System.out.println("Delete Tag Success");
		System.out.println();
		
		locationsDao.delete(l1);
		System.out.println("Delete Location Success");
		System.out.println();
		
		channelsDao.delete(ch1);
		System.out.println("Delete Channel Success");
		System.out.println();
		
		videosTagsDao.delete(testVideosTags);
		System.out.println("Delete videosTags Success");
		System.out.println();
		
		videosChannelsDao.delete(testVideosChannels);
		System.out.println("Delete videosChannels Success");
		System.out.println();
		
		videosLocationsDao.delete(testVideosLocations);
		System.out.println("Delete videosLocations Success");
		System.out.println();
		
		goodLocationChannelsDao.delete(testGoodLocationChannels);
		System.out.println("Delete GoodLocationChannels Success");
		System.out.println();
		
		badLocationChannelsDao.delete(testBadLocationChannels);
		System.out.println("Delete BadLocationChannels Success");
		System.out.println();
		
		
//		goodLocationTagsDao.delete(testGoodLocationTags);
//		System.out.println("Delete GoodLocationTags Success");
//		System.out.println();
		
		
	}

}
