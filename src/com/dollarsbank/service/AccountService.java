package com.dollarsbank.service;

import java.util.List;
import java.util.Scanner;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.Clear;
import com.dollarsbank.utility.Color;

public class AccountService  {
	
	Color col = new Color();
	Clear clear = new Clear();
	
	public Account newAccount(Scanner readIn, Customer cust) {
		
		Account newAccount = new Account();
		//List<String> transactions = new 
		

		col.text();
		System.out.println();
		System.out.println("Initial Deposit Amount: ");
		col.input();
		newAccount.setBalance(readIn.nextDouble());
		readIn.nextLine();//Must advance the line reader any time nextInt, nextDouble etc is used
		
		newAccount.setCustId(cust.getId());
		
		newAccount.addTransaction("Initial deposit amount: " + newAccount.getBalance() );
		
		return newAccount;
	}
	
	
	
	public List<Account> accountMenu(Scanner readIn, Customer cust, List<Account> aList, int index){
		
		String menu = "";
		boolean exit = false;
		
		while(!exit) {
		
			col.title();
			System.out.println("+-----------------------+");
			System.out.println("|   Welcome Customer!   |");
			System.out.println("+-----------------------+");
			
			System.out.println();
			col.text();
			System.out.println("1. Make a Deposit");
			System.out.println("2. Make a Withdrawal");
			System.out.println("3. Transfer Funds");
			System.out.println("4. View Transaction History");
			System.out.println("5. View Customer Profile");
			System.out.println("6. Sign Out");
			
			System.out.println("Enter your selection (1-6):");
			col.input();
			menu = readIn.nextLine();
			
			switch(menu) {
			case "1":
				clear.clearScreen();
				//.set( list index, object to replace existing object )
				aList.set( index, deposit( readIn, aList.get(index) ) );
				break;
			case "2":
				clear.clearScreen();
				aList.set( index, withdrawal( readIn, aList.get(index) ) );
				break;
			case "3":
				clear.clearScreen();
				transfer(readIn, aList, index);
				break;
			case "4":
				clear.clearScreen();
				transactions(readIn, aList.get(index));
				break;
			case "5":
				clear.clearScreen();
				printProfile(readIn, cust, aList.get(index) );
				break;
			case "6":
				exit = true;
				clear.clearScreen();
				break;
			default:
				clear.clearScreen();
				col.red();
				System.out.println("Invalid input! Please enter 1, 2, 3, 4, 5, or 6!");
			}
		
		}
		
		return aList;
	}
	
	
	
	private Account deposit(Scanner readIn, Account account) {
		
		double deposit = -1.0;
		
		col.title();
		System.out.println("+-------------+");
		System.out.println("|   Deposit   |");
		System.out.println("+-------------+");
		
		while (deposit < 0.0) {
			System.out.println();
			col.text();
			System.out.println("Current Balance: " + account.getBalance() );
			System.out.println("Deposit Amount: ");
			col.input();
			deposit = readIn.nextDouble();
			readIn.nextLine();
			
			if (deposit < 0.0) {
				col.red();
				System.out.println("Invalid Input! Please enter a positive amount or enter 0 to cancel deposit");
			}
		}
		
		col.text();
		System.out.println("\n\n\nPrevious Balance: " + account.getBalance() );
		account.setBalance(deposit + account.getBalance() );
		account.addTransaction("Deposited Amount: " + deposit);
		System.out.println("Amount Deposited: " + deposit);
		System.out.println("New Balance: " + account.getBalance() );
		System.out.println("\nPress ENTER to Continue...");
		readIn.nextLine();
		
		clear.clearScreen();
		
		return account;
	}
	
	
	
	private Account withdrawal(Scanner readIn, Account account) {
		
		double withdrawal = -1.0;
		
		col.title();
		System.out.println("+----------------+");
		System.out.println("|   Withdrawal   |");
		System.out.println("+----------------+");
		
		while (withdrawal < 0.0 || withdrawal > account.getBalance()) {
			System.out.println();
			col.text();
			System.out.println("Current Balance: " + account.getBalance() );
			System.out.println("Deposit Amount: ");
			col.input();
			withdrawal = readIn.nextDouble();
			readIn.nextLine();
			
			col.red();
			if (withdrawal < 0.0) System.out.println("Invalid Input! Please enter a positive amount or enter 0 to cancel withdrawal");
			else if (withdrawal > account.getBalance() ) System.out.println("Withdrawal cannot exceed your current balance!");
		}
		
		col.text();
		System.out.println("\n\n\nPrevious Balance: " + account.getBalance() );
		account.setBalance(account.getBalance() - withdrawal);
		account.addTransaction("Withdrawn Amount: " + Color.RED + withdrawal + Color.BLUE);
		System.out.println("Amount Withdrawn: " + Color.RED + withdrawal);
		col.text();
		System.out.println("New Balance: " + account.getBalance() );
		System.out.println("\nPress ENTER to Continue...");
		readIn.nextLine();
		
		clear.clearScreen();
		
		return account;
	}
	
	
	
