package dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import models.Helditem;

public class ItemDao {
	public void createItems(List<Helditem> itms) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			for(Helditem itm: itms) {
				em.persist(itm);	
			}
			trans.commit();
		} catch (Exception e){
			trans.rollback();
		}
		finally {
			em.close();
		}
	}

	
	public List<Helditem> getAllHelditem(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		List<Helditem> itmlst = new ArrayList();
		try {
			itmlst = em.createNamedQuery("Helditem.findAll", Helditem.class).getResultList();
		} catch(Exception e) {
			trans.rollback();
		}finally {
			em.close();
		}
		return itmlst;
	}
	
	public void deleteItemList() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			List<Helditem> itmlst = em.createNamedQuery("Helditem.findAll", Helditem.class).getResultList();
			for(Helditem itm: itmlst) {
				trans.begin();
		        em.remove(em.merge(itm));
		        trans.commit();
			}
		} catch(Exception e) {
			trans.rollback();
		}finally {
			em.close();
		}
	}
}
