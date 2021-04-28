package pm4.dal;

import pm4.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;






public class VideosDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static VideosDao instance = null;
	protected VideosDao() {
		connectionManager = new ConnectionManager();
	}
	public static VideosDao getInstance() {
		if(instance == null) {
			instance = new VideosDao();
		}
		return instance;
	}


	public Videos create(Videos video) throws SQLException {
		String insertVideo = "INSERT INTO Videos(Title,Views,Likes,Dislikes,CommentCount,Link,Description) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertVideo);
			
			insertStmt.setString(1, video.getTitle());
			insertStmt.setInt(2, video.getViews());
			insertStmt.setInt(3, video.getLikes());
			insertStmt.setInt(4, video.getDislikes());
			insertStmt.setInt(5, video.getCommentCount());
			insertStmt.setString(6, video.getLink());
			insertStmt.setString(7, video.getDescription());
			
			insertStmt.executeUpdate();
			
//			resultKey = insertStmt.getGeneratedKeys();
//			int videoId = -1;
//			if(resultKey.next()) {
//				videoId = resultKey.getInt(1);
//			} else {
//				throw new SQLException("Unable to retrieve auto-generated key.");
//			}
//			video.setVideoId(videoId);
			return video;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	
	public Videos updateViews(Videos video, int newViews) throws SQLException {
		String updateViews = "UPDATE Videos SET Views=? WHERE Title=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateViews);
			updateStmt.setInt(1, newViews);
			updateStmt.setString(2, video.getTitle());
			updateStmt.executeUpdate();
			
			
			video.setViews(newViews);
			return video;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	
	
	public Videos delete(Videos video) throws SQLException {
		String deleteVideos = "DELETE FROM Videos WHERE Title=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteVideos);
			deleteStmt.setString(1, video.getTitle());
			deleteStmt.executeUpdate();

			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	
	public Videos getVideoFromTitle(String title) throws SQLException {
		String selectVideos = "SELECT VideoId,Title,Views,Likes,Dislikes,CommentCount,Link,Description FROM Videos WHERE Title=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVideos);
			selectStmt.setString(1, title);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				
				int videoId = results.getInt("VideoId");
				String resultTitle = results.getString("Title");
				int view = results.getInt("Views");
				int likes = results.getInt("Likes");
				int dislikes = results.getInt("Dislikes");
				int commentCount = results.getInt("CommentCount");
				String link = results.getString("Link");
				String description = results.getString("Description");
				
				
				
				
				Videos video = new Videos(videoId, resultTitle, view,likes,dislikes,commentCount,link,description);
				return video;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<Videos> getVideoFromTitles(String title) throws SQLException {
		List<Videos> videos = new ArrayList<Videos>();
		String selectVideos =
			"SELECT VideoId,Title,Views,Likes,Dislikes,CommentCount,Link,Description FROM Videos WHERE Title=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVideos);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int videoId = results.getInt("VideoId");
				String resultTitle = results.getString("Title");
				int view = results.getInt("Views");
				int likes = results.getInt("Likes");
				int dislikes = results.getInt("Dislikes");
				int commentCount = results.getInt("CommentCount");
				String link = results.getString("Link");
				String description = results.getString("Description");
				Videos video = new Videos(videoId, resultTitle, view,likes,dislikes,commentCount,link,description);
				videos.add(video);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return videos;
		
	}


}

