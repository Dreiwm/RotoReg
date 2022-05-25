package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.TeamDao;
import models.Team;
import services.TeamObj;

/**
 * Servlet implementation class StatsServlet
 */
@WebServlet("/Stats")
public class StatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeamDao teamDao = new TeamDao();
		List<Team> teams = new ArrayList();
		TeamObj largestPkm = new TeamObj();
		TeamObj largestItm = new TeamObj();
		Calendar cal = Calendar.getInstance();
		try {
			int year = cal.get(Calendar.YEAR);
			teams = teamDao.getAllTeamsByYear(year);
			List<TeamObj> pkmStats = new ArrayList<TeamObj>();
			List<TeamObj> itmStats = new ArrayList<TeamObj>();
		
			for(int i = 0; i < teams.size() ; i++)
			{
				Team team = teams.get(i);
				String[] pkmItms = {team.getPkm1(), team.getPkm2(), team.getPkm3(), team.getPkm4(), team.getPkm5(), team.getPkm6(),
						team.getItm1(), team.getItm2(), team.getItm3(), team.getItm4(), team.getItm5(), team.getItm6()};
				for(int y = 0; y < pkmItms.length; y++) {
					if(y<3) {
						pkmStats = searchObj(pkmItms[y],pkmStats);
					}
					else {
						itmStats = searchObj(pkmItms[y],itmStats);
					}
				}
			}
			largestPkm = findMostInstatnces(pkmStats);
			largestItm = findMostInstatnces(itmStats);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("teamNum",teams.size());
		request.setAttribute("mostUsedPokemon",largestPkm.getName());
		request.setAttribute("pokemonCount",largestPkm.getCount());
		request.setAttribute("mostUsedItem",largestItm.getName());
		request.setAttribute("itemCount",largestItm.getCount());
		getServletContext().getRequestDispatcher("/WEB-INF/views/Stats.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	/**
	 * Helper funciton that will go though a list of TeamObjs and find the one with the higest count
	 * @param list - List of TeamObjs
	 * @return the TeamObj with the highest count value
	 */
	private TeamObj findMostInstatnces(List<TeamObj> list) {
		TeamObj greatestObj = new TeamObj();
		for (TeamObj obj : list) {
			if(obj.getCount()>greatestObj.getCount()) {
				greatestObj = obj;
			}
		}
		return greatestObj;
	}
	
	/**
	 * Helper function that will go though a list of TeamObjs and check if the name is in the list
	 * if it is then the object is incremented otherwise the item is added to the list
	 * @param name- Target string
	 * @param list- List of TeamObj to check
	 * @return Updated TeamObj list
	 */
	private List<TeamObj> searchObj(String name, List<TeamObj> list){
		boolean found = false;
		int index = 0;
		while (index<list.size()&&!found) {
			if(list.get(index).getName().equalsIgnoreCase(name)) {
				found=true;
				list.get(index).incrementCount();
			}
			index++;
		}
		if(!found) {
			list.add(new TeamObj(name));
		}
		
		return list;
	}


}
