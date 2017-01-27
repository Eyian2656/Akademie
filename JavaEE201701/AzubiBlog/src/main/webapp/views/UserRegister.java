package main.webapp.views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.azubiblog.controller.Usercontext;
import com.csc.azubiblog.model.User;

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {

	private static final long serialVersionUID = 1228635799790224396L;
	public String m_alias = null;
	public String m_lastname = null;
	public String m_surname = null;
	public Date m_birthday = null;
	public String m_passwort = null;

	@EJB
	private Usercontext m_userContext;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		response.setContentType("text/html");
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			m_alias = request.getParameter("alias");
			m_surname = request.getParameter("surname");
			m_lastname = request.getParameter("lastname");
			m_birthday = df.parse(request.getParameter("birthday"));
			m_passwort = request.getParameter("password");
			User user = new User(m_lastname, m_surname, m_birthday, m_alias, m_passwort);
			user.setLastOnline(new Date());
			user = m_userContext.insertUser(user);
			if (user != null) {
				response.sendRedirect("../index.jsp#success");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
