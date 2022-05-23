package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the helditem database table.
 * 
 */
@Entity
@NamedQuery(name="Helditem.findAll", query="SELECT h FROM Helditem h")
public class Helditem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int itemNumber;

	private String itemName;

	public Helditem() {
	}

	public Helditem(String name) {
		this.itemName=name;
	}
	
	public int getItemNumber() {
		return this.itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}