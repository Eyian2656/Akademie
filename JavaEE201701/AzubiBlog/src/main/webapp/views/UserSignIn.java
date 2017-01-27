package main.webapp.views;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

					HttpSession session = request.getSession(true);
					String userIDKey = new String("USERID");
					String userID = value.getId() + "";
					String userNameKey = new String("USERNAME");
					String userName = m_alias;
					session.setAttribute(userIDKey, userID);
					session.setAttribute(userNameKey, userName);

					response.sendRedirect("./ApplicationStart");
					return;
				}
			}
		}

		response.sendRedirect("../index.jsp#noLogIn");
	}

}
