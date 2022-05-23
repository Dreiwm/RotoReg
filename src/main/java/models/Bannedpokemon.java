package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bannedpokemon database table.
 * 
 */
@Entity
@NamedQuery(name="Bannedpokemon.findAll", query="SELECT b FROM Bannedpokemon b")
public class Bannedpokemon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dexNumber;

	private String pokemonName;

	public Bannedpokemon() {
	}
	
	public Bannedpokemon(String name) {
		this.pokemonName=name;
	}

	public int getDexNumber() {
		return this.dexNumber;
	}

	public void setDexNumber(int dexNumber) {
		this.dexNumber = dexNumber;
	}

	public String getPokemonName() {
		return this.pokemonName;
	}

	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}

}