
package models.states;
import models.Customer;
/**
 *
 * @author Pierre
 */
public interface State {
    public double calculateDiscount(Customer customer, double totalCost);
}

