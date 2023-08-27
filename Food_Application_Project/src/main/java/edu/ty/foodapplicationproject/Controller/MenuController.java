package edu.ty.foodapplicationproject.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.ty.foodapplicationproject.model.*;

public class MenuController {
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	Scanner input;

	public MenuController() {
		emf = Persistence.createEntityManagerFactory("food_application_project");
		em = emf.createEntityManager();
		et = em.getTransaction();
		input = new Scanner(System.in);
	}

	public List<FoodProduct> createFoodProduct(Menu menu, int count) {
		List<FoodProduct> foodproducts = new ArrayList<FoodProduct>();
		for (int i = 0; i < count; i++) {
			FoodProduct foodProduct = new FoodProduct();
			System.out.println("Enter the Food Product name");
			String name = input.nextLine();
			System.out.println("Enter the Food Product type");
			String type = input.nextLine();
			System.out.println("Enter 'about' Food Product  ");
			String about = input.nextLine();
			System.out.println("Enter the Food Product availability");
			String avilability = input.nextLine();
			System.out.println("Enter the Food Product price");
			double price = input.nextDouble();
			input.nextLine();

			foodProduct.setName(name);
			foodProduct.setType(type);
			foodProduct.setAbout(about);
			foodProduct.setAvailability(avilability);
			foodProduct.setPrice(price);
			foodProduct.setMenu(menu);

			foodproducts.add(foodProduct);
		}
		return foodproducts;
	}

	public void createMenu(User user) {
		Menu menu = new Menu();
		user.setMenu(menu);
		menu.setUser(user);
		System.out.println("Enter how many food products you want to create for the menu");
		int count = input.nextInt();
		input.nextLine();
		List<FoodProduct> foodProducts = createFoodProduct(menu, count);
		menu.setFoodProducts(foodProducts);

		et.begin();
		em.merge(user);

		et.commit();
	}

	public void updateMenu() {
		System.out.println("Enter the Food Product Id");
		long id = input.nextLong();
		input.nextLine();
		FoodProduct foodproduct = em.find(FoodProduct.class, id);
		System.out.println("Enter the Food Product new Price");
		double price = input.nextDouble();

		foodproduct.setPrice(price);
		et.begin();
		em.merge(foodproduct);
		et.commit();

	}

	public void deleteMenu(User user) {
		
		user.setMenu(null);
		System.out.println("Enter the menu id");
		long id = input.nextLong();

		Menu menu = em.find(Menu.class, id);
		System.out.println(menu);
		
		et.begin();
		em.merge(user);
		em.remove(menu);
		et.commit();

	}
	public void showMenu() {
		Query query = em.createQuery("Select m from Menu m"); 
		List<Menu> menu =  query.getResultList();
		
		for(Menu m : menu) {
			System.out.println(m.getId());
			List<FoodProduct> foodproducts = m.getFoodProducts();
			for(FoodProduct fp : foodproducts) {
				System.out.println("Food Id: "+ fp.getId() + "Name: " + fp.getName() );
				
			}
		}
		
	
	}

	public void addFoodToMenu() {
		System.out.println("Enter the menu id");
		long menuId= input.nextLong();
		Menu menu = em.find(Menu.class, menuId);
		
		System.out.println("Enter how many product products you want to add");
		int count = input.nextInt();
		input.nextLine();
		
		List<FoodProduct> foodProducts = createFoodProduct(menu, count);
		
		menu.setFoodProducts(foodProducts);
		
		et.begin();
		em.merge(menu);
		et.commit();
		
		
		
		
		
		
	}
}
