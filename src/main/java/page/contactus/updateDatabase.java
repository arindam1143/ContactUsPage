package page.contactus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/contactus/updateDatabase")
public class updateDatabase extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		HttpSession session=req.getSession();
		if(session.getAttribute("uname")== null) {
			res.sendRedirect("/ContactUsPage/admin/login");
		}
		RequestDispatcher rd = req.getRequestDispatcher("/request.jsp");
		rd.forward(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException{
		String email = req.getParameter("email");
		HttpSession session=req.getSession();
		if(session.getAttribute("uname")== null) {
			res.sendRedirect("/ContactUsPage/admin/login");
		}
		else {


			try {
				Class.forName("org.postgresql.Driver");
				String postgresUrl="jdbc:postgresql://localhost:5432/MyDatabase";
				String username="postgres";
				String password="123456";
				Connection con = DriverManager.getConnection(postgresUrl,username, password);
				PreparedStatement ps=con.prepareStatement("update contactinfo set status = 'archive' where email = ?");
				ps.setString(1, email);
				ps.executeUpdate();
				RequestDispatcher rd = req.getRequestDispatcher("/request.jsp");
				rd.forward(req, res);
			}catch(Exception e) {
				System.out.print(e);
			}
		}

	}
}
