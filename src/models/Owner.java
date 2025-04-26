
package models;
import models.states.Silver;
/**
 *
 * @author Priyanshi
 */
public class Owner extends User {
    
    public Owner(String username, String password) {
        super(username, password);
    }
    
    public void addBook(BookStore store, String name, double price) {
        for (Book book : store.getBooksList()) {
            if (book.getName().equals(name)) {
                System.out.println("Book already exists in the list.");
                return; 
            }
        }
    
        store.getBooksList().add(new Book(name, price));
        store.saveData();
    }
    
    public void deleteBook(BookStore store, String name) {
        store.getBooksList().removeIf(book -> book.getName().equals(name));
        store.saveData();
    }
    
    public void addCustomer(BookStore store, String username, String password) {
        store.getCustomerList().add(new Customer(username, password, 0, new Silver()));
        store.saveData();
    }
    
    public void deleteCustomer(BookStore store, String username) {
        store.getCustomerList().removeIf(customer -> customer.getUsername().equals(username));
        store.saveData();
    }
}
