package page.contactus;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/CheckLogedInOrNot")
public class CheckLogedInOrNot extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("uname") !=null) {
			res.sendRedirect("/ContactUsPage/admin/contactus/requests");
		}else {
			res.sendRedirect("/ContactUsPage/admin/login");
		}
	}
}
