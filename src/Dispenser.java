package FruitMachine;

public class Dispenser {  
    private int numberOfItems;  
    private int cost;  

    // Default constructor  
    public Dispenser() {  
        this.numberOfItems = 50; // Default number of items  
        this.cost = 100; // Default cost in cents  
    }  

    // Constructor with parameters  
    public Dispenser(int setNoOfItems, int setCost) {  
        this.numberOfItems = setNoOfItems;  
        this.cost = setCost;  
    }  

    // Show the number of items in the dispenser  
    public int getNoOfItems() {  
        return numberOfItems;  
    }  

    // Show the cost of the item  
    public int getCost() {  
        return cost;  
    }  

    // Reduce the number of items by 1 and return success status  
    public boolean makeSale() {  
        if (numberOfItems > 0) {  
            numberOfItems--;  
            return true; // Sale was successful  
        } else {  
            System.out.println("Dispenser is empty.");  
            return false; // Sale failed, no items left  
        }  
    }  

    // Method to restock the dispenser with a given number of items  
    public void restock(int itemsToAdd) {  
        if (itemsToAdd > 0) {  
            numberOfItems += itemsToAdd;  
            System.out.printf("Restocked %d items. Total items now: %d%n", itemsToAdd, numberOfItems);  
        } else {  
            System.out.println("Cannot add a non-positive number of items.");  
        }  
    }  
}
