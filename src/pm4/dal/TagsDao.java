package pm4.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import pm4.model.*;

public class TagsDao {
	
	protected ConnectionManager connectionManager;
	protected static TagsDao instance = null;
	
	protected TagsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static TagsDao getInstance() {
		if (instance == null) {
			instance = new TagsDao();
		}
		return instance;
	}

	
	public Tags create(Tags tag) throws SQLException {
		String insertTag =
			"INSERT INTO Tags(Tag,Likes,Dislikes,LikePercentage,Views,CommentCount) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTag,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, tag.getTag());
			insertStmt.setInt(2, tag.getLikes());
			insertStmt.setInt(3, tag.getDislikes());
			insertStmt.setBigDecimal(4, tag.getLikePercentage());
			insertStmt.setInt(5, tag.getViews());
			insertStmt.setInt(6, tag.getCommentCount());
			
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
			return tag;
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
	public Tags updateView(Tags tag, int newView) throws SQLException {
		String updateView = "UPDATE Tags SET Views=? WHERE TagId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateView);
			updateStmt.setInt(1, newView);
			updateStmt.setInt(2, tag.getTagId());

			updateStmt.executeUpdate();
			tag.setViews(newView);

			return tag;
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
	
	public Tags getTagFromTags(String tag) throws SQLException {
		String selectTag = 
				"SELECT TagId,Tag,Likes,Dislikes,LikePercentage,Views,CommentCount " +
						"FROM Tags " +
						"WHERE Tag=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTag);
			selectStmt.setString(1, tag);

			results = selectStmt.executeQuery();

			if(results.next()) {
				
				int resultTagId = results.getInt("TagId");
				String resultTag = results.getString("Tag");
				int resultLikes = results.getInt("Likes");
				int resultDislikes = results.getInt("Dislikes");
				BigDecimal resultLikePercentage = results.getBigDecimal("LikePercentage");
				int resultViews = results.getInt("Views");
				int resultCommentCount = results.getInt("CommentCount");
				
				Tags oneTag = new Tags(resultTagId, resultTag, resultLikes, resultDislikes, 
						resultLikePercentage, resultViews, resultCommentCount);
				
				return oneTag;
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
	
	
	
	public Tags delete(Tags tag) throws SQLException {
		String deleteTags = 
				"DELETE FROM Tags WHERE Tag=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTags);
			deleteStmt.setString(1, tag.getTag());
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

