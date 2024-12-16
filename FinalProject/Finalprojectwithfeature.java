package FinalProject;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Finalprojectwithfeature {


        private static double balance = 5000.00;
        private static final String FILE_NAME = "account_balance.txt";
        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            // Load previous balance from file if exists
            loadBalanceFromFile();

            String roleInput = JOptionPane.showInputDialog(null,
                    "What is your role? (1 for Bank Teller, 2 for Account Holder, 3 for Visitor)",
                    "Role Selection", JOptionPane.QUESTION_MESSAGE);
            int roleType;

            try {
                roleType = Integer.parseInt(roleInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid role selection. Exiting program.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (roleType == 1 || roleType == 2) {
                // Verification of Bank Teller & Account Holder
                String username = JOptionPane.showInputDialog(null, "Enter username:", "Login", JOptionPane.QUESTION_MESSAGE);
                String password = JOptionPane.showInputDialog(null, "Enter password:", "Login", JOptionPane.QUESTION_MESSAGE);

                int verifiedRole = verifyLogin(username, password);
                if (verifiedRole != roleType) {
                    JOptionPane.showMessageDialog(null, "Incorrect credentials. Exiting program.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (roleType != 3) {
                JOptionPane.showMessageDialog(null, "Invalid role selection. Exiting program.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            showMenu(roleType);

            String menuOptionInput = JOptionPane.showInputDialog(null, "Choose one of the transaction options:",
                    "Transaction Menu", JOptionPane.QUESTION_MESSAGE);
            int menuOption;

            try {
                menuOption = Integer.parseInt(menuOptionInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Exiting program.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean isValidOption = validInput(menuOption, roleType);

            if (isValidOption) {
                processTransaction(menuOption, roleType);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong input. Exiting program.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Save balance to file after transaction
            saveBalanceToFile();
        }

        private static int verifyLogin(String username, String password) {
            // Login credentials
            if (username.equals("dev") && password.equals("dev123")) {
                return 1;  // Bank Teller
            } else if (username.equals("professor") && password.equals("professor123")) {
                return 2;  // Account Holder
            }
            return 0;  // Invalid login
        }

        private static void showMenu(int role) {
            StringBuilder menu = new StringBuilder("Bank Of America Banking System Menu\n====================================\n");

            if (role == 1) {
                menu.append("1. Withdraw money\n")
                        .append("2. Make a certified check\n")
                        .append("3. Balance transfer\n")
                        .append("4. Log out\n");
            } else if (role == 2) {
                menu.append("1. Withdraw money\n")
                        .append("3. Balance transfer\n")
                        .append("4. Log out\n");
            } else if (role == 3) {
                menu.append("6. Research about Bank\n")
                        .append("7. Open an Account\n")
                        .append("8. Look for locations\n");
            }

            menu.append("====================================");

            System.out.println(menu.toString());
            JOptionPane.showMessageDialog(null, menu.toString(), "Menu", JOptionPane.INFORMATION_MESSAGE);
        }

        private static void processTransaction(int menuOption, int role) {
            if (role == 1 || role == 2) {
                switch (menuOption) {
                    case 1:
                        String withdrawInput = JOptionPane.showInputDialog(null, "Enter the amount to withdraw:",
                                "Withdrawal", JOptionPane.QUESTION_MESSAGE);
                        try {
                            double withdrawAmount = Double.parseDouble(withdrawInput);
                            if (withdrawAmount <= 0) {
                                JOptionPane.showMessageDialog(null, "Invalid amount. Amount must be positive.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else if (withdrawAmount > balance) {
                                JOptionPane.showMessageDialog(null, "Insufficient balance. Withdrawal amount exceeds current balance.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                balance -= withdrawAmount;
                                String successMessage = String.format("Withdrawal successful. New balance: $%.2f", balance);
                                System.out.println(successMessage);
                                JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid input. Transaction failed.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 3:
                        String transferInput = JOptionPane.showInputDialog(null, "Enter the amount to transfer:",
                                "Balance Transfer", JOptionPane.QUESTION_MESSAGE);
                        try {
                            double transferAmount = Double.parseDouble(transferInput);
                            if (transferAmount <= 0) {
                                JOptionPane.showMessageDialog(null, "Invalid amount. Amount must be positive.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else if (transferAmount > balance) {
                                JOptionPane.showMessageDialog(null, "Insufficient balance. Transfer amount exceeds current balance.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                balance -= transferAmount;
                                String successMessage = String.format("Transfer successful. New balance: $%.2f", balance);
                                System.out.println(successMessage);
                                JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid input. Transaction failed.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 4:
                        String logoutMessage = "Logging out...";
                        System.out.println(logoutMessage);
                        JOptionPane.showMessageDialog(null, logoutMessage, "Logout", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option for your role.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (role == 3) {
                switch (menuOption) {
                    case 6:
                        String researchMessage = "Research about Bank feature selected.";
                        System.out.println(researchMessage);
                        JOptionPane.showMessageDialog(null, researchMessage, "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 7:
                        String openAccountMessage = "Open an Account feature selected.";
                        System.out.println(openAccountMessage);
                        JOptionPane.showMessageDialog(null, openAccountMessage, "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 8:
                        String locationsMessage = "Look for locations feature selected.";
                        System.out.println(locationsMessage);
                        JOptionPane.showMessageDialog(null, locationsMessage, "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option for Visitor role.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private static boolean validInput(int input, int role) {
            if (role == 1 || role == 2) {
                return input >= 1 && input <= 4;
            } else if (role == 3) {
                return input >= 6 && input <= 8;
            }
            return false;
        }

        private static void loadBalanceFromFile() {
            try {
                File file = new File(FILE_NAME);
                if (file.exists()) {
                    Scanner fileScanner = new Scanner(file);
                    if (fileScanner.hasNextDouble()) {
                        balance = fileScanner.nextDouble();
                    }
                    fileScanner.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error loading balance from file.");
            }
        }

        private static void saveBalanceToFile() {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                writer.println(balance);
            } catch (IOException e) {
                System.out.println("Error saving balance to file.");
            }
        }
    }
