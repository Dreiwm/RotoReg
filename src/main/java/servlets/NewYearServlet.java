package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.WebSiteParser;

/**
 * Servlet implementation class NewYearServlet
 */
@WebServlet("/NewYear")
public class NewYearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/NewYear.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		HttpSession session = request.getSession();
		request.setAttribute("errorMsg", "");
		
		if(url.contains("_number")&&url.contains("bulbapedia.bulbagarden.net")) {
			session.setAttribute("bulbUrl", url);
			response.sendRedirect("NewRestrictions");
		}
		else {
			request.setAttribute("errorMsg", "Invalid Url!");
			this.doGet(request, response);
		}
	}

}
