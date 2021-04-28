package pm4.servlet;

import pm4.dal.*;
import pm4.model.Videos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/videoupdate")
public class VideoUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Title.");
        } else {
        	try {
        		Videos video = videosDao.getVideoFromTitle(title);
        		if(video == null) {
        			messages.put("success", "Title does not exist.");
        		}
        		req.setAttribute("video", video);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/VideoUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	try {
        		Videos video = videosDao.getVideoFromTitle(title);
        		if(video == null) {
        			messages.put("success", "Title does not exist. No update to perform.");
        		} else {
        			int newViews = Integer.parseInt(req.getParameter("views"));
        			if (req.getParameter("views").trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid view.");
        	        } else {
        	        	video = videosDao.updateViews(video, newViews);
        	        	messages.put("success", "Successfully updated newViews " + newViews);
        	        }
        		}
        		req.setAttribute("video", video);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/VideoUpdate.jsp").forward(req, resp);
    }
}
