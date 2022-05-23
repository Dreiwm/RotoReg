package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pokemon database table.
 * 
 */
@Entity
@NamedQuery(name="Pokemon.findAll", query="SELECT p FROM Pokemon p")
public class Pokemon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dexNumber;

	private String pokemonName;

	public Pokemon() {
	}
	
	public Pokemon(String name) {
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