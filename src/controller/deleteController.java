package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.couseDAO;

@WebServlet("/delete.do")
public class deleteController extends HttpServlet{
	
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
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		couseDAO instance =couseDAO.getinstance();
		String id = req.getParameter("id");
		
		boolean rst = instance.deleteCourse(id);
		
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
