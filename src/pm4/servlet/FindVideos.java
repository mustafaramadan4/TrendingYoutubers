package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.*;
import pm4.model.Videos;

@WebServlet("/findvideos")
public class FindVideos extends HttpServlet {
	
	protected VideosDao videosDao;
	
	@Override
	public void init() throws ServletException {
		videosDao = VideosDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        
        
        List<Videos> videos = new ArrayList<Videos>();
        
        
        String title = req.getParameter("title");
        
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	
        	try {
        		videos=videosDao.getVideoFromTitles(title);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        	
        	messages.put("previousTitle", title);
        }
        req.setAttribute("videos", videos);
        
        req.getRequestDispatcher("/FindVideos.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Videos> videos = new ArrayList<Videos>();
        
        
        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	
        	try {
        		videos = videosDao.getVideoFromTitles(title);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        }
        req.setAttribute("videos", videos);
        
        req.getRequestDispatcher("/FindVideos.jsp").forward(req, resp);
    }
}
