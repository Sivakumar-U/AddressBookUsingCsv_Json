package com.blz.addressbookcsv.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {

	public static void main(String[] args) throws IOException {
		options();
	}

	static Map<String, AddressBookMain> addressBook = new HashMap<>();
	static AddressBookMain mainObj = new AddressBookMain();
	static Scanner sc = new Scanner(System.in);
	static String addressBookName;

	public void addAddressBook() throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println(
				"Enter choice \n0.Creating new addressbook \n1.Adding contacts in existing register \n2.Exit ");
		int entry = input.nextInt();
		if (entry != 2) {
			switch (entry) {
			case 0:
				Scanner nameInput = new Scanner(System.in);
				System.out.println(" Enter name of address book ");
				String nameOfNewBook = nameInput.nextLine();
				if (addressBook.containsKey(nameOfNewBook)) {
					System.out.println(" address book already exists");
					break;
				}
				addressBook.put(nameOfNewBook, mainObj);
				System.out.println(" address  book" + " " + nameOfNewBook + " " + "has been added");
				AddressBookMain.options();
				break;
			case 1:
				Scanner existingAddressName = new Scanner(System.in);
				System.out.println(" Enter name of address book ");
				String nameOfExistingRegister = existingAddressName.nextLine();
				if (addressBook.containsKey(nameOfExistingRegister)) {
					addressBook.get(nameOfExistingRegister);
					AddressBookMain.options();
				} else
					System.out.println(" address book is not found ");
			case 2:
				entry = 2;
				break;
			default:
				System.out.println(" Enter valid input ");
				break;
			}
		}
	}

	public static void options() throws IOException {
		AddressBook addressBook = new AddressBook();
		Scanner sc = new Scanner(System.in);
		int flag = 1;
		while (flag == 1) {
			System.out.println("Welcome to address book program ");
			System.out.println(
					"Enter choice \n1. Add Contact \n2. Edit Contact \n3. Delete Contact \n4. Search Contact \n5. View Contact \n6. Count no.of peoples in same city"
							+ " \n7. Sort By Name \n8. Sort By CityName \n9. Add Contact To File \n10. Read Data From File \n11. add Contacts To CSV File \n12. Read Data From CSV File"
							+ " \n13. add Contacts To JSON File \n14. Read Contacts From JSON File \n15. Exit ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				addressBook.addContact(addressBookName);
				break;
			case 2:
				if (AddressBook.person.isEmpty()) {
					System.out.println(" Address book is empty ");
					break;
				}
				addressBook.editContact();
				break;
			case 3:
				if (AddressBook.person.isEmpty()) {
					System.out.println(" Address book is empty ");
					break;
				}
				addressBook.deleteContact();
				break;
			case 4:
				if (addressBook.person.isEmpty()) {
					System.out.println(" Address book is empty ");
					break;
				}
				addressBook.searchByCity();
				break;
			case 5:
				if (addressBook.person.isEmpty()) {
					System.out.println(" Address book is empty ");
					break;
				}
				addressBook.viewByCity();
				break;
			case 6:
				if (addressBook.person.isEmpty()) {
					System.out.println(" Address book is empty ");
					break;
				}
				addressBook.countBasedOnCity();
				break;
			case 7:
				if (addressBook.person.isEmpty()) {
					System.out.println(" Address book is empty ");
					break;
				}
				addressBook.sortingByName();
				break;
			case 8:
				if (addressBook.person.isEmpty()) {
					System.out.println(" Address book is empty ");
					break;
				}
				addressBook.sortingByCity();
				break;
			case 9:
				addressBook.addContact(addressBookName);
				System.out.println("Successfully Added to text file");
				break;
			case 10:
				addressBook.readDataFromFile();
				break;
			case 11:
				addressBook.addContact(addressBookName);
				System.out.println("Successfully Added to CSV file");
				break;
			case 12:
				addressBook.readDataFromCSVFile();
				break;
			case 13:
				addressBook.addContact(addressBookName);
				System.out.println("Successfully added to JSON file");
				break;
			case 14:
				addressBook.readDataFromJSONFile();
				break;
			case 15:
				mainObj.addAddressBook();
				flag = 0;
				break;
			default:
				System.out.println(" Enter a valid choice");
				break;
			}
		}
		System.out.println(addressBook.person);
	}

}
