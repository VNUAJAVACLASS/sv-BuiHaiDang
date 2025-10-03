<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>
	<%
		session.invalidate(); // Huy session
		
		
		  Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if ("rememberedUser".equals(cookie.getName())) {
		                cookie.setMaxAge(0); // xóa ngay
		                cookie.setPath("/"); // đảm bảo đúng phạm vi
		                response.addCookie(cookie);
		            }
		        }
		    }

		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.sendRedirect("login");
	%>
</body>
</html>