import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

//Have 3 section not fully check yet!
public class StaffManagementSystem {
    Scanner sc = new Scanner(System.in);
    int pageSize = 4;
    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String blue = "\u001B[34m";
    String yellow = "\u001B[33m";

    private List<StaffMember> staffMembers = new ArrayList<>(Arrays.asList(
            new Volunteer("Tong Suhan", "Phnom Penh", 1, 0.0),
            new Volunteer("Lim Dara", "Siem Reap", 4, 0.0),
            new SalariedEmployee("Tang Kimhu", "Kompot", 5,300.0, 20),
            new SalariedEmployee("Meas Sreysor", "Kompong Cham", 2,300.0, 10.0),
            new HourlySalaryEmployee("Van Kimsorng", "Battambong", 3, 60, 10),
            new HourlySalaryEmployee("Mey Sovann", "Preas Vihea", 6, 50, 10)

    ));
    public StaffManagementSystem() {
        this.staffMembers.get(0).setId(1);
        this.staffMembers.get(1).setId(4);
        this.staffMembers.get(2).setId(5);
        this.staffMembers.get(3).setId(2);
        this.staffMembers.get(4).setId(3);
        this.staffMembers.get(5).setId(6);
        staffMembers.sort(Comparator.comparingInt(StaffMember::getId));
    }

