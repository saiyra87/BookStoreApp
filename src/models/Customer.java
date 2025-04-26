
package models;
import java.util.ArrayList;
import models.states.State;
import models.states.Gold;
import models.states.Silver;
import models.Book;

/**
 *
 * @author Pierre
 */
public class Customer extends User {
    private int points;
    private State status;

    public Customer(String username, String password, int points, State status) {
        super(username, password);
        this.points = points;
        this.status = status;
    }

    // Method to update customer status
    public void updateStatus() {
        if (points >= 1000) {
            setStatus(new Gold()); 
        } else {
            setStatus(new Silver()); 
        }
    }


    // Getters and Setters
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }
}


