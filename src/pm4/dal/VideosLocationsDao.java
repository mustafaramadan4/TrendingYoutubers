package pm4.dal;

import pm4.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class VideosLocationsDao {
	protected ConnectionManager connectionManager;

	private static VideosLocationsDao instance = null;
	protected VideosLocationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static VideosLocationsDao getInstance() {
		if(instance == null) {
			instance = new VideosLocationsDao();
		}
		return instance;
	}

	public VideosLocations create(VideosLocations videosLocation) throws SQLException {
		String insertVideosLocation =
			"INSERT INTO VideosLocations(VideoId,LocationId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertVideosLocation,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, videosLocation.getVideoId());
			insertStmt.setInt(2, videosLocation.getLocationId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int videosLocationId = -1;
			if(resultKey.next()) {
				videosLocationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			videosLocation.setVideosLocationsId(videosLocationId);
			return videosLocation;
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
	public VideosLocations delete(VideosLocations videosLocation) throws SQLException {
		String deleteReshare = "DELETE FROM VideosLocations WHERE VideosLocationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReshare);
			deleteStmt.setInt(1, videosLocation.getVideosLocationsId());
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
	

	
	public List<String> getVideosLocationsFromVideo(String title)
			throws SQLException {
		List<String> videosLocations = new ArrayList<String>();
		String selectVideosLocations =
				"SELECT Location FROM "
						+ "    (SELECT "
						+ "        Title, LocationId"
						+ "    FROM VideosLocations INNER JOIN Videos ON VideosLocations.VideoId = Videos.VideoId "
						+ "    WHERE Title = ?) AS T1 INNER JOIN Locations ON T1.LocationId = Locations.LocationId;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVideosLocations);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultLocations = results.getString("Location");

			
				videosLocations.add(resultLocations);
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
		return videosLocations;
	}
	
	
	public List<String> getVideosLocationsFromLocation(String location)
			throws SQLException {
		List<String> videosLocations = new ArrayList<String>();
		String selectVideosLocations =
				"SELECT Title FROM "
						+ "    (SELECT "
						+ "        VideoId, Location"
						+ "    FROM VideosLocations INNER JOIN Locations ON VideosLocations.LocationId = Locations.LocationId "
						+ "    WHERE Location = ?) AS T1 INNER JOIN Videos ON T1.VideoId = Videos.VideoId;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVideosLocations);
			selectStmt.setString(1, location);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultLocations = results.getString("Title");

			
				videosLocations.add(resultLocations);
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
		return videosLocations;
	}
	
}


