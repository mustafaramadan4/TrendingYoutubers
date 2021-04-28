<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find a Video</title>
</head>


<body>
<div class="bg-image"
style="background-image: url('background.jpeg');
      height: 100vh">
	<nav class="navbar navbar-expand-lg navbar-light bg-warning">
  		<a class="navbar-brand" href="#"><img src="logo.gif" alt="logo" height=30px>TrendingYoutubers</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>
  		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    		<div class="navbar-nav">
      			<a class="nav-item nav-link active" href="findvideos"><img src="icon1.png" alt="icon1" height=30px> Read Video <span class="sr-only">(current)</span></a>
      			<a class="nav-item nav-link" id="videoCreate" href="videocreate"><img src="icon2.png" alt="icon2" height=30px>Create Video</a>
      			<a class="nav-item nav-link" id="videoDelete" href="videodelete"><img src="icon3.png" alt="icon3" height=30px>Delete Video</a>
      			<a class="nav-item nav-link" id="videoUpdate" href="videoupdate"><img src="icon4.png" alt="icon4" height=30px>Update Video</a>
   			 </div>
  		</div>
	</nav>
	
	<audio src="a.mp3" controls></audio>

<!-- 	<div class="container theme-showcase" role="main" class="bg-image"
style="background-image: url('background.jpeg');
      height: 100vh"> -->

	<form action="findvideos" method="post">
		<div class="bg-warning text-dark" role="alert">
			<h1 class="d-flex justify-content-center">Search for a Video by Title</h1>
		</div>
		<div class="d-flex justify-content-center">
		<p  class="alert alert-info" role="alert">
			<label for="title">Title</label>
			<input id="title" name="title" value="${fn:escapeXml(param.title)}">
		</p>
		</div>
		
		<p><div class="d-flex justify-content-center">
			<input type="submit" ></div>
			<br/><br/><br/>
			<div class="alert alert-info" role="alert">
				<span id="successMessage"><b>${messages.success}</b></span>
			</div>
		</p>
		
	</form>
	<br/>
	<!-- <div id="videoCreate"><a href="videocreate">Create Video</a></div> -->
	<br/>
	<div class="bg-warning text-dark" role="alert">
	<h1 class="d-flex justify-content-center">Matching Video</h1>
        <table border="1" class="table table-striped">
            <tr>
                <th>VideoId</th>
                <th>Title</th>
                <th>Views</th>
                <th>Likes</th>
                <th>Dislikes</th>
                <th>CommentCount</th>
                

                <th>Delete</th>
                <th>Update</th>
            </tr>
            <c:forEach items="${videos}" var="video" >
                <tr>
                    <td><c:out value="${video.getVideoId()}" /></td>
                    <td><c:out value="${video.getTitle()}" /></td>
                    <td><c:out value="${video.getViews()}" /></td>
                    <td><c:out value="${video.getLikes()}" /></td>
                    <td><c:out value="${video.getDislikes()}" /></td>
                    <td><c:out value="${video.getCommentCount()}" /></td>
                    

                    <td><a href="videodelete?title=<c:out value="${video.getTitle()}"/>">Delete</a></td>
                    <td><a href="videoupdate?title=<c:out value="${video.getTitle()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
       </div>
       
       
       <div class="alert alert-info">
       		<div>Guangpeng Wu,Yu Fan,Fangquan Zhuang,Mustafa Ramadan,Jing Zhao</div>
       </div>
       
       
      </div>
       <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
