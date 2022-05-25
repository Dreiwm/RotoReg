package dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import models.Bannedpokemon;
import models.Helditem;
import models.Pokemon;
import models.Team;
import models.Trainer;

public class TeamDao {
	  @PersistenceUnit(unitName="RotoReg")
	
	public void addTeam(Team team) throws ClassNotFoundException{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			Trainer trainer = team.getTrainer();
			trans.begin();
			em.persist(team);
			em.merge(trainer);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
		}
		finally {
			em.close();
		}
	}
	
	public void updateTeam(Team team) throws Exception{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(team);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
		}
		finally {
			em.close();
		}
	}
	
	public Team getTeamByID(int teamID) throws Exception{
		 EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        try{
	            Team item = em.find(Team.class, teamID);
	            return item;
	        }
	        finally{
	            em.close();
	        }
	}
	
	public Team getLatestTeam(Trainer trainer) throws Exception{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Team> teams = new ArrayList();
		try {
			teams = em.createNamedQuery("Team.FindByTrainer", Team.class).setParameter("trainer", trainer).getResultList();
		}finally {
			 em.close();
		}
		
		if(teams.isEmpty()) {
			Team team = new Team();
			team.setTrainer(trainer);
			return team;
		}
		else {
			return teams.get(teams.size()-1);
		}
	}
	
	public List<Team> getAllTeamsByYear(int year) throws Exception{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            List<Team> item = em.createNamedQuery("Team.findAllByYear", Team.class).setParameter("year", year).getResultList();
            return item;
        }
        finally{
            em.close();
        }
	}
	
	
	
}
