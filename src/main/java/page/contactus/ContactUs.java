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
@WebServlet("/contactus")
public class ContactUs extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd=req.getRequestDispatcher("/contactus.html");
		rd.include(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("namefild");
		String email=req.getParameter("emailfild");
		String message=req.getParameter("messagefild").trim();

		PrintWriter out= res.getWriter();
		try {
			Class.forName("org.postgresql.Driver");
			String postgresUrl="jdbc:postgresql://localhost:5432/MyDatabase";
			String username="postgres";
			String password="123456";
			Connection con = DriverManager.getConnection(postgresUrl,username, password);
			PreparedStatement emailCheck=con.prepareStatement("select email from contactinfo where email=?");
			emailCheck.setString(1, email);
			ResultSet rs=emailCheck.executeQuery();
			if(rs.next()) {
				res.setContentType("text/html");
				out.println("<h3 style='text-align: center ; color:red '>Oh!!  Doesnot success</h3>");
				out.println("<p style='text-align: center ; color:red '>This email already exists</p>");
				
				RequestDispatcher rd=req.getRequestDispatcher("/contactus.html");
				rd.include(req, res);
			}else {
				PreparedStatement ps=con.prepareStatement("insert into contactinfo values(?,?,?,?)");
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, message);
				ps.setString(4, "active");

				int val=ps.executeUpdate();

				res.setContentType("text/html");
				out.println("<h3 style='text-align: center ; color:green' > successfull</h3>");
				RequestDispatcher rd=req.getRequestDispatcher("/contactus.html");
				rd.include(req, res);

			}


		}catch(Exception e) {
			System.out.print(e);
		}
	}

}
