package main.webapp.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.azubiblog.controller.BlogManager;
import com.csc.azubiblog.controller.CommentManager;
import com.csc.azubiblog.controller.Usercontext;
import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Comment;
import com.csc.azubiblog.model.User;

@WebServlet("/blog")
public class BlogViewer extends WebPage {

	private static final long serialVersionUID = -2858868477821766680L;
	@EJB
	private BlogManager m_blogmanager;
	@EJB
	private Usercontext m_usercontext;
	@EJB
	private CommentManager m_commentmgr;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String fullHTML = "";
		PrintWriter out = resp.getWriter();
		StringBuilder sb = new StringBuilder();

		sb.append(htmlHead);
		
		long bid = Long.parseLong((String)req.getParameter("id"));
		
		Blog b = m_blogmanager.getBlog(bid);
		User u = m_usercontext.getUser(b.getAuthor());
		List<Comment> lc = m_commentmgr.getCommentsByBlog(b);

		StringBuilder sb2 = new StringBuilder();
		if (b != null) {
			String strblog = loadHTML("ShowSingleBlog.html");
			String cblog = "";
			cblog = strblog.replace("{%TITLE%}", b.getTitle());
			cblog = cblog.replace("{%AUTHOR%}", u.getAlias());
			cblog = cblog.replace("{%CATEGORY%}", b.getCategory().getName());
			cblog = cblog.replace("{%CONTENT%}", b.getContent());
			sb.append(cblog);
			sb.append('\n');

			String comment = loadHTML("comment.html");
			for (Comment c : lc) {
				String cc = "";
				
				User cu = m_usercontext.getUser(c.getId());
				
				cc = comment.replace("{%COMMENTAUTHOR%}", cu.getAlias());
				cc = cc.replace("{%COMMENTCONTENT%}", c.getContent());
				
				sb2.append(cc);
				
			}
			
			
			
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

		fullHTML = fullHTML.replace("{%COMMENT%}", sb2.toString());
		
		out.print(fullHTML);

	}

}
