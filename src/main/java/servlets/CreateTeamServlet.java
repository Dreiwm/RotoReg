package servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataaccess.ItemDao;
import dataaccess.PokemonDao;
import dataaccess.TeamDao;
import models.Pokemon;
import models.Team;
import models.Trainer;
import services.Sanitaization;

/**
 * Servlet implementation class CreateTeamServlet
 */
@WebServlet("/CreateTeam")
public class CreateTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PokemonDao pkmDao = new PokemonDao();
		Sanitaization sani = new Sanitaization();
		ItemDao itmDao = new ItemDao();
		HttpSession session = request.getSession();
		Trainer tr = (Trainer) session.getAttribute("trainer");
		
		request.setAttribute("trainerID", tr.getTrainerId());
		request.setAttribute("trainerName", sani.desanitize(tr.getTrainerName()));
		request.setAttribute("trainerNature", sani.desanitize(tr.getTainerNatue()));
		request.setAttribute("pokemon", pkmDao.getAllPokemon());
		request.setAttribute("items",itmDao.getAllHelditem());
		getServletContext().getRequestDispatcher("/WEB-INF/views/CreateTeam.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Trainer tr = (Trainer) session.getAttribute("trainer");
		
		Team team = new Team();
		team.setTrainer(tr);
		String[] pkmList = request.getParameterValues("pokemon");
		String[] itmList = request.getParameterValues("item");
		boolean notNone = false;
		request.setAttribute("errorMsg", "");
		for(String str: pkmList) {
			if(!str.equalsIgnoreCase("None"))
				notNone=true;
		}
		if(notNone) {
			team.setPokemonAndItems(pkmList, itmList);
			TeamDao tmDao = new TeamDao();
			try {
				Team checkTeam = tmDao.getLatestTeam(tr);
				Calendar calendar = new GregorianCalendar();
				if (checkTeam.getYear() == calendar.get(Calendar.YEAR)){
					checkTeam.updateTeam(team);
					tmDao.updateTeam(checkTeam);
				}
				else
					tmDao.addTeam(team);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/RotoReg.jsp").forward(request, response);
		}
		else {
			request.setAttribute("errorMsg", "Team Cannont have no Pokemon!");
			this.doGet(request, response);

		}
	}

}
