package pm4.dal;

import pm4.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class VideosTagsDao {
	protected ConnectionManager connectionManager;

	private static VideosTagsDao instance = null;
	protected VideosTagsDao() {
		connectionManager = new ConnectionManager();
	}
	public static VideosTagsDao getInstance() {
		if(instance == null) {
			instance = new VideosTagsDao();
		}
		return instance;
	}

	public VideosTags create(VideosTags videosTag) throws SQLException {
		String insertVideosTag =
			"INSERT INTO VideosTags(VideoId,TagId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertVideosTag,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, videosTag.getVideoId());
			insertStmt.setInt(2, videosTag.getTagId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int videosTagId = -1;
			if(resultKey.next()) {
				videosTagId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			videosTag.setVideosTagsId(videosTagId);
			return videosTag;
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

	/**
	 * 
	 * This runs a DELETE statement.
	 */
	public VideosTags delete(VideosTags videosTag) throws SQLException {
		String deleteReshare = "DELETE FROM VideosTags WHERE VideosTagId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReshare);
			deleteStmt.setInt(1, videosTag.getVideosTagsId());
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
	

	
	public List<String> getVideosTagsFromVideo(String title)
			throws SQLException {
		List<String> videosTags = new ArrayList<String>();
		String selectVideosTags =
				"SELECT Tag FROM "
						+ "    (SELECT "
						+ "        Title, TagId"
						+ "    FROM VideosTags INNER JOIN Videos ON VideosTags.VideoId = Videos.VideoId "
						+ "    WHERE Title = ?) AS T1 INNER JOIN Tags ON T1.TagId = Tags.TagId;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVideosTags);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultTags = results.getString("Tag");

			
				videosTags.add(resultTags);
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
		return videosTags;
	}
	
	
	public List<String> getVideosTagsFromTag(String tag)
			throws SQLException {
		List<String> videosTags = new ArrayList<String>();
		String selectVideosTags =
				"SELECT Title FROM "
						+ "    (SELECT "
						+ "        VideoId, Tag"
						+ "    FROM VideosTags INNER JOIN Tags ON VideosTags.TagId = Tags.TagId "
						+ "    WHERE Tag = ?) AS T1 INNER JOIN Videos ON T1.VideoId = Videos.VideoId;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVideosTags);
			selectStmt.setString(1, tag);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultTags = results.getString("Title");

			
				videosTags.add(resultTags);
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
		return videosTags;
	}
	
}

