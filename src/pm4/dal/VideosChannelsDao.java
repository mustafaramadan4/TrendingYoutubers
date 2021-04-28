package pm4.dal;

import pm4.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class VideosChannelsDao {
	protected ConnectionManager connectionManager;

	private static VideosChannelsDao instance = null;
	protected VideosChannelsDao() {
		connectionManager = new ConnectionManager();
	}
	public static VideosChannelsDao getInstance() {
		if(instance == null) {
			instance = new VideosChannelsDao();
		}
		return instance;
	}

	public VideosChannels create(VideosChannels videosChannel) throws SQLException {
		String insertVideosChannel =
			"INSERT INTO VideosChannels(VideoId,ChannelId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertVideosChannel,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, videosChannel.getVideoId());
			insertStmt.setInt(2, videosChannel.getChannelId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int videosChannelId = -1;
			if(resultKey.next()) {
				videosChannelId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			videosChannel.setVideosChannelsId(videosChannelId);
			return videosChannel;
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
	public VideosChannels delete(VideosChannels videosChannel) throws SQLException {
		String deleteReshare = "DELETE FROM VideosChannels WHERE VideosChannelId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReshare);
			deleteStmt.setInt(1, videosChannel.getVideosChannelsId());
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
	

	
	public List<String> getVideosChannelsFromVideo(String title)
			throws SQLException {
		List<String> videosChannels = new ArrayList<String>();
		String selectVideosChannels =
				"SELECT Channel FROM "
						+ "    (SELECT "
						+ "        Title, ChannelId"
						+ "    FROM VideosChannels INNER JOIN Videos ON VideosChannels.VideoId = Videos.VideoId "
						+ "    WHERE Title = ?) AS T1 INNER JOIN Channels ON T1.ChannelId = Channels.ChannelId;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVideosChannels);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultChannels = results.getString("Channel");

			
				videosChannels.add(resultChannels);
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
		return videosChannels;
	}
	
	
	public List<String> getVideosChannelsFromChannel(String channel)
			throws SQLException {
		List<String> videosChannels = new ArrayList<String>();
		String selectVideosChannels =
				"SELECT Title FROM "
						+ "    (SELECT "
						+ "        VideoId, Channel"
						+ "    FROM VideosChannels INNER JOIN Channels ON VideosChannels.ChannelId = Channels.ChannelId "
						+ "    WHERE Channel = ?) AS T1 INNER JOIN Videos ON T1.VideoId = Videos.VideoId;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVideosChannels);
			selectStmt.setString(1, channel);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultChannels = results.getString("Title");

			
				videosChannels.add(resultChannels);
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
		return videosChannels;
	}
	
}

