package edu.ty.foodapplicationproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class FoodProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_food_order")
	@SequenceGenerator(name = "seq_gen_food_order", allocationSize = 1, initialValue = 201, sequenceName = "seq_foodproduct")
	private long id;
	
	private String name;
	private String type;
	private String about;
	private String availability;
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
