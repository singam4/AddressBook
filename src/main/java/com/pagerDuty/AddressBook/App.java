package com.pagerDuty.AddressBook;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;

public class App {
	public static void main(String[] args) throws IOException, JSONException, InterruptedException {
		AddressBook addressBook = new AddressBook();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Address Book :\n1) List all users\n2) List details about\nEnter option: ");
		int option = in.nextInt();

		if (option == 1) {
			System.out.println(addressBook.fetch("https://api.pagerduty.com/users?include[]=contact_methods&offset="));
		}
		else if (option == 2) { 
			System.out.print("Enter name : "); 
			System.out.println(addressBook.fetch("https://api.pagerduty.com/users?query=" + in.next() 
			                                     + "&include[]=contact_methods&offset=")); 
		}
		else System.out.println("Wrong option entered");
		
		in.close();
	}
}