    int id = staffMembers.size();
    //(Have done)✅
    public void insertVolunteer() {

        try {
            System.out.println("=> Input volunteer details:");
            System.out.println(blue+"ID: " + (id + 1)+reset);
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Address: ");
            String address = sc.nextLine();
            System.out.print("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            id++;
            Volunteer volunteer = new Volunteer(name, address, (id + 1), salary);
            staffMembers.add(volunteer);
            System.out.println(blue+"Volunteer ID["+(id)+"] added successfully!"+ reset);

        } catch (InputMismatchException err) {
            System.out.println(red + "Error input Volunteer!" + reset);
            sc.nextLine();

        }

    }

    //(Have done)✅
    public void insertSalariedEmployee() {
        try {
            System.out.println("=> Input salaried employee details:");
            System.out.println(blue+"ID: " + (id + 1)+reset);
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Address: ");
            String address = sc.nextLine();
            System.out.print("Salary: ");
            double salary = sc.nextDouble();

            System.out.print("Bonus: ");
            double bonus = sc.nextDouble();
            sc.nextLine();
            id++;
            SalariedEmployee salariedEmployee = new SalariedEmployee(name, address, (id + 1), salary, bonus);
            staffMembers.add(salariedEmployee);
            System.out.println(blue+"Salaried Employee ID ["+(id)+"] added successfully!"+reset);
        }catch (InputMismatchException err){
            System.out.println(red+"Error input Salaried Employee!"+reset);
            sc.nextLine();
        }
    }

    //(Have done)✅
    public void insertHourlySalaryEmployee() {
        try {
            System.out.println("=> Input hourly salary employee details:");
            System.out.println(blue+"ID: " + (id + 1)+reset);
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Address: ");
            String address = sc.nextLine();

            System.out.print("Hours Worked: ");
            int hourWorked = sc.nextInt();

            System.out.print("Rate: ");
            double rate = sc.nextDouble();
            sc.nextLine();

            id++;
            HourlySalaryEmployee hourlySalaryEmployee = new HourlySalaryEmployee(name, address, (id + 1), hourWorked, rate);
            staffMembers.add(hourlySalaryEmployee);
            System.out.println(blue+"Hourly Salary Employee ID["+(id)+"] added successfully!"+reset);
        }
        catch (InputMismatchException err){
            System.out.println(red+"Error input Hourly Salary Employee!"+reset);
            sc.nextLine();

        }
    }

    // ចំណុច paginationDisplayEmployee បានធ្វើការផ្ទៀងផ្ទាត់ ដោយប្រើException រួចរាល់ និងដំណើរការតាម Requirement (Done)✅
    public void paginationDisplayEmployee() {

        int totalStaff = staffMembers.size();
        int totalPages = (int) Math.ceil((double) totalStaff / pageSize);

        if (totalStaff == 0) {
            System.out.println("No staff in this company.");
            return;
        }

        int currentPage = 0;

        while (true) {

            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, totalStaff);
            List<String> headerOfTable = new ArrayList<>(Arrays.asList(
                    "Type", "ID", "Name", "Address", "Salary", "Bonus", "Hours", "Rate", "Pay"
            ));
            CellStyle cell = new CellStyle(CellStyle.HorizontalAlign.center);
            Table t = new Table(headerOfTable.size(), BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

            for (int i = 0; i < headerOfTable.size(); i++) {
                t.setColumnWidth(i, 20, 40);
            }

            t.addCell(" Staff List (Page " + (currentPage + 1) + " of " + totalPages + ") ", cell, headerOfTable.size());
            for (String header : headerOfTable) {
                t.addCell(header, cell);
            }

            for (int i = start; i < end; i++) {
                StaffMember staff = staffMembers.get(i);

                t.addCell(staff.getClass().getSimpleName(), cell);
                t.addCell(String.valueOf(staff.getId()), cell);
                t.addCell(staff.getName(), cell);
                t.addCell(staff.getAddress(), cell);

                if (staff instanceof Volunteer) {
                    Volunteer v = (Volunteer) staff;
                    t.addCell(String.valueOf(v.getSalary()), cell);
                    t.addCell("-", cell);
                    t.addCell("-", cell);
                    t.addCell("-", cell);
                    t.addCell(String.valueOf(v.pay()), cell);
                } else if (staff instanceof SalariedEmployee) {
                    SalariedEmployee s = (SalariedEmployee) staff;
                    t.addCell(String.valueOf(s.getSalary()), cell);
                    t.addCell(String.valueOf(s.getBonus()), cell);
                    t.addCell("-", cell);
                    t.addCell("-", cell);
                    t.addCell(String.valueOf(s.pay()), cell);
                } else if (staff instanceof HourlySalaryEmployee) {
                    HourlySalaryEmployee h = (HourlySalaryEmployee) staff;
                    t.addCell("-", cell);
                    t.addCell("-", cell);
                    t.addCell(String.valueOf(h.getHourWorked()), cell);
                    t.addCell(String.valueOf(h.getRate()), cell);
                    t.addCell(String.valueOf(h.pay()), cell);
                }
            }

            System.out.println(t.render());


            int choice;
            while (true) {
                try {
                    System.out.println("1. First Page \t\t\t 2. Next Page \t\t\t 3. Previous Page \t\t\t 4. Last Page \t\t\t 5. Exit");
                    System.out.print("Enter choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1: currentPage = 0; break;
                        case 2:
                            if (currentPage < totalPages - 1) currentPage++;
                            else System.out.println("Already on the last page!");
                            break;
                        case 3:
                            if (currentPage > 0) currentPage--;
                            else System.out.println("Already on the first page!");
                            break;
                        case 4: currentPage = totalPages - 1; break;
                        case 5: return;
                        default:
                            System.out.println("Invalid choice, please choose between [1-5].");
                            continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(red + "Error input pagination!" + reset);
                    sc.nextLine();
                }
            }
        }
    }


    //( Done only for show , it calls method paginationDisplayEmployee() )✅
    public void displayAllType() {
        paginationDisplayEmployee();
    }

    // តារាងសម្រាប់បង្ហាញ Data ដែល Update ទៅតាម type នីមួយៗ (Done)✅
    public void tableUpdate(StaffMember updateStaffMember) {

        List<String> headers = new ArrayList<>(Arrays.asList("Type", "ID", "Name", "Address"));
        List<Integer> columnWidth = new ArrayList<>(Arrays.asList(30, 15, 30, 30));

        if (updateStaffMember instanceof Volunteer) {
            headers.add("Salary");
            headers.add("Pay");
            columnWidth.add(20);
            columnWidth.add(20);
        } else if (updateStaffMember instanceof SalariedEmployee) {
            headers.add("Salary");
            headers.add("Bonus");
            headers.add("Pay");
            columnWidth.add(20);
            columnWidth.add(20);
            columnWidth.add(20);
        } else if (updateStaffMember instanceof HourlySalaryEmployee) {
            headers.add("Hours");
            headers.add("Rate");
            headers.add("Pay");
            columnWidth.add(20);
            columnWidth.add(20);
            columnWidth.add(20);
        }

        CellStyle cell = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t = new Table(headers.size(), BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        for (int i = 0; i < columnWidth.size(); i++) {
            t.setColumnWidth(i, columnWidth.get(i), columnWidth.get(i));
        }

        for (String header : headers) {
            t.addCell(header, cell);
        }

        t.addCell(updateStaffMember.getClass().getSimpleName(), cell);
        t.addCell(String.valueOf(updateStaffMember.getId()), cell);
        t.addCell(updateStaffMember.name, cell);
        t.addCell(updateStaffMember.address, cell);

        if (updateStaffMember instanceof Volunteer) {
            Volunteer v = (Volunteer) updateStaffMember;
            t.addCell(String.valueOf(v.getSalary()), cell);
            t.addCell(String.valueOf(v.pay()), cell);
        } else if (updateStaffMember instanceof SalariedEmployee) {
            SalariedEmployee s = (SalariedEmployee) updateStaffMember;
            t.addCell(String.valueOf(s.getSalary()), cell);
            t.addCell(String.valueOf(s.getBonus()), cell);
            t.addCell(String.valueOf(s.pay()), cell);
        } else if (updateStaffMember instanceof HourlySalaryEmployee) {
            HourlySalaryEmployee h = (HourlySalaryEmployee) updateStaffMember;
            t.addCell(String.valueOf(h.getHourWorked()), cell);
            t.addCell(String.valueOf(h.getRate()), cell);
            t.addCell(String.valueOf(h.pay()), cell);
        }

        System.out.println(t.render());
    }

    // ចំណុច updateInformation បានធ្វើការផ្ទៀងផ្ទាត់ ដោយប្រើException រួចរាល់ និងដំណើរការតាម Requirement (Done)✅
    public void updateInformation() {
        System.out.println("================ Update Employee =================");

        System.out.print("=> Enter or Search ID to update: ");
        int updateId;
        try {
            updateId = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException err) {
            System.out.println(red + "Error input Search ID: Please enter a valid number! " + reset);
            sc.nextLine();
            return;
        }

        StaffMember updateStaffMember = staffMembers.stream()
                .filter(x -> x.getId() == updateId)
                .findFirst()
                .orElse(null);

        if (updateStaffMember == null) {
            System.out.println(red + "Employee with ID " + blue + updateId + reset + red + " not found in system!" + reset);
            return;
        }

        int updateColumn = 0;
        tableUpdate(updateStaffMember);


        while (true) {
            if (updateStaffMember instanceof Volunteer) {
                System.out.println("[1] Name  \t|  [2] Address  \t|  [3] Salary  \t|  [4] Cancel");
            } else if (updateStaffMember instanceof SalariedEmployee) {
                System.out.println("[1] Name  \t|  [2] Address  \t|  [3] Salary  \t|  [4] Bonus  \t|  [5] Cancel");
            } else if (updateStaffMember instanceof HourlySalaryEmployee) {
                System.out.println("[1] Name  \t|  [2] Address  \t|  [3] Hours Worked  \t|  [4] Rate  \t|  [5] Cancel");
            }
            while (true) {
                try {
                    System.out.print("Choose column to update or cancel: ");
                    updateColumn = sc.nextInt();
                    sc.nextLine();

                    if ((updateStaffMember instanceof Volunteer && updateColumn >= 1 && updateColumn <= 4) ||
                            (updateStaffMember instanceof SalariedEmployee && updateColumn >= 1 && updateColumn <= 5) ||
                            (updateStaffMember instanceof HourlySalaryEmployee && updateColumn >= 1 && updateColumn <= 5)) {
                        break;
                    } else {
                        System.out.println(red + "Invalid option! Please choose a valid column." + reset);
                    }
                } catch (InputMismatchException err) {
                    System.out.println(red + "Error input: Please enter a valid number!" + reset);
                    sc.nextLine();
                }
            }




            try {
                switch (updateColumn) {
                    case 1:
                        System.out.print("Change name: ");
                        String name = sc.nextLine();
                        updateStaffMember.setName(name);
                        break;

                    case 2:
                        System.out.print("Change address: ");
                        String address = sc.nextLine();
                        updateStaffMember.setAddress(address);
                        break;

                    case 3:
                        if (updateStaffMember instanceof Volunteer) {
                            System.out.print("Change salary: ");
                            double salary = sc.nextDouble();
                            sc.nextLine();
                            ((Volunteer) updateStaffMember).setSalary(salary);
                        } else if (updateStaffMember instanceof SalariedEmployee) {
                            System.out.print("Change salary: ");
                            double salary = sc.nextDouble();
                            sc.nextLine();
                            ((SalariedEmployee) updateStaffMember).setSalary(salary);
                        } else if (updateStaffMember instanceof HourlySalaryEmployee) {
                            System.out.print("Change hours worked: ");
                            int hoursWorked = sc.nextInt();
                            sc.nextLine();
                            ((HourlySalaryEmployee) updateStaffMember).setHourWorked(hoursWorked);
                        }
                        break;

                    case 4:
                        if (updateStaffMember instanceof SalariedEmployee) {
                            System.out.print("Change bonus: ");
                            double bonus = sc.nextDouble();
                            sc.nextLine();
                            ((SalariedEmployee) updateStaffMember).setBonus(bonus);
                        } else if (updateStaffMember instanceof HourlySalaryEmployee) {
                            System.out.print("Change rate: ");
                            double rate = sc.nextDouble();
                            sc.nextLine();
                            ((HourlySalaryEmployee) updateStaffMember).setRate(rate);
                        } else {
                            System.out.println(red + "You have canceled updating return to menu"+reset);
                            return;
                        }
                        break;

                    case 5:
                        System.out.println(red + "You have canceled updating return to menu"+reset);
                        return;

                    default:
                        System.out.println(red + "Invalid option! No updates made." + reset);
                }
            } catch (InputMismatchException err) {
                System.out.println(red + "Error: Invalid input. Please enter the correct data type!" + reset);
                sc.nextLine();
            }
            tableUpdate(updateStaffMember);

        }
    }

    //(Done)✅
    public void removeEmployee() {
        System.out.println("================ Remove Employee ===================");
        System.out.print("Enter ID to remove: ");

        int removeId;
        try {
            removeId = sc.nextInt();
            boolean removed = staffMembers.removeIf(x -> x.getId() == removeId);
            if (removed) {
                System.out.println(yellow+"Removed employee with ID " + removeId + " successfully."+reset);
            } else {
                System.out.println(red+"Error: Employee with ID " + removeId + " not found!"+reset);
            }
        } catch (InputMismatchException err) {
            System.out.println(red + "Error input remove!" + reset);
            sc.nextLine();
        }
    }

}
