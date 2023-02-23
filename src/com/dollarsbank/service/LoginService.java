package com.dollarsbank.service;

import java.util.List;
import java.util.Scanner;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.Clear;
import com.dollarsbank.utility.Color;

public class LoginService {
	
	Color col = new Color();
	public Clear clear = new Clear();

	public List<Account> login(Scanner readIn, List<Customer> cList, List<Account> aList){
		
		AccountService service = new AccountService();
		
		String pass = "";
		String user = "";
		int cIndex = -1;
		int aIndex = -1;
		boolean validLogin = false;
		
		
		while (!validLogin) {
			col.title();
			System.out.println("+----------------------+");
			System.out.println("|     DOLLARS BANK     |");
			System.out.println("+----------------------+");
			col.text();
			System.out.println();
			System.out.println("Please enter your credentials");
			//System.out.println("or enter exit ") implement later
			System.out.println();
			
			System.out.println("Username: ");
			col.input();
			user = readIn.nextLine();
			col.text();
			System.out.println("Password: ");
			col.input();
			pass = readIn.nextLine();
			
			for (int i = 0; i < cList.size(); i++) {
				if (user.equals(cList.get(i).getUserName() ) ) {
					if(pass.equals(cList.get(i).getPassword() ) ) {
						cIndex = i;
						validLogin = true;
						for (int j = 0; j < aList.size(); j++) {
							if( cList.get(i).getId() == aList.get(j).getCustId() ) aIndex = j; 
						}
						break;
					}
					else break;
				}
			}
			
			clear.clearScreen();
			col.red();
			if(!validLogin) System.out.println("Invalid username or password!");
			System.out.println();
		
		}//while(!validLogin)
		
		clear.clearScreen();
		aList = service.accountMenu(readIn, cList.get(cIndex), aList, aIndex);
	
		return aList;
		
	}//login
	
	
}//class
