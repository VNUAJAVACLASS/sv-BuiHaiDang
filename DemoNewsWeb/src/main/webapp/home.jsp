<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/home.css" type="text/css">
</head>
<body>
	<header class="header">
		<div class="container">
			<div class="d-flex justify-content-end"><a href="login.jsp">Đăng nhập</a></div>
			<div class="row">
				<div class="col-3">
					<img alt="logo" src="<%= request.getContextPath() %>/img/logo_home.png" width="100%">
				</div>
				<div class="col-8">Title</div>
			</div>
		</div>	
	</header>
	   <div class="d-flex justify-content-between align-items-center p-3 border-bottom">
        <div class="d-flex align-items-center">
            <img src="img/logo.png" alt="Logo" class="logo">
            <div class="header-title">Website Cửa Hàng Sách với JSP/Servlet</div>
        </div>
        <div>
            <a href="login.jsp" class="login-link">Đăng nhập</a>
        </div>
    </div>

	<jsp:include page="index.jsp"></jsp:include>
</body>
</html>