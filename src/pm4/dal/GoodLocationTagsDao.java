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



public class GoodLocationTagsDao {
	protected ConnectionManager connectionManager;

	private static GoodLocationTagsDao instance = null;
	protected GoodLocationTagsDao() {
		connectionManager = new ConnectionManager();
	}
	public static GoodLocationTagsDao getInstance() {
		if(instance == null) {
			instance = new GoodLocationTagsDao();
		}
		return instance;
	}

	public GoodLocationTags create(GoodLocationTags goodLocationTag) throws SQLException {
		String insertGoodLocationTag =
			"INSERT INTO GoodLocationChannels(LocationId,TagId,Likes,Dislikes,LikePercentage) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGoodLocationTag,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, goodLocationTag.getLocationId());
			insertStmt.setInt(2, goodLocationTag.getTagId());
			insertStmt.setInt(3, goodLocationTag.getLikes());
			insertStmt.setInt(4, goodLocationTag.getDislikes());
			insertStmt.setBigDecimal(5, goodLocationTag.getLikePercentage());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int goodLocationTagId = -1;
			if(resultKey.next()) {
				goodLocationTagId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			goodLocationTag.setGoodLocationTagId(goodLocationTagId);
			return goodLocationTag;
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
	public GoodLocationTags delete(GoodLocationTags goodLocationTag) throws SQLException {
		String deleteReshare = "DELETE FROM GoodLocationTags WHERE GoodLocationTagId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReshare);
			deleteStmt.setInt(1, goodLocationTag.getGoodLocationTagId());
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
	
	public GoodLocationTags updateLike(GoodLocationTags goodLocationTag, int newLike) throws SQLException {
		String updateView = "UPDATE GoodLocationTags SET Likes=? WHERE GoodLocationTagId=?;";
		String updateView2 = "UPDATE GoodLocationTags SET GoodLocationTags.LikePercentage = GoodLocationTags.Likes / (GoodLocationTags.Dislikes+ GoodLocationTags.Likes)*100 WHERE GoodLocationTagId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt2 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newLike);
			updateStmt.setInt(2, goodLocationTag.getGoodLocationTagId());

			updateStmt.executeUpdate();
			
			updateStmt2 = connection.prepareStatement(updateView2);
			updateStmt2.setInt(1, goodLocationTag.getGoodLocationTagId());
			updateStmt2.executeUpdate();
			
			
			goodLocationTag.setLikes(newLike);

			return goodLocationTag;
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
	
	public GoodLocationTags updateDislike(GoodLocationTags goodLocationTag, int newDislike) throws SQLException {
		String updateView = "UPDATE GoodLocationTags SET Dislikes=? WHERE GoodLocationTagId=?;";
		String updateView2 = "UPDATE GoodLocationTags SET GoodLocationTags.LikePercentage = GoodLocationTags.Likes / (GoodLocationTags.Dislikes+ GoodLocationTags.Likes)*100 WHERE GoodLocationTagId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt2 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newDislike);
			updateStmt.setInt(2, goodLocationTag.getGoodLocationTagId());

			updateStmt.executeUpdate();
			
			updateStmt2 = connection.prepareStatement(updateView2);
			updateStmt2.setInt(1, goodLocationTag.getGoodLocationTagId());
			updateStmt2.executeUpdate();
			
			
			goodLocationTag.setLikes(newDislike);

			return goodLocationTag;
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

	
	public List<GoodLocationTags> getGoodLocationTagsForTag(String tag) throws SQLException {
		List<GoodLocationTags> goodLocationTags = new ArrayList<GoodLocationTags>();
		String selectGoodLocationTags =
			"SELECT GoodLocationTagId,LocationId,GoodLocationTags.TagId AS TagId,"
			+ "GoodLocationTags.Likes AS Likes,GoodLocationTags.Dislikes AS DisLikes,GoodLocationTags.LikePercentage AS LikePercentage"
			+ " FROM GoodLocationTags INNER JOIN Tags ON GoodLocationTags.TagId = Tags.TagId"
			+ " WHERE Tags.Tag = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGoodLocationTags);
			selectStmt.setString(1, tag);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int goodLocationTagId = results.getInt("GoodLocationTagId");
				int locationId = results.getInt("LocationId");
				int tagId = results.getInt("TagId");
				int likes = results.getInt("Likes");
				int disLikes = results.getInt("DisLikes");
				BigDecimal resultLikePercentage = results.getBigDecimal("LikePercentage");
				
				GoodLocationTags goodLocationTag = new GoodLocationTags(
						goodLocationTagId, locationId, tagId,likes,disLikes,resultLikePercentage);
				goodLocationTags.add(goodLocationTag);
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
		return goodLocationTags;
	}
	
	
	public List<GoodLocationTags> getGoodLocationTagsForLocation(String location) throws SQLException {
		List<GoodLocationTags> goodLocationTags = new ArrayList<GoodLocationTags>();
		String selectGoodLocationTags =
			"SELECT GoodLocationTagId,GoodLocationTags.LocationId AS LocationId,,GoodLocationTags.TagId AS TagId,"
			+ "GoodLocationTags.Likes AS Likes,GoodLocationTags.Dislikes AS DisLikes,GoodLocationTags.LikePercentage AS LikePercentage"
			+ " FROM GoodLocationTags INNER JOIN Locations ON GoodLocationTags.LocationId = Locations.LocationId"
			+ " WHERE Locations.Location = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGoodLocationTags);
			selectStmt.setString(1, location);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int goodLocationTagId = results.getInt("GoodLocationTagId");
				int locationId = results.getInt("LocationId");
				int tagId = results.getInt("TagId");
				int likes = results.getInt("Likes");
				int disLikes = results.getInt("DisLikes");
				BigDecimal resultLikePercentage = results.getBigDecimal("LikePercentage");
				
				GoodLocationTags goodLocationTag = new GoodLocationTags(
						goodLocationTagId, locationId, tagId,likes,disLikes,resultLikePercentage);
				goodLocationTags.add(goodLocationTag);
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
		return goodLocationTags;
	}
}


