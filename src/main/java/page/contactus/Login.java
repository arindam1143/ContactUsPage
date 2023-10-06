package page.contactus;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/admin/login")
public class Login extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session=req.getSession();
		PrintWriter out= res.getWriter();
		if(session.getAttribute("uname") !=null){
			out.print("<p style='color:red; text-align: center'>already loged in</p>");
//			RequestDispatcher rd= req.getRequestDispatcher("/login.html");
//			rd.include(req, res);
		}else if(session.getAttribute("uname") !=null) {
			RequestDispatcher rd= req.getRequestDispatcher("/login.html");
			rd.include(req, res);
		}
		else {
			RequestDispatcher rd= req.getRequestDispatcher("/login.html");
			rd.include(req, res);
		}
		
	}
}
