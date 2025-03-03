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
        tableMenuManagement tableMenu = new tableMenuManagement();
        String menu;
        do {
            tableMenu.tableMenuChoice();
            menu = sc.nextLine();
            switch (menu) {
                case "1": {
                    Map<Integer, Consumer<String>> actions = new HashMap<>();

                    actions.put(1, x -> managementSystem.insertVolunteer());
                    actions.put(2, x -> managementSystem.insertSalariedEmployee());
                    actions.put(3, x -> managementSystem.insertHourlySalaryEmployee());
                    actions.put(4, x -> System.out.println(blue+"Returning to previous menu..."+reset));

                    while (true) {
                    System.out.println("[1]. Volunteer \t\t [2]. Salaried Employee \t\t [3]. Hourly Employee \t\t [4]. Back");
                    System.out.print("Choose Type: ");
                        if (sc.hasNextInt()) {
                            int choice = sc.nextInt();
                            sc.nextLine();

                            actions.getOrDefault(choice, x -> System.out.println("Invalid type! >>> ")).accept("choice");
                            if (choice == 4) {
                                break;
                            }
                        } else {
                            System.out.println(blue+"Invalid input. Please enter a number."+reset);
                            sc.nextLine();
                            break;
                        }
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
                    System.out.println(blue+"Thank you😊🙏❤️"+reset);
                    break;
                }

                default:
                    System.out.println(red + "Invalid option, please try again." + reset);
            }
        }while (!menu.equals("5"));

    }

}