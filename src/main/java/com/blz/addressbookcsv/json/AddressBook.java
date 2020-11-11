package com.blz.addressbookcsv.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class AddressBook {

	static Scanner sc = new Scanner(System.in);
	static List<PersonDetails> person = new ArrayList<PersonDetails>();
	PersonDetails newEntry;
	boolean isExist;

	public void addContact(String addressBookName) {
		isExist = false;
		System.out.println("Enter your firstName : ");
		String firstName = sc.nextLine();
		System.out.println("Enter your lastName : ");
		String lastName = sc.nextLine();
		System.out.println("Enter your address : ");
		String address = sc.nextLine();
		System.out.println("Enter your city : ");
		String city = sc.nextLine();
		System.out.println("Enter your state : ");
		String state = sc.nextLine();
		System.out.println("Enter your zipCode : ");
		String zip = sc.nextLine();
		System.out.println("Enter your phoneNo : ");
		String phoneNo = sc.nextLine();
		System.out.println("Enter your emailId : ");
		String email = sc.nextLine();
		if (person.size() > 0) {
			for (PersonDetails details : person) {
				newEntry = details;
				if (firstName.equals(newEntry.firstName) && lastName.equals(newEntry.lastName)) {
					System.out.println("Contact " + newEntry.firstName + " " + newEntry.lastName + " already exists");
					isExist = true;
					break;
				}
			}
		}
		if (!isExist) {
			newEntry = new PersonDetails(firstName, lastName, address, city, state, zip, phoneNo, email);
			person.add(newEntry);
			addDataToFile(firstName, lastName, address, city, state, phoneNo, zip, email, addressBookName);
			try {
				addDataToCSVFile(addressBookName);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			try {
				addDataToJSONFile(addressBookName);

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void editContact() {
		Scanner nameInput = new Scanner(System.in);
		System.out.println(" Enter the first name ");
		String fName = nameInput.nextLine();
		for (int index = 0; index < person.size(); index++) {
			if (person.get(index).getFirstName().equals(fName)) {
				System.out.println(person.get(index));
				Scanner input = new Scanner(System.in);
				System.out
						.println(" Enter a choice 	1.first name 2.last name 3. city 4.state 5.zip 6.phone 7.email ");
				int option = nameInput.nextInt();
				switch (option) {
				case 1:
					System.out.println(" Enter first name ");
					String first_Name = input.nextLine();
					person.get(index).setFirstName(first_Name);
					System.out.println(person.get(index).getFirstName());
					break;
				case 2:
					System.out.println(" Enter last name ");
					String last_Name = input.nextLine();
					person.get(index).setLastName(last_Name);
					break;
				case 3:
					System.out.println(" Enter city name ");
					String city = input.nextLine();
					person.get(index).setCity(city);
					break;
				case 4:
					System.out.println(" Enter State ");
					String state = input.nextLine();
					person.get(index).setCity(state);
					break;
				case 5:
					System.out.println(" Enter pincode ");
					String zip = input.nextLine();
					person.get(index).setCity(zip);
					break;
				case 6:
					System.out.println(" Enter Mobile number ");
					String phone = input.nextLine();
					person.get(index).setZip(phone);
					break;
				case 7:
					System.out.println(" Enter Email id ");
					String email = input.nextLine();
					person.get(index).setCity(email);
					break;
				default:
					System.out.println(" Enter valid input ");
					break;
				}
			}
		}
		System.out.println(person);
	}

	public void deleteContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first name : ");
		String firstName = sc.nextLine();
		for (int i = 0; i < person.size(); i++) {
			if (person.get(i).getFirstName().equalsIgnoreCase(firstName)) {
				person.remove(i);
			} else {
				System.out.println("No data found");
			}
		}
		sc.close();
	}

	public void searchByCity() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter city name : ");
		String city = sc.nextLine();
		person.stream().filter(n -> n.getCity().equals(city))
				.forEach(i -> System.out.println("Data Found:" + i.getFirstName()));
	}

	public void viewByCity() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter city name : ");
		String city = sc.nextLine();
		person.stream().filter(n -> n.getCity().equals(city)).forEach(i -> System.out.println(i));
	}

	public void countBasedOnCity() {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter city name : ");
		String city = sc.nextLine();
		count = (int) person.stream().filter(n -> n.getCity().equals(city)).count();// count Stream;
		System.out.println(count);
	}

	public void sortingByName() {
		person = person.stream().sorted(Comparator.comparing(PersonDetails::getFirstName)).collect(Collectors.toList());
		person.forEach(i -> System.out.println(i));
	}

	public void sortingByCity() {
		person = person.stream().sorted(Comparator.comparing(PersonDetails::getCity)).collect(Collectors.toList());
		person.forEach(i -> System.out.println(i));
	}

	public void addDataToFile(String firstName, String lastName, String address, String city, String state,
			String phoneNumber, String zip, String email, String addressBookName) {
		System.out.println("Enter name for text file : ");
		String fileName = sc.nextLine();
		File file = new File("C:\\Users\\Siva Reddy\\eclipse-workspace\\AddressBookCSV_Json" + fileName + ".txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Contact:" + "\n1.First name: " + firstName + "\n2.Last name: " + lastName + "\n3.Address: "
					+ address + "\n4.City: " + city + "\n5.State: " + state + "\n6.Phone number: " + phoneNumber
					+ "\n7.Zip: " + zip + "\n8.email: " + email + "\n");
			bw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void readDataFromFile() {
		System.out.println("Enter address book filename : ");
		String fileName = sc.nextLine();
		Path filePath = Paths.get("C:\\Users\\Siva Reddy\\eclipse-workspace\\AddressBookCSV_Json" + fileName + ".txt");
		try {
			Files.lines(filePath).map(line -> line.trim()).forEach(line -> System.out.println(line));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void addDataToCSVFile(String addressBookName) throws IOException {
		System.out.println("Enter name for csv file : ");
		String fileName = sc.nextLine();
		Path filePath = Paths.get("C:\\Users\\Siva Reddy\\eclipse-workspace\\AddressBookCSV_Json" + fileName + ".csv");

		if (Files.notExists(filePath))
			Files.createFile(filePath);
		File file = new File(String.valueOf(filePath));

		try {
			FileWriter outputfile = new FileWriter(file, true);
			CSVWriter writer = new CSVWriter(outputfile);
			List<String[]> data = new ArrayList<>();
			for (PersonDetails detail : person) {
				data.add(new String[] { "Contact:" + "\n1.First name: " + detail.firstName + "\n2.Last name: "
						+ detail.lastName + "\n3.Address: " + detail.address + "\n4.City: " + detail.city
						+ "\n5.State: " + detail.state + "\n6.Phone number: " + detail.phoneNo + "\n7.Zip: "
						+ detail.zip + "\n8.email: " + detail.email + "\n" });
			}
			writer.writeAll(data);
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void readDataFromCSVFile() {
		System.out.println("Enter address book CSV filename : ");
		String fileName = sc.nextLine();
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(
					"C:\\Users\\Siva Reddy\\eclipse-workspace\\AddressBookCSV_Json" + fileName + ".csv"));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				for (String token : nextLine) {
					System.out.println(token);
				}
				System.out.print("\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void addDataToJSONFile(String addressBookName) throws IOException {
		System.out.println("Enter name for json file : ");
		String fileName = sc.nextLine();
		Path filePath = Paths.get("C:\\Users\\siva Reddy\\eclipse-workspace\\AddressBookCSV_Json" + fileName + ".json");
		Gson gson = new Gson();
		String json = gson.toJson(person);
		FileWriter writer = new FileWriter(String.valueOf(filePath));
		writer.write(json);
		writer.close();
	}

	public void readDataFromJSONFile() throws FileNotFoundException {
		System.out.println("Enter address book Json filename : ");
		String fileName = sc.nextLine();
		Path filePath = Paths.get("C:\\Users\\Siva Reddy\\eclipse-workspace\\AddressBookCSV_Json" + fileName + ".json");
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader(String.valueOf(filePath)));
		PersonDetails[] data = gson.fromJson(br, PersonDetails[].class);
		List<PersonDetails> list = Arrays.asList(data);
		for (PersonDetails details : list) {
			System.out.println("Firstname : " + details.firstName);
			System.out.println("Lastname : " + details.lastName);
			System.out.println("Address : " + details.address);
			System.out.println("City : " + details.city);
			System.out.println("State : " + details.state);
			System.out.println("Zip : " + details.zip);
			System.out.println("Phone no : " + details.phoneNo);
			System.out.println("Email : " + details.email);
		}
	}

}
