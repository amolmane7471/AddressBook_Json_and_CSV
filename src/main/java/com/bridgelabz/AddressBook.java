package com.bridgelabz;
import java.util.*;

public class AddressBook {
    ArrayList<Contact> contacts = new ArrayList<>();

    Scanner sc = new Scanner(System.in);
    public void addContact(){
        System.out.println("Enter first name: ");
        String firstName = sc.next();
        System.out.println("Enter last name: ");
        String lastName = sc.next();
        System.out.println("Enter Address : ");
        String address = sc.next();
        System.out.println("Enter city : ");
        String city = sc.next();
        System.out.println("Enter State : ");
        String state = sc.next();
        System.out.println("Enter zip : ");
        int zip = sc.nextInt();
        System.out.println("Enter phone number : ");
        long phoneNo = sc.nextLong();
        System.out.println("Enter email : ");
        String email = sc.next();

        if (isDuplicate(firstName, lastName))
            System.out.println(firstName+" "+ lastName+" already exists in contacts");
        else
            contacts.add(new Contact(firstName,lastName,address,city,state,zip,phoneNo,email));

    }

    public void displayContact(){
        for (Contact contact : contacts){
            System.out.println(contact);
        }
    }
    public void editContact(){
        System.out.println("Enter person name : ");
        String name = sc.next();
        for(Contact contact : contacts){
            if(contact.getFirstName().equals(name) || contact.getLastName().equals(name)){
                System.out.println("What you want to edit : \n1.first name \n2.last name \n3.address \n4.city \n5.state \n6.zip \n7.phone number \n8.email");
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        System.out.println("Enter first name :");
                        contact.setFirstName(sc.next());
                        System.out.println("Contact updated successfully");
                        break;
                    case 2:
                        System.out.println("Enter last name :");
                        contact.setLastName(sc.next());
                        System.out.println("Contact updated successfully");
                        break;
                    case 3:
                        System.out.println("Enter address :");
                        contact.setAddress(sc.next());
                        System.out.println("Contact updated successfully");
                        break;
                    case 4:
                        System.out.println("Enter city :");
                        contact.setCity(sc.next());
                        System.out.println("Contact updated successfully");
                        break;
                    case 5:
                        System.out.println("Enter state :");
                        contact.setState(sc.next());
                        System.out.println("Contact updated successfully");
                        break;
                    case 6:
                        System.out.println("Enter zip code :");
                        contact.setZip(sc.nextInt());
                        System.out.println("Contact updated successfully");
                        break;
                    case 7:
                        System.out.println("Enter contact number :");
                        contact.setPhoneNo(sc.nextLong());
                        System.out.println("Contact updated successfully");
                        break;
                    case 8:
                        System.out.println("Enter email :");
                        contact.setEmail(sc.next());
                        System.out.println("Contact updated successfully");
                        break;
                    default:
                        System.out.println("Invalid input!!!!!!!");
                }
                return;
            }
        }
        System.out.println(name + " not found!");
    }

    public void deleteContact(){
        System.out.println("Enter the person name  ");
        String name = sc.next();
        for(Contact contact : contacts){
            if(contact.getFirstName().equals(name) ){
                contacts.remove(contact);
                System.out.println(contact.getFirstName() +" contact deleted");
                return;
            }
        }
        System.out.println(name + " contact not found!!!");
    }
    public List<Contact> getContactList(){
        return contacts;
    }
  public boolean isDuplicate(String firstName, String lastName){
        boolean result = contacts.stream().filter(contact -> contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)).count() > 0;
        return result;
    }
    public void searchByCityOrState(String location){
        contacts.stream().forEach(contact -> {
            if (contact.getCity().equals(location) || contact.getState().equals(location)){
                System.out.println(contact);
            }
        });
    }
 }
