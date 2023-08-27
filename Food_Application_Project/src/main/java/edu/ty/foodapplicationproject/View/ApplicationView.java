package edu.ty.foodapplicationproject.View;
import edu.ty.foodapplicationproject.Controller.*;
import edu.ty.foodapplicationproject.Controller.*;
import java.util.Scanner;
import edu.ty.foodapplicationproject.model.*;

public class ApplicationView {
	static User currentUser;
	Scanner input; 
	UserController uc;
	MenuController mc;
	BranchController bc;
	FoodOrderController foc;
	static {
		System.out.println("---------------------Welcome to the Food Application Project----------------------");
	}
	ApplicationView(){
		input =  new Scanner(System.in); 
			uc = new UserController();
			mc = new MenuController();
			bc = new BranchController();
			foc = new FoodOrderController();
	}
	
	
	
	public void registerUser() {
		System.out.println("Enter the user name: ");
		String name = input.nextLine();
		System.out.println("Enter the user email: ");
		String email = input.nextLine();
		System.out.println("Enter the user password: ");
		String password = input.nextLine();
		System.out.println("Choose the role  \n 1: manager \n 2: staff \n 3: customer ");
		int choice = input.nextInt();
		input.nextLine();
		String role ="" ;
		if(choice == 1) {
			role = "manager";
		}
		else if(choice == 2) {
			role = "staff";
		}
		else if(choice == 3) {
			role = "customer";
		}
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setRole(role);
		user.setEmail(email);
		
		// registering the user
		currentUser= uc.registerUser(user);
		
	}
	
	public void loginUser() {
//		System.out.println("Enter the user id: ");
//		 long id = input.nextLong();
//		 input.nextLine();
		 System.out.println("Enter your email");
		 String name = input.nextLine();
		 System.out.println("Enter your password");
		 String password = input.nextLine();
		   currentUser = uc.loginUser(name, password);
		 if(currentUser != null) {
			 System.out.println("login successfull");
			 
		 }
		 else {
			 System.out.println("login failed");
			 
		 }
		 
		 
		 
	}
	public static void main(String[] args) {
		ApplicationView run  = new ApplicationView();
		while(true) {
			System.out.println("Choose an option \n 1: Register \n 2: Login \n 3: Exit");
			int choice = run.input.nextInt();
			run.input.nextLine();
			
			switch (choice) {
			case 1:
			{
				run.registerUser();
				break;
			}
			case 2:
			{
				 run.loginUser();
				
					break;	
			
				
			}	
			case 3:
			{
				continue;
			}

			default:
			{
				return;
			}
				
			}
			
			if(currentUser.getRole().equals("staff")) {
				boolean condition = true;
				while(condition) {
					System.out.println("Enter your Choice \n 1: Create Menu \n 2: Update Menu \n 3: Show Menu \n 4:Delete menu \n 5: exit \n"
							+ " 6: Insert new Food Product to the existing menu");
					int choice1 = run.input.nextInt();
					run.input.nextLine();
					switch (choice1) {
					case 1:
					{	
						run.mc.createMenu(currentUser);
						break;
					}
					case 2:
					{	
						run.mc.updateMenu();
						break;
					}
					case 4:
					{	
						run.mc.deleteMenu(currentUser);
						break;
					}
					case 5:
					{
						condition = false;
						break;
					}
						
					case 3:
					{
						run.mc.showMenu();
					}
						
					default:
					{
						break;
					}
					
					case 6:
					{
						run.mc.addFoodToMenu();
					}
					}
					
					
				}
			}
			
			else if(currentUser.getRole().equals("manager")) {
				boolean condition1 = true;
				while(condition1) {
					System.out.println("Choose your options \n 1: Create Branch \n 2: Update Branch \n 3: Delete Branch \n 4: Show Branch \n 5: exit");
					 int choice1 = run.input.nextInt();
					 switch (choice1) {
					case 1:
					{
						run.bc.createBranch(currentUser);
						break;
					}
					case 2:
					{
						run.bc.updateBranch();
						break;
					}
					case 3:
					{
						run.bc.deleteBranch();
						break;
					}
					case 4:
					{
						run.bc.showBranches(currentUser);
						break;
					}
					case 5:
					{
						condition1 = false;
						break;
					}

					default:
					{
						condition1 = false;
						break;
					}
						
					}
				}
			}
			
			else if (currentUser.getRole().equals("customer")) {
				boolean condition2 = true;
				while(condition2) {
					System.out.println("Choose the options: \n 1: create the order \n 2: update order \n 3: delete order \n 4:exit \n 5: Show my order ");
					 int choice1 = run.input.nextInt();
					 switch (choice1) {
					case 1:
					{
						run.foc.createFoodOrder(currentUser);
						break;
					}
					case 2:
					{
						run.foc.updateFoodOrder(currentUser);
						break;
					}
					case 3:
					{
						run.foc.deleteFoodOrder();
						break;
					}
					case 4:
					{
						condition2 = false;
						break;
					}	
					case 5:
					{
						run.foc.showPlacedOrder(currentUser);
					}

					default:
					{
						break;
					}
						
					}
				}
			}
		}
		
	}
}
