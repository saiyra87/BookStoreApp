
package models.states;
import models.Customer;
/**
 *
 * @author Pierre
 */
public class Silver implements State {
   
    @Override
    public double calculateDiscount(Customer customer, double totalCost) {
        return 0;
    }
}