	private List<Account> transfer(Scanner readIn, List<Account> aList, int index) {
		
		long account2Num;
		int account2Index = -1;
		double transferAmount = -1.0;
		char confirm = ' ';

		while(account2Index < 0) {
		
			col.title();
			System.out.println("+--------------------+");
			System.out.println("|   Transfer Funds   |");
			System.out.println("+--------------------+");
			
			col.text();
			System.out.println();
			System.out.println("Please enter the account number for the account you wish to TRANSFER TO");
			System.out.println("Account Number: ");
			col.input();
			account2Num = readIn.nextLong();
			for(int i = 0; i < aList.size(); i++) {
				if(aList.get(i).getAccountNum() == account2Num) {
					account2Index = i;
					break;
				}
			}
		
			if(account2Index < 0 ) {
				col.red();
				System.out.println("Account not found. Please try again");
			}
		}//while

		while (transferAmount < 0.0 || transferAmount > aList.get(index).getBalance()) {
			col.text();
			System.out.println("Current Balance: " + aList.get(index).getBalance() );
			System.out.println("Transfer Amount: ");
			col.input();
			transferAmount = readIn.nextDouble();
			readIn.nextLine();
			
			col.red();
			if(transferAmount < 0.0) System.out.println("\nPlease enter a positive number!\n");
			else if(transferAmount > aList.get(index).getBalance() ) System.out.println("\nAmount to transfer can't exceed current balance!\n");
			
		}
			
		while(confirm != 'Y') {
			col.text();
			System.out.println("$" + transferAmount + " will be transfered from Account Number " +
					aList.get(index).getAccountNum() + " to account number " + aList.get(account2Index).getAccountNum());
			System.out.println("Please enter (Y)es to confirm, or (N) to cancel:");
			col.input();
			confirm = readIn.next().toUpperCase().charAt(0);
		
			if (confirm == 'N') {
				col.text();
				System.out.println("Transfer canceled. Press ENTER to continue...");
				readIn.nextLine();
				return aList;
			}
		}
		
		col.text();
		System.out.println("Previous Balance: " + aList.get(index).getBalance());
		
		//Adjust Balances
		aList.get(index).setBalance(aList.get(index).getBalance() - transferAmount);
		aList.get(account2Index).setBalance(aList.get(account2Index).getBalance() + transferAmount);
		
		//Add transactions to history
		aList.get(index).addTransaction("Transfer to account # " + aList.get(account2Index).getAccountNum()
			+	": " + Color.RED  + transferAmount + Color.BLUE);
		aList.get(account2Index).addTransaction("Transfer from  account # " + aList.get(index).getAccountNum()
				+	": " + transferAmount);
		
		
		//Output of results
		System.out.println("$" + transferAmount + " was transfered from Account Number " +
				aList.get(index).getAccountNum() + " to account number " + aList.get(account2Index).getAccountNum());
		System.out.println("Current Balance: " + aList.get(index).getBalance());
		System.out.println("Press ENTER to continue...");
		readIn.nextLine();
		
		clear.clearScreen();
		
		return aList;
	}
	
	
	private void transactions(Scanner readIn, Account account ) {
		
		List<String> transactions = account.getTransactions();
		
		int firstIndex = transactions.size() - 1;
		int lastIndex = firstIndex - 5;
		char selection = ' ';
		
		col.title();
		System.out.println("+-------------------------+");
		System.out.println("|   Transaction History   |");
		System.out.println("+-------------------------+");
		
		col.text();
		System.out.println();
		System.out.println("Transactions sorted from most recent to oldest: ");
		System.out.println();
		
		if(lastIndex < 0) lastIndex = 0;
		for(int i = firstIndex; i >= lastIndex; i--) {
			System.out.println(transactions.get(i) );
			System.out.println();
		}
		
		
		System.out.println("Select (N)ext, (P)revious, Show (A)ll, or E(X)it: ");
		selection = readIn.next().toUpperCase().charAt(0);
		readIn.nextLine();
		
		switch(selection) {
		case 'N':
			firstIndex -= 5;
			break;
		case 'P':
			break;
		case 'A':
		case 'X':
			
		}
		
		clear.clearScreen();
		
		if (selection == 'A') {
			transactions.forEach(transaction -> System.out.println(transaction));
			System.out.println("\n\nPress ENTER to continue...");
			readIn.nextLine();
		}
		
		
		
		clear.clearScreen();
		
		return;
	}



	private void printProfile(Scanner readIn, Customer cust, Account account) {
		

		//int lastFour = (int) account.getAccountNum() % 10000; 
		
		col.title();
		System.out.println("+----------------------+");
		System.out.println("|   Customer Profile   |");
		System.out.println("+----------------------+");
		
		col.text();
		System.out.println("Name: " + cust.getName() );
		System.out.println("\nUsername: " + cust.getUserName() );
		System.out.println("\nAddress: " + cust.getAddress() );
		System.out.println("\nPhone Number: " + cust.getPhone() );
		//if (lastFour == 0) System.out.println("\nAccount Number: *****0000");
		//else System.out.println("\nAccount Number: *****" + lastFour);
		System.out.println("\nAccount Number: " + account.getAccountNum() );//Need to include for testing
		System.out.println("\nAccount Balance: " + account.getBalance());
		
		System.out.println("\n\nPress ENTER to continue...");
		readIn.nextLine();
		
		return;
	}
	
	
}