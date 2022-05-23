package dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Trainer;

public class TrainerDao {

	/**
	 * Add a new trainer entity to the connected sql database
	 * @param trainer - Entity to be added to the sql database
	 * @throws ClassNotFoundException
	 */
	public void registerTrainer(Trainer trainer) throws ClassNotFoundException{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(trainer);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
		}
		finally {
			em.close();
		}
	}
/**
 * This class gets the last inputed trainer into the database
 * @return
 * @throws ClassNotFoundException
 */
	public Trainer getNewTrainerId() throws ClassNotFoundException{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		Trainer train = new Trainer();
		try {
			trans.begin();
			Query q = em.createQuery ("SELECT count(x) FROM Trainer x");
			List<Trainer> trainers = em.createNamedQuery("Trainer.findAll", Trainer.class).getResultList();		
			train = trainers.get(trainers.size()-1);
//			System.out.println(train.getTrainerId());

		} catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
		
		return train;
	}
	
	public Trainer getTrainerById(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		Trainer train = new Trainer();
		try {
			trans.begin();
			train = em.createNamedQuery("Trainer.findByID", Trainer.class).setParameter("trainerId", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
		
		return train;
	}
	
	
	public List<Trainer> getAllTrainers(){
	
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		List<Trainer> trainers = new ArrayList();
		try {
			trans.begin();
			trainers = em.createNamedQuery("Trainer.findAll", Trainer.class).getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
		return trainers;
	}
}
