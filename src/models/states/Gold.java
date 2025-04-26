
package models.states;
import models.Customer;
/**
 *
 * @author Pierre
 */
public class Gold implements State {
   
   @Override
    public double calculateDiscount(Customer customer, double totalCost) {
       
       return 0;
    }
}
