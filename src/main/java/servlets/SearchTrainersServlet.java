package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataaccess.TeamDao;
import dataaccess.TrainerDao;
import models.Team;
import models.Trainer;
import services.Sanitaization;

/**
 * Servlet implementation class SearchTrainersServlet
 */ 
@WebServlet("/SearchTrainers")
public class SearchTrainersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Sanitaization sani = new Sanitaization();
		List<Trainer> trainers = new ArrayList();
		List<Team> teams = new ArrayList();
		
		TrainerDao trainerDao = new TrainerDao();
		TeamDao teamDao = new TeamDao();
		
		trainers = trainerDao.getAllTrainers();
		for(Trainer trainer : trainers) {
			try {
				teams.add(teamDao.getLatestTeam(trainer));
				trainer.setTrainerName(sani.desanitize(trainer.getTrainerName()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Map<String, List<Trainer>> model = new HashMap<>();
		model.put("trainer_list", trainers);
		
		Map<String, List<Team>> teamModel = new HashMap<>();
		teamModel.put("team_list", teams);
		
 		request.setAttribute("teams", teamModel);
 		request.setAttribute("trainers",model);
 		
 		getServletContext().getRequestDispatcher("/WEB-INF/views/SearchTrainers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String value = request.getParameter("trainerList");
		request.setAttribute("errorMsg", "");
		if(value != null) {
			int id = Integer.parseInt(value);
			TrainerDao trainerDao = new TrainerDao();
			Trainer trainer = trainerDao.getTrainerById(id);
			session.setAttribute("trainer",trainer);
			response.sendRedirect("CreateTeam");
		}
		else {
			request.setAttribute("errorMsg", "No Trainer Selected!");
			this.doGet(request, response);
		}
	}

}
