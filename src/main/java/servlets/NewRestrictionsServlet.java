package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataaccess.BanDao;
import dataaccess.ItemDao;
import dataaccess.PokemonDao;
import dataaccess.TeamDao;
import models.Bannedpokemon;
import models.Helditem;
import models.Pokemon;
import services.WebSiteParser;

/**
 * Servlet implementation class NewRestrictionsServlet
 */
@WebServlet("/NewRestrictions")
public class NewRestrictionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = (String) session.getAttribute("bulbUrl");

		WebSiteParser wsp = new WebSiteParser();

		String[] splitUrl = url.split(",");
		List<String> fullPokemonList = new ArrayList();

		for (String s: splitUrl) {
			List<String> pkmSpecies = wsp.getSmogonPkm(wsp.getFullPkm(s));
			for (String pkm : pkmSpecies) {
				if(!fullPokemonList.contains(pkm))
					fullPokemonList.add(pkm);
			}

		}
		session.setAttribute("pokemonList", fullPokemonList);
		request.setAttribute("pokemon", fullPokemonList);
		getServletContext().getRequestDispatcher("/WEB-INF/views/NewRestrictions.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> pkmList = (List<String>) session.getAttribute("pokemonList");
		String[] banList = request.getParameterValues("removePokemon");

		//ToDo: Add a database call to get ban list and add what is to be removed to it and then go though list

		List<Bannedpokemon> removed = new ArrayList();
		List<Pokemon> dexList = new ArrayList();
		if(banList != null) {
			List<String> innerPkmList = new ArrayList();

			for(Iterator<String> iterator = pkmList.iterator(); iterator.hasNext() ; ) {
				String value = iterator.next();
				Boolean found = false;
				for(String ban: banList) {
					if(value.contains(ban)) {
						Bannedpokemon bnnd = new Bannedpokemon(value);
						removed.add(bnnd);
						found = true;
					}
				}
				if(found==false) {
					if(!innerPkmList.contains(value)) {
						innerPkmList.add(value);
					}
				}
			}

			//			for(String pkm: innerPkmList) {
			//				System.out.println(pkm);
			//			}

			for(String mon: innerPkmList) {
				Pokemon pkm = new Pokemon(mon);
				dexList.add(pkm);
			}
		}
		for(Pokemon pkm: dexList) {
			System.out.println(pkm.getPokemonName());
		}
		//This is to catch if the pokemon list has no changes
		if(dexList.isEmpty()){
			for (String pkm: pkmList) {
				Pokemon mon = new Pokemon(pkm);
				dexList.add(mon);
			}
		}
		BanDao banDao = new BanDao();
		PokemonDao pkDao = new PokemonDao();
		ItemDao itmDao = new ItemDao();
		pkDao.deleteDex();
		banDao.deleteBanList();

		//		pkDao.addFullDex(dexList);
		for(Pokemon pkm: dexList) {
			pkDao.addDex(pkm);
		}

		banDao.creatBanList(removed);

		dexList = pkDao.getAllPokemon();

		WebSiteParser wsp = new WebSiteParser();
		List<Helditem> itmList=new ArrayList();
		List<String>itms=wsp.getSmogonItems();
		for(String itm: itms) {
			Helditem im = new Helditem(itm);
			itmList.add(im);
		}

		itmDao.createItems(itmList);

		getServletContext().getRequestDispatcher("/WEB-INF/views/RotoReg.jsp").forward(request, response);
	}

}
