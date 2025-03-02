import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        StaffManagementSystem managementSystem = new StaffManagementSystem();

        String menu;
        do {
            System.out.println("============================== STAFF MANAGEMENT SYSTEM ===========================");
            System.out.println("[1]. Insert Employee");
            System.out.println("[2]. Update Employee");
            System.out.println("[3]. Display Employee");
            System.out.println("[4]. Remove Employee");
            System.out.println("[5]. Exit");
            System.out.println("==================================================================================");
            System.out.print("Choose your option: ");
            menu = sc.nextLine();
            switch (menu) {
                case "1": {
                    Map<Integer, Consumer<String>> actions = new HashMap<>();
                    actions.put(1, x -> managementSystem.insertVolunteer());
                    actions.put(2, x -> managementSystem.insertSalariedEmployee());
                    actions.put(3, x -> managementSystem.insertHourlySalaryEmployee());
                    actions.put(4, x -> System.out.println("Returning to previous menu..."));

                    System.out.println("[1]. Volunteer \t\t [2]. Salaried Employee \t\t [3]. Hourly Employee \t\t [4]. Back");
                    System.out.print("Choose Type: ");

                    if (sc.hasNextInt()) {
                        int choice = sc.nextInt();
                        sc.nextLine();
                        actions.getOrDefault(choice, x -> System.out.println("Invalid type")).accept("choice");
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.nextLine();
                    }

                    break;
                }

                case "2": {

                    managementSystem.updateInformation();
                    break;
                }
                case "3": {

                    managementSystem.displayAllType();
                    break;
                }
                case "4": {
                    managementSystem.removeEmployee();
                    break;
                }
                case "5": {
                    System.out.println("Thank you");
                    break;
                }

                default:
                    System.out.println(red + "Invalid option, please try again." + reset);
            }
        }while (!menu.equals("8"));

    }

}