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



public class BadLocationChannelsDao {
	protected ConnectionManager connectionManager;

	private static BadLocationChannelsDao instance = null;
	protected BadLocationChannelsDao() {
		connectionManager = new ConnectionManager();
	}
	public static BadLocationChannelsDao getInstance() {
		if(instance == null) {
			instance = new BadLocationChannelsDao();
		}
		return instance;
	}

	public BadLocationChannels create(BadLocationChannels badLocationChannel) throws SQLException {
		String insertBadLocationChannel =
			"INSERT INTO BadLocationChannels(LocationId,ChannelId,Likes,Dislikes,DislikePercentage) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBadLocationChannel,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, badLocationChannel.getLocationId());
			insertStmt.setInt(2, badLocationChannel.getChannelId());
			insertStmt.setInt(3, badLocationChannel.getLikes());
			insertStmt.setInt(4, badLocationChannel.getDislikes());
			insertStmt.setBigDecimal(5, badLocationChannel.getDislikePercentage());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int badLocationChannelId = -1;
			if(resultKey.next()) {
				badLocationChannelId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			badLocationChannel.setBadLocationChannelId(badLocationChannelId);
			return badLocationChannel;
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
	public BadLocationChannels delete(BadLocationChannels badLocationChannel) throws SQLException {
		String deleteReshare = "DELETE FROM BadLocationChannels WHERE BadLocationChannelId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReshare);
			deleteStmt.setInt(1, badLocationChannel.getBadLocationChannelId());
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
	
	public BadLocationChannels updateLike(BadLocationChannels badLocationChannel, int newLike) throws SQLException {
		String updateView = "UPDATE BadLocationChannels SET Likes=? WHERE BadLocationChannelId=?;";
		String updateView2 = "UPDATE BadLocationChannels SET BadLocationChannels.DislikePercentage = BadLocationChannels.Dislikes / (BadLocationChannels.Dislikes+ BadLocationChannels.Likes)*100 WHERE BadLocationChannelId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt2 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newLike);
			updateStmt.setInt(2, badLocationChannel.getBadLocationChannelId());

			updateStmt.executeUpdate();
			
			updateStmt2 = connection.prepareStatement(updateView2);
			updateStmt2.setInt(1, badLocationChannel.getBadLocationChannelId());
			updateStmt2.executeUpdate();
			
			
			badLocationChannel.setLikes(newLike);

			return badLocationChannel;
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
	
	public BadLocationChannels updateDislike(BadLocationChannels badLocationChannel, int newDislike) throws SQLException {
		String updateView = "UPDATE BadLocationChannels SET Dislikes=? WHERE BadLocationChannelId=?;";
		String updateView2 = "UPDATE BadLocationChannels SET BadLocationChannels.DislikePercentage = BadLocationChannels.Dislikes / (BadLocationChannels.Dislikes+ BadLocationChannels.Likes)*100 WHERE BadLocationChannelId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt2 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newDislike);
			updateStmt.setInt(2, badLocationChannel.getBadLocationChannelId());

			updateStmt.executeUpdate();
			
			updateStmt2 = connection.prepareStatement(updateView2);
			updateStmt2.setInt(1, badLocationChannel.getBadLocationChannelId());
			updateStmt2.executeUpdate();
			
			
			badLocationChannel.setLikes(newDislike);

			return badLocationChannel;
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

	
	public List<BadLocationChannels> getBadLocationChannelsForChannel(String channel) throws SQLException {
		List<BadLocationChannels> badLocationChannels = new ArrayList<BadLocationChannels>();
		String selectBadLocationChannels =
			"SELECT BadLocationChannelId,LocationId,BadLocationChannels.ChannelId AS ChannelId,"
			+ "BadLocationChannels.Likes AS Likes,BadLocationChannels.Dislikes AS DisLikes,BadLocationChannels.DislikePercentage AS DislikePercentage"
			+ " FROM BadLocationChannels INNER JOIN Channels ON BadLocationChannels.ChannelId = Channels.ChannelId"
			+ " WHERE Channels.Channel = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBadLocationChannels);
			selectStmt.setString(1, channel);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int badLocationChannelId = results.getInt("BadLocationChannelId");
				int locationId = results.getInt("LocationId");
				int channelId = results.getInt("ChannelId");
				int likes = results.getInt("Likes");
				int disLikes = results.getInt("DisLikes");
				BigDecimal resultDislikePercentage = results.getBigDecimal("DislikePercentage");
				
				BadLocationChannels badLocationChannel = new BadLocationChannels(
						badLocationChannelId, locationId, channelId,likes,disLikes,resultDislikePercentage);
				badLocationChannels.add(badLocationChannel);
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
		return badLocationChannels;
	}
	
	
	public List<BadLocationChannels> getBadLocationChannelsForLocation(String location) throws SQLException {
		List<BadLocationChannels> badLocationChannels = new ArrayList<BadLocationChannels>();
		String selectBadLocationChannels =
			"SELECT BadLocationChannelId,BadLocationChannels.LocationId AS LocationId,,BadLocationChannels.ChannelId AS ChannelId,"
			+ "BadLocationChannels.Likes AS Likes,BadLocationChannels.Dislikes AS DisLikes,BadLocationChannels.DislikePercentage AS DislikePercentage"
			+ " FROM BadLocationChannels INNER JOIN Locations ON BadLocationChannels.LocationId = Locations.LocationId"
			+ " WHERE Locations.Location = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBadLocationChannels);
			selectStmt.setString(1, location);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int badLocationChannelId = results.getInt("BadLocationChannelId");
				int locationId = results.getInt("LocationId");
				int channelId = results.getInt("ChannelId");
				int likes = results.getInt("Likes");
				int disLikes = results.getInt("DisLikes");
				BigDecimal resultDislikePercentage = results.getBigDecimal("DislikePercentage");
				
				BadLocationChannels badLocationChannel = new BadLocationChannels(
						badLocationChannelId, locationId, channelId,likes,disLikes,resultDislikePercentage);
				badLocationChannels.add(badLocationChannel);
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
		return badLocationChannels;
	}
}


