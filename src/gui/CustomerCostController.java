package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Customer;
/**
 *
 * @author Saiyra
 */
public class CustomerCostController {

    @FXML
    private Label totalCostLabel;
    @FXML
    private Label pointsStatusLabel;

    private Customer customer;
    private double totalCost;

    public void setData(Customer customer, double totalCost) {
        this.customer = customer;
        this.totalCost = totalCost;
        updateScreen();
    }

    private void updateScreen() {
        totalCostLabel.setText(String.format("Total Cost: %.2f", totalCost));
        String status = (customer.getPoints() >= 1000) ? "Gold" : "Silver";
        pointsStatusLabel.setText(String.format("Points: %d, Status: %s", customer.getPoints(), status));
    }

    @FXML
    private void handleLogout() {
         GUIController.getInstance().showLoginScreen();
    }
}
