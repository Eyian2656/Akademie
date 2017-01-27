package main.webapp.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.azubiblog.controller.BlogManager;
import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Category;

@WebServlet("/ApplicationStart")
public class ApplicationStart extends WebPage {
	
	private static final long serialVersionUID = -4737068622228649212L;
	@EJB
	private BlogManager m_blogmanager;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String fullHTML = "";
		PrintWriter out = resp.getWriter();
		StringBuilder sb = new StringBuilder();

		sb.append(htmlHead);

		Long uid = Long.parseLong((String) session.getAttribute("USERID"));
		String uname = (String) session.getAttribute("USERNAME");

		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			Blog b = new Blog("test" + i, Category.FLOP, uid);
			char[] data = new char[100];
			for (int j = 0; j < data.length; j++) {
				data[j] = j % 10 == 0 ? (char) (r.nextInt(26) + 65) : ' ';
			}
			b.setContent(new String(data));
			m_blogmanager.insertBlog(b);
		}

		List<Blog> blogs = m_blogmanager.getBlogsByAuthor(uid);

		if (blogs != null) {
			sb.append("\n<ul class='list-unstyled'>");
			String strblog = loadHTML("blog.html");
			String cblog = "";
			for (Blog b : blogs) {
				cblog = strblog.replace("{%BLOGTITLE%}", b.getTitle());
				cblog = cblog.replace("{%BLOGAUTHOR%}", uname);
				cblog = cblog.replace("{%BLOGLINK%}", "./blog?id=" + b.getId());
				cblog = cblog.replace("{%BLOGCONTENT%}", b.getContent());
				sb.append(cblog);
				sb.append('\n');
			}

			sb.append("\n</ul>");
		}

		sb.append(htmlFoot);

		fullHTML = sb.toString();

		Enumeration<String> vals = session.getAttributeNames();
		while (vals.hasMoreElements()) {
			String val = vals.nextElement();
			String key = "{%" + val.toUpperCase() + "%}";
			String reg = "\\{\\%" + val.toUpperCase() + "\\%\\}";
			if (fullHTML.contains(key)) {
				fullHTML = fullHTML.replaceAll(reg, (String) session.getAttribute(val));
			}
		}

		out.print(fullHTML);

	}

}
