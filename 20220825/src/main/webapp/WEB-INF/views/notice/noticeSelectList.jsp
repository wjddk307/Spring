<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script> -->
<script src="resources/js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div align="center">
		<div><h1>공지사항 목록</h1></div>
		<div>
		   <table>
		   	  <tr>
		   	    <td width="60">
		   	    	<select id="key" name="key">
		   	    		<option value="1">제목</option>
		   	    		<option value="2">내용</option>
		   	    		<option value="3">작성자</option>
		   	    	</select>
		   	    </td>
		   	    <td width="60">
		   	    	<input type="text" id="val" name="val">
		   	    </td>
		   	    <td width="60" align="center">
		   	       <button type="button" onclick="searchCall()">검색</button>
		   	    </td>
		   	    <td>
		   	    	<button type="button" onclick="location.href='noticeForm.do'">작성하기</button>
		   	    </td>
		   	  </tr>
		   </table>
		</div><br>
		<div>
			<table border="1" id="table">
				<thead>
					<tr>
						<th width="100">아이디</th>
						<th width="150">작성자</th>
						<th width="250">제목</th>
						<th width="150">날짜</th>
						<th width="100">조회수</th>
						<th width="70">첨부파일</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:if test="${empty notices }">
					<tr>
						<td colspan="6" align="center">
						   게시글이 존재하지 않습니다.
						</td>
					</tr>
				    </c:if>
					<c:forEach items="${notices }" var="n">
						<tr class="colored" onclick="noticeCall(${n.NOTICE_ID})">
							<td  align="center">${n.NOTICE_ID }</td>
							<td  align="center">${n.NOTICE_WRITER }</td>
							<td>&nbsp;${n.NOTICE_TITLE }</td>
							<td align="center">${n.NOTICE_DATE }</td>
							<td align="center">${n.NOTICE_HIT }</td>
							<c:choose>
							<c:when test="${not empty n.NOTICE_ATTACH }"> 
								<td align="center">@</td>
							</c:when>
							<c:otherwise>
								<td align="center"></td>
							</c:otherwise>
						    </c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<div>
				<form id="frm" method="post">
					<input type="hidden" id="noticeId" name="noticeId">
				</form>
		</div>
	</div>
	
	<script type="text/javascript">
	function noticeCall(id) {
		frm.noticeId.value=id;
		frm.action = "noticeSelect.do";
		frm.submit();
	}
	
/*	function searchCall() { // ajax로 검색처리
		let key = document.getElementById('key').value;
		let val = document.getElementById('val').value;
		
		fetch("ajaxSearch.do", { // restController로 생성
			method: 'post',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded',
			},
			body : "key=" + key + "&val=" + val
		})
		.then(response => response.json())
		.then((data) => htmlView(data)  //html convert method가 필효하다.
	} 
	
	   // json타입으로 값을 전달할때
	      headers : {
				'Content-Type' : 'application/json',
			},
			body : JSON.stringify({'key':key, 'val': val})
	
	*/
	
	// jquery ajax
	function searchCall() {
		let key = document.getElementById('key').value;
		let val = document.getElementById('val').value;
		$.ajax({
			url : "ajaxSearch.do",
		    type : "post",
		    data : {"key" : key, "val" : val},
		    dataType : "json",
		    success : function(response) {
		    //	console.log(response);
		    	htmlView(response);
		    },
		    error : function() {
		    	
		    }
		});
	}
	
	function htmlView(data) {
		// html로 변환해서 원하는 원하는 위치에 출력하도록 구현
		
		$("#tbody").remove();
		var newbody = $("<tbody>").attr("id","tbody");
		data.forEach(function(els) {
			var tr = $("<tr>").attr("onclick", "noticeCall("+ els.noticeId +")")
							  .append( $("<td>").html(els.noticeId))
					          .append( $("<td>").html(els.noticeWriter))
					 		  .append( $("<td>").html(els.noticeTitle))
					          .append( $("<td>").html(els.noticeDate))
				     		  .append( $("<td>").html(els.noticeHit))
					 		  .append( $("<td>").html(els.noticeAttach));
		newbody.append(tr);
		});
		$("#table").append(newbody);			          
		
		
	}
</script>
</body>
</html>