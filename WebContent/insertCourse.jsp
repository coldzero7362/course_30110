<%@page import="course.lecturerVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="course.couseDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	couseDAO instance = couseDAO.getinstance();
	ArrayList<lecturerVO> list = instance.selectLecturer();
%>
<body>
	<div class ="index">
		<h1>교과목 추가</h1>
		<form action="/insert.do">
			<table border="1">
				<tr align="center">
					<td>교과목 코드</td>
					<td><input type="text" name="id"></td>
				</tr>
				<tr align="center">
					<td>과목명</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr align="center">
					<td>담당강사</td>
					<td>
						<select name="lecturer">
							<%
								for(lecturerVO vo : list){
							%>
								<option value="<%=vo.getLecturerIDX()%>"><%=vo.getLecturerName() %></option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr align="center">
					<td>학점</td>
					<td><input type="text" name="credit"></td>
				</tr>
				<tr align="center">
					<td>요일</td>
					<td>
						<input type="radio" value="1" name="week">월
						<input type="radio" value="2" name="week">화
						<input type="radio" value="3" name="week">수
						<input type="radio" value="4" name="week">목
						<input type="radio" value="5" name="week">금
						<input type="radio" value="6" name="week">토
					</td>
				</tr>
				<tr align="center">
					<td>시작</td>
					<td><input type="text" name="start_hour"></td>
				</tr>
				<tr align="center">
					<td>종료</td>
					<td><input type="text" name="end_end"></td>
				</tr>
			</table>	
		<button type="button" class="btn" onClick="location.href='select.do'">목록</button>
		<input type="submit" value="완료">
		</form>
	</div>
</body>
</html>