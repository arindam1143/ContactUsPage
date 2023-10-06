package page.contactus;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/admin/contactus/requests")
public class Admin extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		HttpSession session=req.getSession();
		PrintWriter out= res.getWriter();
		String verifyUname="";
		String verifyPass="";
		try {
			Class.forName("org.postgresql.Driver");
			String postgresUrl = "jdbc:postgresql://localhost:5432/MyDatabase";
			String uname = "postgres";
			String pass = "123456";
			Connection con = DriverManager.getConnection(postgresUrl, uname, pass);
			PreparedStatement ps = con.prepareStatement("select * from admininfo");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
					verifyUname=username;
					verifyPass=password;
					break;
				}
			}
			
		}catch(Exception e) {
			System.out.print(e);
		}
		if(username != null && password != null && ((username.equals("") && password.equals("")) || (!username.equals(verifyUname) && !password.equals(verifyPass)))) {
			res.sendRedirect("/ContactUsPage/admin/login");
		}
		else if(username != null && password != null && username.equals(verifyUname) && password.equals(verifyPass)){
			session.setAttribute("uname", verifyUname);
			session.setAttribute("pass", verifyPass);
			RequestDispatcher rd= req.getRequestDispatcher("/request.jsp");
			rd.forward(req, res);
		}
		else if(session.getAttribute("uname") !=null ) {
			RequestDispatcher rd= req.getRequestDispatcher("/request.jsp");
			rd.forward(req, res);
		}
		else {
			res.sendRedirect("/ContactUsPage/admin/login");

		}

	}
}