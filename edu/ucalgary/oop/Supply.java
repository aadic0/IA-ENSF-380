package edu.ucalgary.oop;

public class Supply {

    private String type;
    private int quantity;

    public Supply(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    

    // Allocate a supply to a DisasterVictim, removing it from the location's inventory
    public void allocateToVictim(DisasterVictim victim, Location location) {
        if (quantity > 0) {
            victim.addPersonalBelonging(this); // Add the supply to the victim's resources
            quantity--; // Reduce the quantity at the location
            location.removeSupply(this); // Remove from the location's inventory
            System.out.println(this.type + " allocated to " + victim.getFirstName() + " from " + location.getName());
        } else {
            System.out.println("Insufficient quantity of " + this.type + " at " + location.getName());
        }
    }
}
