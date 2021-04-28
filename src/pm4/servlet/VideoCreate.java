package pm4.servlet;

import pm4.dal.*;
import pm4.model.Videos;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/videocreate")
public class VideoCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/VideoCreate.jsp").forward(req, resp);
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
            messages.put("success", "Invalid title");
        } else {
        	
        	int videoid = Integer.parseInt(req.getParameter("videoid"));
        	int views = Integer.parseInt(req.getParameter("views"));
        	int likes = Integer.parseInt(req.getParameter("likes"));
        	int dislikes = Integer.parseInt(req.getParameter("dislikes"));
        	int commentcount = Integer.parseInt(req.getParameter("commentcount"));
        	String link=req.getParameter("link");
        	String description=req.getParameter("description");
        	
        	
	        try {
	        	
	        	Videos video = new Videos(videoid, title, views, likes, dislikes, commentcount,link,description);
	        	video = videosDao.create(video);
	        	messages.put("success", "Successfully created " + title);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/VideoCreate.jsp").forward(req, resp);
    }
}
