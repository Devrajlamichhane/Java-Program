package FinalProject;

import java.util.Scanner;

public class LogicalFinalProjectAssignment5coding {
    private static double balance = 5000.00;

        public static void main(String[] args) {
            Scanner userInput = new Scanner(System.in);

            System.out.print("What is your role? (1 for Bank Teller, 2 for Account Holder, 3 for Visitor) >>>>> ");
            int roleType = userInput.nextInt();

            if (roleType == 1 || roleType == 2) {
                // verification of Bank Teller & Account Holder
                System.out.print("Enter username: ");
                String username = userInput.next();
                System.out.print("Enter password: ");
                String password = userInput.next();

                int verifiedRole = verifyLogin(username, password);
                if (verifiedRole != roleType) {
                    System.out.println("Incorrect credentials....");
                    System.exit(0);
                }
            } else if (roleType != 3) {
                System.out.println("Invalid role selection....");
                System.exit(0);
            }

            showMenu(roleType);

            System.out.print("Choose one of the transaction options >>>>> ");
            int menuOption = userInput.nextInt();
            boolean isValidOption = validInput(menuOption, roleType);

            if (isValidOption) {
                processTransaction(menuOption, roleType, userInput);
            } else {
                System.out.println("Wrong input... Exiting program...");
                System.exit(0);
            }
        }

        private static int verifyLogin(String username, String password) {
            // login credentials
            if (username.equals("dev") && password.equals("dev123")) {
                return 1;  // Bank Teller
            } else if (username.equals("professor") && password.equals("professor123")) {
                return 2;  // Account Holder
            }
            return 0;  // Invalid login
        }

        private static void showMenu(int role) {
            System.out.println("====================================");
            System.out.println("Bank Of America Banking System Menu");
            System.out.println("=====================================");

            if (role == 1) {
                System.out.println("1. Withdraw money");
                System.out.println("2. Make a certified check");
                System.out.println("3. Balance transfer");
                System.out.println("4. Log out");
            } else if (role == 2) {
                System.out.println("1. Withdraw money");
                System.out.println("3. Balance transfer");
                System.out.println("4. Log out");
            } else if (role == 3) {
                System.out.println("6. Research about Bank");
                System.out.println("7. Open an Account");
                System.out.println("8. Look for locations");
            }
            System.out.println("=====================================");
        }

        private static void processTransaction(int menuOption, int role, Scanner userInput) {
            if (role == 1 || role == 2) { // Bank Teller and Account Holder
                switch (menuOption) {
                    case 1:
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount = userInput.nextDouble();
                        if (withdrawAmount <= 0) {
                            System.out.println("Invalid amount. Amount must be positive.");
                        } else if (withdrawAmount > balance) {
                            System.out.println("Insufficient balance. Withdrawal amount exceeds current balance.");
                        } else {
                            balance -= withdrawAmount;
                            System.out.println("Withdrawal successful. New balance: $" + balance);
                        }
                        break;

                    case 3:
                        System.out.print("Enter the amount to transfer: ");
                        double transferAmount = userInput.nextDouble();
                        if (transferAmount <= 0) {
                            System.out.println("Invalid amount. Amount must be positive.");
                        } else if (transferAmount > balance) {
                            System.out.println("Insufficient balance. Transfer amount exceeds current balance.");
                        } else {
                            balance -= transferAmount;
                            System.out.println("Transfer successful. New balance: $" + balance);
                        }
                        break;

                    case 4:
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid option for your role.");
                }
            } else if (role == 3) {
                switch (menuOption) {
                    case 6:
                        System.out.println("Research about Bank feature selected.");
                        break;
                    case 7:
                        System.out.println("Open an Account feature selected.");
                        break;
                    case 8:
                        System.out.println("Look for locations feature selected.");
                        break;
                    default:
                        System.out.println("Invalid option for Visitor role.");
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
    }

/**
 	[human-to-human]  Withdraw money
 •	[human-to-human]  Make a Certified Check
 	[system-to-system]  Transfer  Balance


 Here Customer can
 certified check Cumtomer Intitialize.
 cash deposite Account holder Initialize
 cash withdraw Account holder initialize
 Balance Transfer Account holder initialize
 logout
 exit
 */