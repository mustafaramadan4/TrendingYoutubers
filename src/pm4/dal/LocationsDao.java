package pm4.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import pm4.model.*;

public class LocationsDao {
	
protected ConnectionManager connectionManager;
	
	protected static LocationsDao instance = null;
	
	protected LocationsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static LocationsDao getInstance() {
		if (instance == null) {
			instance = new LocationsDao();
		}
		return instance;
	}

	
	public Locations create(Locations location) throws SQLException {
		String insertLocation =
			"INSERT INTO Locations(Location) " +
			"VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLocation,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, location.getLocation());
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int LocationId = -1;
			if(resultKey.next()) {
				LocationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			location.setLocationId(LocationId);;
			return location;
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
	 * Update the location of the Locations instance.
	 * This runs a UPDATE statement.
	 */
	public Locations updateLocation(Locations location, String newLocation) throws SQLException {
		String updateLocation = "UPDATE Locations SET Location=? WHERE LocationId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLocation);
			updateStmt.setString(1, newLocation);
			updateStmt.setInt(2, location.getLocationId());

			updateStmt.executeUpdate();
			location.setLocation(newLocation);

			return location;
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
	
	public Locations getLocationFromLocations(String location) throws SQLException {
		String selectLocation = 
				"SELECT LocationId,Location " +
						"FROM Locations " +
						"WHERE Location=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setString(1, location);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultLocationId = results.getInt("LocationId");
				String resultLocation = results.getString("Location");
				
				Locations oneLocation = new Locations(resultLocationId, resultLocation);
				
				return oneLocation;
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
	
	
	
	public Locations delete(Locations location) throws SQLException {
		String deleteLocations = 
				"DELETE FROM Locations WHERE location=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLocations);
			deleteStmt.setString(1, location.getLocation());
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
