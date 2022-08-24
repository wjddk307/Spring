<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원목록</h1>
<c:forEach items="${notices }" var="n">
	${n.notice_id } : ${n.notice_writer } : ${n.notice_title } : ${n.notice_subject } 
	: ${n.notice_date } : ${n.notice_hit } : ${n.notice_attach } : ${n.notice_attach_dir }<br>
</c:forEach>
<a href="noticeForm.do">글 등록</a>
</body>
</html>