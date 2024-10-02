package dddubrico;  

public class Register {  
    private double cashOnHand; // Cash on hand in dollars  

    /**  
     * Default constructor that initializes the cash register with a default amount.  
     */  
    public Register() {  
        this.cashOnHand = 500; // Initial cash in dollars  
    }  

    /**  
     * Constructor with parameter to initialize the cash register with a specific amount.  
     *   
     * @param cashInDollars Initial cash amount in dollars  
     */  
    public Register(double cashInDollars) {  
        this.cashOnHand = cashInDollars; // Store in dollars  
    }  

    /**  
     * Return the current amount in the cash register in dollars.  
     *   
     * @return Current balance in dollars  
     */  
    public double getCurrentBalance() {  
        return cashOnHand; // Return the balance directly in dollars  
    }  

    /**  
     * Receive the amount deposited by the customer in dollars.  
     *   
     * @param amountInDollars Amount to be added to the cash register  
     */  
    public void acceptAmount(double amountInDollars) {  
        cashOnHand += amountInDollars; // Add the amount directly  
    }  

    /**  
     * Make a sale and update the cash register balance.  
     *   
     * @param saleAmount Amount of the sale in dollars  
     */  
    public void makeSale(double saleAmount) {  
        cashOnHand += saleAmount; // Update cash on hand with the sale amount  
    }  

    /**  
     * Method to check if the register can give change.  
     *   
     * @param change Amount of change to be given in dollars  
     * @return true if the register can give the change, false otherwise  
     */  
    public boolean canGiveChange(double change) {  
        return change <= cashOnHand; // Check if the change can be given  
    }  
}
