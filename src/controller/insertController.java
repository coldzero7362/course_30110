package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.courseVO;
import course.couseDAO;

@WebServlet("/insert.do")
public class insertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		couseDAO instance =couseDAO.getinstance();
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		int credit = Integer.parseInt(req.getParameter("credit"));
		String lecturer = req.getParameter("lecturer");
		String week = req.getParameter("week");
		String start = req.getParameter("start_hour");
		String end = req.getParameter("end_end");
		
		courseVO vo = new courseVO();
		
		vo.setId(id);
		vo.setName(name);
		vo.setCredit(credit);
		vo.setLecturer(lecturer);
		vo.setWeek(week);
		vo.setStart_hour(start);
		vo.setEnd_end(end);
		
		boolean rst = instance.insertCourse(vo);
		
		try {
			if(rst) {
				System.out.println("성공");
				RequestDispatcher rd = req.getRequestDispatcher("/select.do");
				rd.forward(req, resp);
			}else {
				System.out.println("실패");
				RequestDispatcher rd = req.getRequestDispatcher("/select.do");
				rd.forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
