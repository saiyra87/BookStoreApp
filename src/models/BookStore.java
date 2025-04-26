
package models;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import models.states.Gold;
import models.states.Silver;
import models.states.State;



/**
 *
 * @author Priyanshi
 */
public class BookStore {
    
    private ArrayList<Book> booksList;
    private ArrayList<Customer> customerList;
    private Owner owner;
    private static BookStore instance;

    public BookStore(Owner owner) {
        this.owner = owner;
        this.booksList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }
    
    public static BookStore getInstance(Owner owner) {
    if (instance == null) {
        instance = new BookStore(owner);
        instance.loadData(); 
    }
    return instance;
}

    public void loadData() {
        booksList.clear();
        customerList.clear();

        // Load books
        try (Scanner bookScanner = new Scanner(new File("src/books.txt"))) {
            while (bookScanner.hasNextLine()) {
                String[] data = bookScanner.nextLine().split(",");
                if (data.length == 2) {
                    String name = data[0];
                    double price = Double.parseDouble(data[1]);
                    booksList.add(new Book(name, price));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Books file not found. Starting fresh.");
        }

        // Load customers
        try (Scanner customerScanner = new Scanner(new File("src/customers.txt"))) {
            while (customerScanner.hasNextLine()) {
                String[] data = customerScanner.nextLine().split(",");
                if (data.length == 4) {
                    String username = data[0];
                    String password = data[1];
                    int points = Integer.parseInt(data[2]);

                    // Determine customer state
                    State status;
                    switch (data[3]) {
                        case "Gold":
                            status = new Gold();
                            break;
                        default:
                            status = new Silver();
                    }

                    customerList.add(new Customer(username, password, points, status));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Customers file not found. Starting fresh.");
        }
    }

    public void saveData() {
        try (PrintWriter bookWriter = new PrintWriter(new FileWriter("src/books.txt"));
            PrintWriter customerWriter = new PrintWriter(new FileWriter("src/customers.txt"))) {

            // Save books
            for (Book book : booksList) {
                bookWriter.println(book.getName() + "," + book.getPrice());
            }

            // Save customers
            for (Customer customer : customerList) {
                customerWriter.println(customer.getUsername() + "," 
                + customer.getPassword() + "," 
                + customer.getPoints() + "," 
                + customer.getStatus().getClass().getSimpleName()); // Save state as class name
            }

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    

    public int login(String username, String password) {
        if (owner.getUsername().equals(username) && owner.getPassword().equals(password)) {
            return 0;
        }
        for (Customer customer : customerList) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return 1;
            }
        }
        return -1;
    }

    public ArrayList<Book> getBooksList() {
        return booksList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }
}
