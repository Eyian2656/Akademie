package main.webapp.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class WebPage extends HttpServlet{
	private static final long serialVersionUID = 5384582502555726932L;

	protected static String htmlHead = null;
	protected static String htmlFoot = null;
	protected static String htmlBody = null;
	
	@Override
	public void init() throws ServletException {
		super.init();

		htmlHead = loadHead();
		htmlFoot = loadFoot();
		
	}
	
	protected String loadHTML(String name) {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				this.getClass().getClassLoader().getResourceAsStream(name)));

		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String loadFoot() {
		if (htmlFoot != null) {
			return htmlFoot;
		}
		htmlFoot = loadHTML("footer.html");
		return htmlFoot;
	}

	private String loadHead() {
		if (htmlHead != null) {
			return htmlHead;
		}
		htmlHead = loadHTML("header.html");
		return htmlHead;
	}

}
