package com.bridgelabz;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AddressBookClass {
   static HashMap<String, AddressBook> addressBookMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book System Using CSV and ");

        String filePath = "C:\\Users\\manea\\IdeaProjects\\day28_AddressBook\\";

        while (true) {
            System.out.println("1.Add Contact \n2.Display Contact \n3.Edit Contact \n4.Delete Contact \n5.Add new Address Book \n6.Display available address books \n7.Display all address books" +
                    "\n8.Search by city or state\n9.Write to the file\n10.Read from the file\n11.Write Address book contacts in CSV file\n12.Read Address book contacts from CSV file \n13.Exit");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter the address book name to add contact in that address book : ");
                    String addressBookName = sc.next();
                    if (!addressBookMap.containsKey(addressBookName)) {
                        System.out.println("Address book not exists!");
                    } else {
                        AddressBook addressBook = addressBookMap.get(addressBookName);
                        addressBook.addContact();
                    }
                    break;
                case 2:
                    System.out.println("Enter the address book name to display contacts in that address book : ");
                    String addressBookName1 = sc.next();
                    if (!addressBookMap.containsKey(addressBookName1)) {
                        System.out.println("Address book not exists!");
                    } else {
                        AddressBook addressBook = addressBookMap.get(addressBookName1);
                        addressBook.displayContact();
                    }
                    break;
                case 3:
                    System.out.println("Enter the address book name to edit contacts in that address book : ");
                    String addressBookName2 = sc.next();
                    if (!addressBookMap.containsKey(addressBookName2)) {
                        System.out.println("Address book not exists!");
                    } else {
                        AddressBook addressBook = addressBookMap.get(addressBookName2);
                        addressBook.editContact();
                    }
                    break;
                case 4:
                    System.out.println("Enter the address book name to edit contacts in that address book : ");
                    String addressBookName3 = sc.next();
                    if (!addressBookMap.containsKey(addressBookName3)) {
                        System.out.println("Address book not exists!");
                    } else {
                        AddressBook addressBook = addressBookMap.get(addressBookName3);
                        addressBook.deleteContact();
                    }
                    break;
                case 5:
                    System.out.println("Enter the address book name : ");
                    String name = sc.next();
                    if (addressBookMap.containsKey(name)) {
                        System.out.println("Address book already exists!");
                    } else {
                        AddressBook addressBook = new AddressBook();
                        addressBookMap.put(name, addressBook);
                    }
                    break;
                case 6:
                    Set<String> keys = addressBookMap.keySet();
                    if (keys.isEmpty()) {
                        System.out.println("No address books available!");
                    }
                    for (String str : keys) {
                        System.out.print(str + " ");
                    }
                    System.out.println();
                    break;
                case 7:
                    Set<Map.Entry<String, AddressBook>> addressBook = addressBookMap.entrySet();
                    if (addressBook.isEmpty()) {
                        System.out.println("No address books available!");
                    }
                    for (Map.Entry entry : addressBook) {
                        System.out.println(entry.getKey());
                        AddressBook addBook = (AddressBook) entry.getValue();
                        addBook.displayContact();
                    }
                    break;
                case 8:
                    Set<Map.Entry<String, AddressBook>> addressBook2 = addressBookMap.entrySet();
                    System.out.println("Enter city or state : ");
                    String location = sc.next();
                    if (addressBook2.isEmpty()) {
                        System.out.println("No address books available!");
                    }
                    for (Map.Entry entry : addressBook2) {
                        System.out.println(entry.getKey());
                        AddressBook addBook = (AddressBook) entry.getValue();
                        addBook.searchByCityOrState(location);
                    }
                    break;
                    case 9:
                        Set<Map.Entry<String, AddressBook>> addressBook1 = addressBookMap.entrySet();
                        for (Map.Entry entry : addressBook1) {
                            try {
                                FileOutputStream fout = new FileOutputStream(filePath + entry.getKey() + ".txt");
                                ObjectOutputStream oos = new ObjectOutputStream(fout);
                                AddressBook adBook = (AddressBook) entry.getValue();
                                List<Contact> contacts = adBook.getContactList();
                                oos.writeObject(contacts);
                                oos.close();
                            } catch (Exception exception) {
                                System.out.println(exception);
                            }
                        }
                        break;
                case 10:

                    System.out.println("Enter address book name :");
                    String file = sc.next();
                    try {
                        FileInputStream fin = new FileInputStream(filePath + file + ".txt");
                        ObjectInputStream oins = new ObjectInputStream(fin);
                        List<Contact> contacts = (List<Contact>) oins.readObject();
                        oins.close();
                        for (Contact contact : contacts) {
                            System.out.println(contact);
                        }
                    } catch (Exception exception) {
                        System.out.println(exception);
                    }
                    break;
                case 11:
                    Set<Map.Entry<String, AddressBook>> addressBook3 = addressBookMap.entrySet();
                    if (addressBook3.isEmpty()) {
                        System.out.println("Address book not available!");
                    }
                    for (Map.Entry entry : addressBook3) {
                        String fileName = entry.getKey().toString();
                        try {

                            FileWriter fileWriter = new FileWriter(filePath + fileName + ".csv");
                            CSVWriter writer = new CSVWriter(fileWriter);

                            String[] header = {"First Name", "Last Name", "Street", "City", "State", "zipcode", "ContactNo", "Email"};
                            writer.writeNext(header);
                            AddressBook addressBook4 = (AddressBook) entry.getValue();
                            List<Contact> contacts = addressBook4.getContactList();

                            for (Contact cnt : contacts) {
                                String[] data = {cnt.getFirstName(), cnt.getLastName(), cnt.getAddress(), cnt.getCity(), cnt.getState(), String.valueOf(cnt.getZip()), String.valueOf(cnt.getPhoneNo()), cnt.getEmail()};
                                writer.writeNext(data);
                            }

                            writer.close();

                        } catch (Exception exception) {
                            System.out.println(exception);
                        }
                    }
                    break;
                case 12:
                    System.out.println("Enter address book name :");
                    String addBookName = sc.next();
                    File f = new File(filePath + addBookName + ".csv");
                    String fileName = filePath + addBookName + ".csv";
                    if (!f.exists()) {
                        System.out.println(addBookName + " not found!");
                    } else {
                        try {
                            Reader reader = Files.newBufferedReader(Paths.get(fileName));
                            CSVReader csvReader = new CSVReader(reader);

                            String[] nextRecord;
                            csvReader.readNext();
                            while ((nextRecord = csvReader.readNext()) != null) {
                                System.out.print("First name:" + nextRecord[0] + "\n");
                                System.out.print("Last name:" + nextRecord[1] + "\n");
                                System.out.print("Address:" + nextRecord[2] + "\n");
                                System.out.print("City:" + nextRecord[3] + "\n");
                                System.out.print("State:" + nextRecord[4] + "\n");
                                System.out.print("Zip code:" + nextRecord[5] + "\n");
                                System.out.print("Phone:" + nextRecord[6] + "\n");
                                System.out.print("Email:" + nextRecord[7] + "\n");
                                System.out.println();
                            }

                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                    break;
                case 13:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }

    }
}
