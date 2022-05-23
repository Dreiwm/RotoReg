package dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import models.Pokemon;

public class PokemonDao {

	/**
	 * This will add the given Pokemon List to the database
	 * @param dex
	 */
	public void addDex(Pokemon pkm) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(pkm);	
			trans.commit();
		} catch (Exception e){
			trans.rollback();
		}
		finally {
			em.close();
		}
	}
	
	public void addFullDex(List<Pokemon> pkmlst) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			for(Pokemon pkm: pkmlst) {
				em.persist(pkm);	
			}
			trans.commit();
		} catch (Exception e){
			trans.rollback();
		}
		finally {
			em.close();
		}
	}
	
	public List<Pokemon> getAllPokemon(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		List<Pokemon> pkmlst = new ArrayList();
		try {
			pkmlst = em.createNamedQuery("Pokemon.findAll", Pokemon.class).getResultList();
		} catch(Exception e) {
			trans.rollback();
		}finally {
			em.close();
		}
		return pkmlst;
	}
	
	/**
	 * This function will delete the entire dex from the database
	 */
	public void deleteDex() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			List<Pokemon> pkmlst = em.createNamedQuery("Pokemon.findAll", Pokemon.class).getResultList();
			for(Pokemon pkm: pkmlst) {
				trans.begin();
		        em.remove(em.merge(pkm));
		        trans.commit();
			}
		} catch(Exception e) {
			trans.rollback();
		}finally {
			em.close();
		}
		
	}
	//TODO Fix this its not working
	public void returnPokemon(Pokemon pkm) {
		String name = pkm.getPokemonName();
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			Query qry = em.createQuery("DELETE FROM Bannedpokemon bannedpokemon.name="+name);
			int deletecount = qry.executeUpdate();
			if(deletecount > 0) {
				System.out.println("Returned Pokemon:"+name);
			}
			em.persist(pkm);
			trans.commit();
		} catch(Exception e) {
			
		}finally {
			em.close();
		}
	}
	
}
