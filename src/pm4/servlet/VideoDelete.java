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


@WebServlet("/videodelete")
public class VideoDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Video");        
        req.getRequestDispatcher("/VideoDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("title", "Invalid title");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Videos video = new Videos(title);
	        try {
	        	video = videosDao.delete(video);
	        	// Update the message.
		        if (video == null) {
		            messages.put("title", "Successfully deleted " + title);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + title);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/VideoDelete.jsp").forward(req, resp);
    }
}
