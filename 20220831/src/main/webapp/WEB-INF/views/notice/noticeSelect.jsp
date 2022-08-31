<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>공지사항 상세보기</h1></div>
	<div>
	<table border="1">
			<tr>
				<th width="70">아이디</th>
				<td width="70" align="center">${n.noticeId }</td>
				<th width="100">작성자</th>
				<td width="150" align="center">${n.noticeWriter }</td>
				<th width="100">작성일자</th>
				<td width="150" align="center">${n.noticeDate }</td>
				<th width="70">조회수</th>
				<td width="70" align="center">${n.noticeHit }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="7">${n.noticeTitle }</td>
			</tr>
			<tr>
			    <th>내용</th>
			    <td colspan="7">
			    	<textarea rows="10" cols="100">${n.noticeSubject }</textarea>
			    </td>
		    </tr>
		    <tr>	    	
				<th>첨부파일</th>
				<td colspan="7">${n.noticeAttach }</td>
			</tr>
	</table>
   </div><br>
   <div>
   	  <button type="button" onclick="location.href='noticeSelectList.do'">목록가기</button>
   </div>
</div>   	
</body>
</html>