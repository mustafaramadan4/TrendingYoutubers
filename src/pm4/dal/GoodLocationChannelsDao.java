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



public class GoodLocationChannelsDao {
	protected ConnectionManager connectionManager;

	private static GoodLocationChannelsDao instance = null;
	protected GoodLocationChannelsDao() {
		connectionManager = new ConnectionManager();
	}
	public static GoodLocationChannelsDao getInstance() {
		if(instance == null) {
			instance = new GoodLocationChannelsDao();
		}
		return instance;
	}

	public GoodLocationChannels create(GoodLocationChannels goodLocationChannel) throws SQLException {
		String insertGoodLocationChannel =
			"INSERT INTO GoodLocationChannels(LocationId,ChannelId,Likes,Dislikes,LikePercentage) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGoodLocationChannel,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, goodLocationChannel.getLocationId());
			insertStmt.setInt(2, goodLocationChannel.getChannelId());
			insertStmt.setInt(3, goodLocationChannel.getLikes());
			insertStmt.setInt(4, goodLocationChannel.getDislikes());
			insertStmt.setBigDecimal(5, goodLocationChannel.getLikePercentage());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int goodLocationChannelId = -1;
			if(resultKey.next()) {
				goodLocationChannelId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			goodLocationChannel.setGoodLocationChannelId(goodLocationChannelId);
			return goodLocationChannel;
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
	public GoodLocationChannels delete(GoodLocationChannels goodLocationChannel) throws SQLException {
		String deleteReshare = "DELETE FROM GoodLocationChannels WHERE GoodLocationChannelId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReshare);
			deleteStmt.setInt(1, goodLocationChannel.getGoodLocationChannelId());
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
	
	public GoodLocationChannels updateLike(GoodLocationChannels goodLocationChannel, int newLike) throws SQLException {
		String updateView = "UPDATE GoodLocationChannels SET Likes=? WHERE GoodLocationChannelId=?;";
		String updateView2 = "UPDATE GoodLocationChannels SET GoodLocationChannels.LikePercentage = GoodLocationChannels.Likes / (GoodLocationChannels.Dislikes+ GoodLocationChannels.Likes)*100 WHERE GoodLocationChannelId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt2 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newLike);
			updateStmt.setInt(2, goodLocationChannel.getGoodLocationChannelId());

			updateStmt.executeUpdate();
			
			updateStmt2 = connection.prepareStatement(updateView2);
			updateStmt2.setInt(1, goodLocationChannel.getGoodLocationChannelId());
			updateStmt2.executeUpdate();
			
			
			goodLocationChannel.setLikes(newLike);

			return goodLocationChannel;
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
	
	public GoodLocationChannels updateDislike(GoodLocationChannels goodLocationChannel, int newDislike) throws SQLException {
		String updateView = "UPDATE GoodLocationChannels SET Dislikes=? WHERE GoodLocationChannelId=?;";
		String updateView2 = "UPDATE GoodLocationChannels SET GoodLocationChannels.LikePercentage = GoodLocationChannels.Likes / (GoodLocationChannels.Dislikes+ GoodLocationChannels.Likes)*100 WHERE GoodLocationChannelId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt2 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newDislike);
			updateStmt.setInt(2, goodLocationChannel.getGoodLocationChannelId());

			updateStmt.executeUpdate();
			
			updateStmt2 = connection.prepareStatement(updateView2);
			updateStmt2.setInt(1, goodLocationChannel.getGoodLocationChannelId());
			updateStmt2.executeUpdate();
			
			
			goodLocationChannel.setLikes(newDislike);

			return goodLocationChannel;
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

	
	public List<GoodLocationChannels> getGoodLocationChannelsForChannel(String channel) throws SQLException {
		List<GoodLocationChannels> goodLocationChannels = new ArrayList<GoodLocationChannels>();
		String selectGoodLocationChannels =
			"SELECT GoodLocationChannelId,LocationId,GoodLocationChannels.ChannelId AS ChannelId,"
			+ "GoodLocationChannels.Likes AS Likes,GoodLocationChannels.Dislikes AS DisLikes,GoodLocationChannels.LikePercentage AS LikePercentage"
			+ " FROM GoodLocationChannels INNER JOIN Channels ON GoodLocationChannels.ChannelId = Channels.ChannelId"
			+ " WHERE Channels.Channel = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGoodLocationChannels);
			selectStmt.setString(1, channel);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int goodLocationChannelId = results.getInt("GoodLocationChannelId");
				int locationId = results.getInt("LocationId");
				int channelId = results.getInt("ChannelId");
				int likes = results.getInt("Likes");
				int disLikes = results.getInt("DisLikes");
				BigDecimal resultLikePercentage = results.getBigDecimal("LikePercentage");
				
				GoodLocationChannels goodLocationChannel = new GoodLocationChannels(
						goodLocationChannelId, locationId, channelId,likes,disLikes,resultLikePercentage);
				goodLocationChannels.add(goodLocationChannel);
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
		return goodLocationChannels;
	}
	
	
	public List<GoodLocationChannels> getGoodLocationChannelsForLocation(String location) throws SQLException {
		List<GoodLocationChannels> goodLocationChannels = new ArrayList<GoodLocationChannels>();
		String selectGoodLocationChannels =
			"SELECT GoodLocationChannelId,GoodLocationChannels.LocationId AS LocationId,,GoodLocationChannels.ChannelId AS ChannelId,"
			+ "GoodLocationChannels.Likes AS Likes,GoodLocationChannels.Dislikes AS DisLikes,GoodLocationChannels.LikePercentage AS LikePercentage"
			+ " FROM GoodLocationChannels INNER JOIN Locations ON GoodLocationChannels.LocationId = Locations.LocationId"
			+ " WHERE Locations.Location = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGoodLocationChannels);
			selectStmt.setString(1, location);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int goodLocationChannelId = results.getInt("GoodLocationChannelId");
				int locationId = results.getInt("LocationId");
				int channelId = results.getInt("ChannelId");
				int likes = results.getInt("Likes");
				int disLikes = results.getInt("DisLikes");
				BigDecimal resultLikePercentage = results.getBigDecimal("LikePercentage");
				
				GoodLocationChannels goodLocationChannel = new GoodLocationChannels(
						goodLocationChannelId, locationId, channelId,likes,disLikes,resultLikePercentage);
				goodLocationChannels.add(goodLocationChannel);
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
		return goodLocationChannels;
	}
}

