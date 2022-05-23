package dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import models.Bannedpokemon;

public class BanDao {

	public void creatBanList(List<Bannedpokemon> dex) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			for(Bannedpokemon pkm: dex) {
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
	
	public List<Bannedpokemon> getAllDannedPokemon(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		List<Bannedpokemon> pkmlst = new ArrayList();
		try {
			pkmlst = em.createNamedQuery("Bannedpokemon.findAll", Bannedpokemon.class).getResultList();
		} catch(Exception e) {
			trans.rollback();
		}finally {
			em.close();
		}
		return pkmlst;
	}
	

	/**
	 * This will update the banlist in the database with the given list
	 * @param dex
	 */
	public void updateBanList(List<Bannedpokemon> dex) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			for(Bannedpokemon pkm: dex) {
				em.persist(pkm);	
			}
			trans.commit();
		} catch (Exception e){
			
		}
		finally {
			em.close();
		}
	
	}
	
	/**
	 * This function will delete the entire banlist
	 */
	public void deleteBanList() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			List<Bannedpokemon> pkmlst = em.createNamedQuery("Bannedpokemon.findAll", Bannedpokemon.class).getResultList();
			for(Bannedpokemon pkm: pkmlst) {
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
	public void banPokemon(Bannedpokemon pkm) {
		String name = pkm.getPokemonName();
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			Query qry = em.createQuery("DELETE FROM Pokemon pokemon.name="+name);
			int deletecount = qry.executeUpdate();
			if(deletecount > 0) {
				System.out.println("Removed Pokemon:"+name);
			}
			em.persist(pkm);
			trans.commit();
		} catch(Exception e) {
			
		}finally {
			em.close();
		}
	}
	
}
