package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the trainer database table.
 * 
 */
@Entity
@NamedQuery(name="Trainer.findAll", query="SELECT t FROM Trainer t")
@NamedQuery(name="Trainer.findByID", query="SELECT t FROM Trainer t WHERE t.trainerId = :trainerId")
public class Trainer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int trainerId;

	private String tainerNatue;

	private String trainerName;

	private int yearEntered;

	public Trainer() {
	}

	public int getTrainerId() {
		return this.trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getTainerNatue() {
		return this.tainerNatue;
	}

	public void setTainerNatue(String tainerNatue) {
		this.tainerNatue = tainerNatue;
	}

	public String getTrainerName() {
		return this.trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public int getYearEntered() {
		return this.yearEntered;
	}

	public void setYearEntered(int yearEntered) {
		this.yearEntered = yearEntered;
	}

}