package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
@NamedQuery(name="Team.findAllByYear", query="SELECT t FROM Team t WHERE t.year = :year")
@NamedQuery(name="Team.FindByTrainer", query="SELECT t FROM Team t WHERE t.trainer = :trainer")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int teamId;

	private String itm1;

	private String itm2;

	private String itm3;

	private String itm4;

	private String itm5;

	private String itm6;

	private String pkm1;

	private String pkm2;

	private String pkm3;

	private String pkm4;

	private String pkm5;

	private String pkm6;

	private int year;

	//bi-directional many-to-one association to Trainer
	@ManyToOne
	@JoinColumn(name="TrainerID")
	private Trainer trainer;

	public Team() {
		this.itm1="None";
		this.itm2="None";
		this.itm3="None";
		this.itm4="None";
		this.itm5="None";
		this.itm6="None";
		this.pkm1="None";
		this.pkm2="None";
		this.pkm3="None";
		this.pkm4="None";
		this.pkm5="None";
		this.pkm6="None";
	}

	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getItm1() {
		return this.itm1;
	}

	public void setItm1(String itm1) {
		this.itm1 = itm1;
	}

	public String getItm2() {
		return this.itm2;
	}

	public void setItm2(String itm2) {
		this.itm2 = itm2;
	}

	public String getItm3() {
		return this.itm3;
	}

	public void setItm3(String itm3) {
		this.itm3 = itm3;
	}

	public String getItm4() {
		return this.itm4;
	}

	public void setItm4(String itm4) {
		this.itm4 = itm4;
	}

	public String getItm5() {
		return this.itm5;
	}

	public void setItm5(String itm5) {
		this.itm5 = itm5;
	}

	public String getItm6() {
		return this.itm6;
	}

	public void setItm6(String itm6) {
		this.itm6 = itm6;
	}

	public String getPkm1() {
		return this.pkm1;
	}

	public void setPkm1(String pkm1) {
		this.pkm1 = pkm1;
	}

	public String getPkm2() {
		return this.pkm2;
	}

	public void setPkm2(String pkm2) {
		this.pkm2 = pkm2;
	}

	public String getPkm3() {
		return this.pkm3;
	}

	public void setPkm3(String pkm3) {
		this.pkm3 = pkm3;
	}

	public String getPkm4() {
		return this.pkm4;
	}

	public void setPkm4(String pkm4) {
		this.pkm4 = pkm4;
	}

	public String getPkm5() {
		return this.pkm5;
	}

	public void setPkm5(String pkm5) {
		this.pkm5 = pkm5;
	}

	public String getPkm6() {
		return this.pkm6;
	}

	public void setPkm6(String pkm6) {
		this.pkm6 = pkm6;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Trainer getTrainer() {
		return this.trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	
	public void setPokemonAndItems(String[] pkm, String[] items) {
		this.itm1 = items[0];
		this.itm2 = items[1];
		this.itm3 = items[2];
		this.itm4 = items[3];
		this.itm5 = items[4];
		this.itm6 = items[5];
		this.pkm1 = pkm[0];
		this.pkm2 = pkm[1];
		this.pkm3 = pkm[2];
		this.pkm4 = pkm[3];
		this.pkm5 = pkm[4];
		this.pkm6 = pkm[5];
	}
	
	public void updateTeam(Team team) {
		this.itm1 = team.getItm1();
		this.itm2 = team.getItm2();
		this.itm3 = team.getItm3();
		this.itm4 = team.getItm4();
		this.itm5 = team.getItm5();
		this.itm6 = team.getItm6();
		this.pkm1 = team.getPkm1();
		this.pkm2 = team.getPkm2();
		this.pkm3 = team.getPkm3();
		this.pkm4 = team.getPkm4();;
		this.pkm5 = team.getPkm5();;
		this.pkm6 = team.getPkm6();;
	}
}