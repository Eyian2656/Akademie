package main.webapp.views;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.azubiblog.controller.Usercontext;
import com.csc.azubiblog.model.User;

@WebServlet("/UserSignIn")
public class UserSignIn extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = -1162811676064346019L;
	public String m_alias = null;
	public String m_passwort = null;

	private User m_user = null;

	@EJB
	private Usercontext m_usercontext;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		response.setContentType("text/html");
		m_alias = request.getParameter("alias");
		m_passwort = request.getParameter("password");

		List<User> userList = m_usercontext.getAllUser();
		for (User value : userList) {
			if (value.getAlias().equals(m_alias)) {
				if (value.getPassword().equals(m_passwort)) {
					value.setOnline(true); // bringt noch nichts weil Objekt nur
											// in Klasse
					// TODO tuh anmelde zeug in der Usercontext.
					response.sendRedirect("views/ApplicationStart.jsp");
					return;
				}
			}
		}

		response.sendRedirect("../index.jsp#noLogIn");
	}

}
