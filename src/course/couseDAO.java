package course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.net.aso.l;

public class couseDAO {
	
	private static couseDAO instance = new couseDAO();
	
	public static couseDAO getinstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","hr","hr");
		return con;
	}
	
	public ArrayList<courseVO> selectCourse(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<courseVO> list = new ArrayList<courseVO>();
		try {
			conn = getConnection();
			String sql="select a.id,a.name,a.credit, b.name,a.week,a.start_hour,a.end_end from course_tbl a,lecturer_tbl b  where a.lecturer = b.idx order by id asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				courseVO vo = new courseVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setCredit(rs.getInt(3));
				vo.setLecturer(rs.getString(4));
				int num = rs.getInt(5);
				switch (num) {
				case 1: vo.setWeek("월요일"); break;
				case 2: vo.setWeek("화요일"); break;
				case 3: vo.setWeek("수요일"); break;
				case 4: vo.setWeek("목요일"); break;
				case 5: vo.setWeek("금요일"); break;
				case 6: vo.setWeek("토요일"); break;
				}
				vo.setStart_hour(rs.getString(6));
				vo.setEnd_end(rs.getString(7));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int countClass() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			conn = getConnection();
			String sql="select count(id) from course_tbl";
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	public boolean insertCourse(courseVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		boolean rst = false;
		try {
			conn = getConnection();
			String sql = "insert into COURSE_TBL values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getCredit());
			pstmt.setString(4, vo.getLecturer());
			pstmt.setString(5,vo.getWeek());
			pstmt.setString(6, vo.getStart_hour());
			pstmt.setString(7, vo.getEnd_end());
			
			cnt = pstmt.executeUpdate();
			
			if(cnt >0) {
				rst = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rst;
	}
	public ArrayList<lecturerVO> selectLecturer(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<lecturerVO> list = new ArrayList<lecturerVO>();
		try {
			conn = getConnection();
			String sql = "select idx,name from lecturer_tbl";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lecturerVO vo = new lecturerVO();
				vo.setLecturerIDX(rs.getInt(1));
				vo.setLecturerName(rs.getString(2));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public courseVO getAline(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		courseVO vo = new courseVO();
		try {
			conn = getConnection();
			String sql = "select * from course_tbl where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setCredit(rs.getInt(3));
				vo.setLecturer(rs.getString(4));
				vo.setWeek(rs.getString(5));
				vo.setStart_hour(rs.getString(6));
				vo.setEnd_end(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	public boolean updateCourse(courseVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		boolean rst = false;
		try {
			conn = getConnection();
			String sql = "update COURSE_TBL set id=?,name=?,credit=?,lecturer=?,week=?,start_hour=?, end_end=? where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getCredit());
			pstmt.setString(4, vo.getLecturer());
			pstmt.setString(5,vo.getWeek());
			pstmt.setString(6, vo.getStart_hour());
			pstmt.setString(7, vo.getEnd_end());
			pstmt.setString(8, vo.getId());
			
			cnt = pstmt.executeUpdate();
			
			if(cnt >0) {
				rst = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rst;
	}
	public boolean deleteCourse(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		boolean rst = false;
		try {
			conn = getConnection();
			String sql = "delete COURSE_TBL where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			cnt = pstmt.executeUpdate();
			
			if(cnt >0) {
				rst = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}
	
}
