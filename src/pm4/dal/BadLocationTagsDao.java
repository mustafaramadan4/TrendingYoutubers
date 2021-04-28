package pm4.dal;

import pm4.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class BadLocationTagsDao {
	protected ConnectionManager connectionManager;

	private static BadLocationTagsDao instance = null;
	protected BadLocationTagsDao() {
		connectionManager = new ConnectionManager();
	}
	public static BadLocationTagsDao getInstance() {
		if(instance == null) {
			instance = new BadLocationTagsDao();
		}
		return instance;
	}

	public BadLocationTags create(BadLocationTags badLocationTag) throws SQLException {
		String insertBadLocationTag =
			"INSERT INTO BadLocationTags(LocationId,TagId,Likes,Dislikes,DislikePercentage) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBadLocationTag,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, badLocationTag.getLocationId());
			insertStmt.setInt(2, badLocationTag.getTagId());
			insertStmt.setInt(3, badLocationTag.getLikes());
			insertStmt.setInt(4, badLocationTag.getDislikes());
			insertStmt.setBigDecimal(5, badLocationTag.getDislikePercentage());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int badLocationTagId = -1;
			if(resultKey.next()) {
				badLocationTagId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			badLocationTag.setBadLocationTagId(badLocationTagId);
			return badLocationTag;
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
	public BadLocationTags delete(BadLocationTags badLocationTag) throws SQLException {
		String deleteReshare = "DELETE FROM BadLocationTags WHERE BadLocationTagId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReshare);
			deleteStmt.setInt(1, badLocationTag.getBadLocationTagId());
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
	
	public BadLocationTags updateLike(BadLocationTags badLocationTag, int newLike) throws SQLException {
		String updateView = "UPDATE BadLocationTags SET Likes=? WHERE BadLocationTagId=?;";
		String updateView2 = "UPDATE BadLocationTags SET BadLocationTags.DislikePercentage = BadLocationTags.Dislikes / (BadLocationTags.Dislikes+ BadLocationTags.Likes)*100 WHERE BadLocationTagId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt2 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newLike);
			updateStmt.setInt(2, badLocationTag.getBadLocationTagId());

			updateStmt.executeUpdate();
			
			updateStmt2 = connection.prepareStatement(updateView2);
			updateStmt2.setInt(1, badLocationTag.getBadLocationTagId());
			updateStmt2.executeUpdate();
			
			
			badLocationTag.setLikes(newLike);

			return badLocationTag;
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
	
	public BadLocationTags updateDislike(BadLocationTags badLocationTag, int newDislike) throws SQLException {
		String updateView = "UPDATE BadLocationTags SET Dislikes=? WHERE BadLocationTagId=?;";
		String updateView2 = "UPDATE BadLocationTags SET BadLocationTags.DislikePercentage = BadLocationTags.Dislikes / (BadLocationTags.Dislikes+ BadLocationTags.Likes)*100 WHERE BadLocationTagId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt2 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newDislike);
			updateStmt.setInt(2, badLocationTag.getBadLocationTagId());

			updateStmt.executeUpdate();
			
			updateStmt2 = connection.prepareStatement(updateView2);
			updateStmt2.setInt(1, badLocationTag.getBadLocationTagId());
			updateStmt2.executeUpdate();
			
			
			badLocationTag.setLikes(newDislike);

			return badLocationTag;
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

	
	public List<BadLocationTags> getBadLocationTagsForTag(String tag) throws SQLException {
		List<BadLocationTags> badLocationTags = new ArrayList<BadLocationTags>();
		String selectBadLocationTags =
			"SELECT BadLocationTagId,LocationId,BadLocationTags.TagId AS TagId,"
			+ "BadLocationTags.Likes AS Likes,BadLocationTags.Dislikes AS DisLikes,BadLocationTags.DislikePercentage AS DislikePercentage"
			+ " FROM BadLocationTags INNER JOIN Tags ON BadLocationTags.TagId = Tags.TagId"
			+ " WHERE Tags.Tag = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBadLocationTags);
			selectStmt.setString(1, tag);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int badLocationTagId = results.getInt("BadLocationTagId");
				int locationId = results.getInt("LocationId");
				int tagId = results.getInt("TagId");
				int likes = results.getInt("Likes");
				int disLikes = results.getInt("DisLikes");
				BigDecimal resultDislikePercentage = results.getBigDecimal("DislikePercentage");
				
				BadLocationTags badLocationTag = new BadLocationTags(
						badLocationTagId, locationId, tagId,likes,disLikes,resultDislikePercentage);
				badLocationTags.add(badLocationTag);
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
		return badLocationTags;
	}
	
	
	public List<BadLocationTags> getBadLocationTagsForLocation(String location) throws SQLException {
		List<BadLocationTags> badLocationTags = new ArrayList<BadLocationTags>();
		String selectBadLocationTags =
			"SELECT BadLocationTagId,BadLocationTags.LocationId AS LocationId,,BadLocationTags.TagId AS TagId,"
			+ "BadLocationTags.Likes AS Likes,BadLocationTags.Dislikes AS DisLikes,BadLocationTags.DislikePercentage AS DislikePercentage"
			+ " FROM BadLocationTags INNER JOIN Locations ON BadLocationTags.LocationId = Locations.LocationId"
			+ " WHERE Locations.Location = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBadLocationTags);
			selectStmt.setString(1, location);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int badLocationTagId = results.getInt("BadLocationTagId");
				int locationId = results.getInt("LocationId");
				int tagId = results.getInt("TagId");
				int likes = results.getInt("Likes");
				int disLikes = results.getInt("DisLikes");
				BigDecimal resultDislikePercentage = results.getBigDecimal("DislikePercentage");
				
				BadLocationTags badLocationTag = new BadLocationTags(
						badLocationTagId, locationId, tagId,likes,disLikes,resultDislikePercentage);
				badLocationTags.add(badLocationTag);
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
		return badLocationTags;
	}
}



