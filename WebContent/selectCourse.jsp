<%@page import="course.couseDAO"%>
<%@page import="course.courseVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		couseDAO instance = couseDAO.getinstance();
		ArrayList<courseVO> list = (ArrayList) request.getAttribute("list");
		int count = instance.countClass();
	%>
	<div class ="index">
	<h1>총 <%=count%>개의 교과목이 있습니다.</h1>
		<table border="1">
			<tr>
				<th>과목코드</th>
				<th>과목명</th>
				<th>학점</th>
				<th>담당강사</th>
				<th>요일</th>
				<th>시작시간</th>
				<th>종료시간</th>
				<th>관리</th>
			</tr>
			<%
				for(courseVO vo : list){
					
				%>
				<tr>
					<td><%=vo.getId() %></td>
					<td><%=vo.getName()%></td>
					<td><%=vo.getCredit() %></td>
					<td><%=vo.getLecturer() %></td>
					<td><%=vo.getWeek() %></td>
					<td><%=vo.getStart_hour() %></td>
					<td><%=vo.getEnd_end() %></td>
					<td><a href="updateCourse.jsp?id=<%=vo.getId()%>">수정</a>/<a href="/delete.do?id=<%=vo.getId()%>">삭제</a></td>
				</tr>
				
				<%
				}
				%>
		</table>
		<a href="insertCourse.jsp">작성</a>
	</div>
</body>
</html>