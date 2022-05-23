package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Trainer;
import services.Sanitaization;
import dataaccess.TrainerDao;

/**
 * Servlet implementation class AddTrainerServlet
 */
@WebServlet("/AddTrainer")
public class AddTrainerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		getServletContext().getRequestDispatcher("/WEB-INF/views/AddTrainer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Sanitaization sani = new Sanitaization();
		String name = request.getParameter("tname");
		String nature = request.getParameter("tnature");
		request.setAttribute("errorMsg", "");
		//Sanitize the input to prevent database attacks
		name = sani.sanitizeInput(name);
		nature = sani.sanitizeInput(nature);
		//Check if the name inputed is black or only special characters
		if(!sani.desanitize(name).isBlank()) {
			Trainer trainer = new Trainer();
			trainer.setTrainerName(name);
			trainer.setTainerNatue(nature);
			try {
				TrainerDao dao = new TrainerDao();
				dao.registerTrainer(trainer);
				session.setAttribute("trainer",dao.getNewTrainerId());
				response.sendRedirect("CreateTeam");
			}
			catch(Exception e){
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/WEB-INF/views/AddTrainer.jsp").forward(request, response);
			}

		}
		else{
			request.setAttribute("errorMsg", "Invalid Name!");
			this.doGet(request, response);
		}
	}

}
