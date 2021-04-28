package pm4.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import pm4.model.*;

public class ChannelsDao {
	
	protected ConnectionManager connectionManager;
	protected static ChannelsDao instance = null;
	
	protected ChannelsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ChannelsDao getInstance() {
		if (instance == null) {
			instance = new ChannelsDao();
		}
		return instance;
	}

	
	public Channels create(Channels channel) throws SQLException {
		String insertChannel =
			"INSERT INTO Channels(Channel,Likes,Dislikes,LikePercentage,Views,CommentCount) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertChannel,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, channel.getChannel());
			insertStmt.setInt(2, channel.getLikes());
			insertStmt.setInt(3, channel.getDislikes());
			insertStmt.setBigDecimal(4, channel.getLikePercentage());
			insertStmt.setInt(5, channel.getViews());
			insertStmt.setInt(6, channel.getCommentCount());
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
//			resultKey = insertStmt.getGeneratedKeys();
//			int TagId = -1;
//			if(resultKey.next()) {
//				TagId = resultKey.getInt(1);
//			} else {
//				throw new SQLException("Unable to retrieve auto-generated key.");
//			}
//			tag.setTagId(TagId);
			return channel;
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
	 * Update the tag of the Tags instance.
	 * This runs a UPDATE statement.
	 */
	public Channels updateView(Channels channel, int newView) throws SQLException {
		String updateView = "UPDATE Channels SET Views=? WHERE ChannelId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newView);
			updateStmt.setInt(2, channel.getChannelId());

			updateStmt.executeUpdate();
			channel.setViews(newView);

			return channel;
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
	
	public Channels getChannelFromChannels(String channel) throws SQLException {
		String selectChannel = 
				"SELECT ChannelId,Channel,Likes,Dislikes,LikePercentage,Views,CommentCount " +
						"FROM Channels " +
						"WHERE Channel=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectChannel);
			selectStmt.setString(1, channel);

			results = selectStmt.executeQuery();

			if(results.next()) {
				
				int resultChannelId = results.getInt("ChannelId");
				String resultChannel = results.getString("Channel");
				int resultLikes = results.getInt("Likes");
				int resultDislikes = results.getInt("Dislikes");
				BigDecimal resultLikePercentage = results.getBigDecimal("LikePercentage");
				int resultViews = results.getInt("Views");
				int resultCommentCount = results.getInt("CommentCount");
				
				Channels oneChannel = new Channels(resultChannelId, resultChannel, resultLikes, resultDislikes, 
						resultLikePercentage, resultViews, resultCommentCount);
				
				return oneChannel;
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
	
	
	
	public Channels delete(Channels channel) throws SQLException {
		String deleteChannels = 
				"DELETE FROM Channels WHERE Channel=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteChannels);
			deleteStmt.setString(1, channel.getChannel());
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
}

