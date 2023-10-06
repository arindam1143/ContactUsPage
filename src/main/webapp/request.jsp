<%
if(session.getAttribute("uname")==null){
	response.sendRedirect("/ContactUsPage/contactus");
}
%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Page</title>
</head>
<body>
	<h3 style="color:Green">All Active Message</h3>

	<%
		
	try {
		
		Class.forName("org.postgresql.Driver");
		String postgresUrl = "jdbc:postgresql://localhost:5432/MyDatabase";
		String username = "postgres";
		String password = "123456";
		Connection con = DriverManager.getConnection(postgresUrl, username, password);
		PreparedStatement ps = con.prepareStatement("select * from contactinfo");

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getString(4).equals("active")) {
				
		String requestData = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)
				+ " <lable style='color:green'>" + rs.getString(4) + " </lable>";
		out.println(requestData);
		out.println("<form action='updateDatabase' method='post'>");
		out.println("<input type='hidden' value='" + rs.getString(2) + "' name='email' />");
		out.println("<button type='submit'>archive </button>");
		out.println("</form>");
		out.println("<br /> <br />");
			}
		}

		out.println("<hr />");
		out.println("<h3 style='color:red'> All Message are Archived</h3>");
		
		ResultSet rs2 = ps.executeQuery();
		while (rs2.next()) {
			if (rs2.getString(4).equals("archive")) {
		String requestData = rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getString(3)
				+ "<lable style='color:red'> " + rs2.getString(4) + " </lable>";
		out.println(requestData);
		out.println("<br /> <br />");
			}
		}
	} catch (Exception e) {
			System.out.print(e);
	}
	out.println("<br /> <br />");
	out.println("<form action='logout' method='post'>");
	out.println("<button type='submit'>Logout </button>");
	out.println("</form>");
	
	%>
	
	
</body>
</html>