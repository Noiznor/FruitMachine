package FruitMachine;  

import javax.swing.JOptionPane;  

public class Main {  
    public static void main(String[] args) {  
        Register cashRegister = new Register(500);  // Starting balance of $500  

        // Define juice options and prices (in dollars)  
        String[] juiceNames = {"Apple Juice", "Orange Juice", "Mango Lassi", "Fruit Punch"};  
        double[] juicePrices = {8.00, 10.00, 12.00, 15.00}; // Prices in dollars  
        int[] juiceStocks = {10, 8, 5, 6}; // Stocks for each juice  

        while (true) {  
            StringBuilder menu = new StringBuilder();  
            menu.append("========================================\n");  
            menu.append("               CDG Fruit Juice Machine          \n");  
            menu.append("========================================\n");  
            menu.append(String.format("Current balance in cash register: $%.2f\n\n", cashRegister.getCurrentBalance()));  
            menu.append("Available Juices and Prices:\n");  
            for (int i = 0; i < juiceNames.length; i++) {  
                menu.append(String.format("%d. %s - $%.2f (Available: %d)\n", (i + 1), juiceNames[i], juicePrices[i], juiceStocks[i]));  
            }  
            menu.append("0. Exit");  

            // Show options and get user selection  
            String input = JOptionPane.showInputDialog(menu.toString());  
            if (input == null) { // Check for cancel action  
                JOptionPane.showMessageDialog(null, "Thank you for using the Fruit Juice Machine! Goodbye!");  
                break; // Exit the loop and terminate the program  
            }  

            int choice;  

            try {  
                choice = Integer.parseInt(input);  
                if (choice == 0) {  
                    JOptionPane.showMessageDialog(null, "Thank you for using the Fruit Juice Machine! Goodbye!");  
                    break; // Exit the loop and terminate the program  
                }  

                // Validate choice  
                if (choice < 1 || choice > juiceNames.length) {  
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");  
                    continue;  
                }  

                // Get the quantity of the selected juice  
                String quantityInput = JOptionPane.showInputDialog(String.format("How many %s would you like to order? (Available: %d)", juiceNames[choice - 1], juiceStocks[choice - 1]));  
                if (quantityInput == null) { // Check for cancel action  
                    continue; // Go back to the main menu  
                }  
                int quantity = Integer.parseInt(quantityInput);  

                // Check if the quantity is available  
                if (quantity > juiceStocks[choice - 1]) {  
                    JOptionPane.showMessageDialog(null, "Insufficient stock available. Please choose a smaller quantity.");  
                    continue; // Go back to the main menu  
                }  

                double totalPrice = juicePrices[choice - 1] * quantity; // Calculate total price  
                
                double insertedCash = 0; // Initialize insertedCash  
                boolean sufficientFunds = false; // Flag to check if sufficient funds are entered  
                
                for (int attempt = 1; attempt <= 2; attempt++) { // Allow 2 attempts  
                    String insertedCashString = JOptionPane.showInputDialog(String.format("The total price is $%.2f. Please insert cash in dollars: ", totalPrice));  
                    if (insertedCashString == null) { // Check for cancel action  
                        break; // Exit the loop and terminate the program  
                    }  
                    
                    try {  
                        insertedCash = Double.parseDouble(insertedCashString);  
                        // Check if enough cash was inserted  
                        if (insertedCash >= totalPrice) {  
                            sufficientFunds = true; // Set sufficient funds flag  
                            break;  
                        } else {  
                            if (attempt < 2) {  
                                JOptionPane.showMessageDialog(null, "Insufficient funds. Please try again. You have " + (2 - attempt) + " attempt(s) left.");  
                            } else {  
                                JOptionPane.showMessageDialog(null, "Insufficient funds. Transaction cancelled.");  
                            }  
                        }  
                    } catch (NumberFormatException e) {  
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numerical value.");  
                    }  
                }  

                if (sufficientFunds) { // Proceed if sufficient funds were entered  
                    double change = insertedCash - totalPrice; // Calculate change  

                    // Check if the register can give the change  
                    while (!cashRegister.canGiveChange(change)) {  
                        // Prompt user for a smaller or exact amount  
                        String newInsertedCashString = JOptionPane.showInputDialog(String.format("Register has insufficient change. Please input a smaller or exact amount for the total price of $%.2f: ", totalPrice));  
                        
                        if (newInsertedCashString == null) { // Check for cancel action  
                            continue; // Go back to the main menu  
                        }  

                        try {  
                            insertedCash = Double.parseDouble(newInsertedCashString);  
                            change = insertedCash - totalPrice; // Recalculate change after new input  
                        } catch (NumberFormatException e) {  
                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number correctly.");  
                        }  
                    }  

                    // Proceed with the transaction  
                    cashRegister.makeSale(totalPrice); // Make sale and update cash register  
                    String successMessage = String.format("========================================\n" +  
                            "       Dispensing Your Juice!          \n" +  
                            "========================================\n" +  
                            "Thank you for your purchase of %d %s!\n", quantity, juiceNames[choice - 1]);  
                    if (change > 0) {  
                        successMessage += String.format("Your change: $%.2f", change);  
                    }  
                    JOptionPane.showMessageDialog(null, successMessage);  
                    juiceStocks[choice - 1] -= quantity; // Update stock  
                }  

                JOptionPane.showMessageDialog(null, "Going back to the main menu...");  

            } catch (NumberFormatException e) {  
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter a number correctly.");  
            }  
        }  
    }  
}
