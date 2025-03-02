import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;
import java.util.stream.Collectors;

public class StaffManagementSystem {
    Scanner sc = new Scanner(System.in);
    int pageSize = 4;

    List<StaffMember> staffMembers = new ArrayList<>(Arrays.asList(
            new Volunteer("Tina", "PP", 1, 0.0),
            new Volunteer("Lee", "SR", 4, 0.0),
            new SalariedEmployee("Fy", "KT", 5,300.0, 20),
            new SalariedEmployee("Dana", "KPS", 2,300.0, 10.0),
            new HourlySalaryEmployee("Sokha", "BTB", 3, 60, 10),
            new HourlySalaryEmployee("Ka", "PV", 6, 50, 10)

    ));
//    List<StaffMember> staffMembers = new ArrayList<>();
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

    public void insertVolunteer() {
        System.out.println("=> Input volunteer details:");
        System.out.println("ID: " + (id + 1));
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        ++id;
        Volunteer volunteer = new Volunteer(name,address,id, salary);
        staffMembers.add(volunteer);
        System.out.println("Volunteer added successfully!");
    }

    public void insertSalariedEmployee() {
        System.out.println("=> Input salaried employee details:");
        System.out.println("ID: " + (id + 1));
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Salary: ");
        double salary = sc.nextDouble();

        System.out.print("Bonus: ");
        double bonus = sc.nextDouble();
        sc.nextLine();

        ++id;
        SalariedEmployee salariedEmployee = new SalariedEmployee(name,address,id, salary, bonus);
        staffMembers.add(salariedEmployee);
        System.out.println("SalariedEmployee added successfully!");
    }

    public void insertHourlySalaryEmployee() {
        System.out.println("=> Input hourly salary employee details:");
        System.out.println("ID: " + (id + 1));
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("Hours Worked: ");
        int hourWorked = sc.nextInt();

        System.out.print("Rate: ");
        double rate = sc.nextDouble();
        sc.nextLine();

        ++id;
        HourlySalaryEmployee hourlySalaryEmployee = new HourlySalaryEmployee(name,address, id , hourWorked, rate);
        staffMembers.add(hourlySalaryEmployee);
        System.out.println("HourlySalaryEmployee added successfully!");
    }




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
            CellStyle cell = new CellStyle(CellStyle.HorizontalAlign.center);
            Table t = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

            for (int i = 0; i < 9; i++) {
                t.setColumnWidth(i, 15, 20);
            }

            t.addCell("Staff List (Page " + (currentPage + 1) + " of " + totalPages + ")", cell, 9);
            t.addCell("Type", cell);
            t.addCell("ID", cell);
            t.addCell("Name", cell);
            t.addCell("Address", cell);
            t.addCell("Salary", cell);
            t.addCell("Bonus", cell);
            t.addCell("Hours", cell);
            t.addCell("Rate", cell);
            t.addCell("Pay", cell);

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

            System.out.println("1. First Page \t\t\t 2. Next Page \t\t\t 3. Previous Page \t\t\t 4. Last Page \t\t\t 5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                currentPage = 0;
            } else if (choice == 2) {
                if (currentPage < totalPages - 1) {
                    currentPage++;
                } else {
                    System.out.println("Already on the last page!");
                }
            } else if (choice == 3) {
                if (currentPage > 0) {
                    currentPage--;
                } else {
                    System.out.println("Already on the first page!");
                }
            } else if (choice == 4) {
                currentPage = totalPages - 1;
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Choose only between [1-5]!");
            }
        }
    }


    public void displayAllType() {
//        staffMembers.get(0).setId(1);
//        staffMembers.get(1).setId(4);
//        staffMembers.get(2).setId(5);
//        staffMembers.get(3).setId(2);
//        staffMembers.get(4).setId(3);
//        staffMembers.get(5).setId(6);
//
//        staffMembers.sort(Comparator.comparingInt(StaffMember::getId));
        paginationDisplayEmployee();
    }

    public void tableUpdate(StaffMember updateStaffMember, int updateColumn) {

        List<String> headers = new ArrayList<>(Arrays.asList("Type", "ID", "Name", "Address"));
        List<Integer> columnWidth = new ArrayList<>(Arrays.asList(15, 15, 30, 30));

        if (updateStaffMember instanceof Volunteer) {
            headers.add("Salary");
            headers.add("Pay");
            columnWidth.add(15);
            columnWidth.add(15);
        } else if (updateStaffMember instanceof SalariedEmployee) {
            headers.add("Salary");
            headers.add("Bonus");
            headers.add("Pay");
            columnWidth.add(15);
            columnWidth.add(15);
            columnWidth.add(15);
        } else if (updateStaffMember instanceof HourlySalaryEmployee) {
            headers.add("Hours");
            headers.add("Rate");
            headers.add("Pay");
            columnWidth.add(15);
            columnWidth.add(15);
            columnWidth.add(15);
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

    public void updateInformation() {
        System.out.println("================ Update Employee =================");

        System.out.print("=> Enter or Search ID to update: ");
        int updateId = sc.nextInt();
        sc.nextLine();
        StaffMember updateStaffMember = staffMembers.stream()
                .filter(x -> x.getId() == updateId)
                .findFirst()
                .orElse(null);

        if (updateStaffMember != null) {
            int updateColumn = 0;
            tableUpdate(updateStaffMember, updateColumn);

            if (updateStaffMember instanceof Volunteer) {
                System.out.println("[1]. Name \t\t [2]. Address \t\t [3]. Salary \t\t [4]. Cancel");
            } else if (updateStaffMember instanceof SalariedEmployee) {
                System.out.println("[1]. Name \t\t [2]. Address \t\t [3]. Salary \t\t [4]. Bonus \t\t [5]. Cancel");
            } else if (updateStaffMember instanceof HourlySalaryEmployee) {
                System.out.println("[1]. Name \t\t [2]. Address \t\t [3]. HoursWorked \t\t [4]. Rate \t\t [5]. Cancel");
            }

            System.out.print("Choose column to update: ");
            updateColumn = sc.nextInt();
            sc.nextLine();

            if (updateColumn == 1) {
                System.out.print("Change name: ");
                String name = sc.nextLine();
                updateStaffMember.setName(name);
            } else if (updateColumn == 2) {
                System.out.print("Change address: ");
                String address = sc.nextLine();
                updateStaffMember.setAddress(address);
            } else if (updateColumn == 3) {
                if (updateStaffMember instanceof Volunteer) {
                    System.out.println("Change salary: ");
                    double salary = sc.nextDouble();
                    ((Volunteer) updateStaffMember).setSalary(salary);
                } else if (updateStaffMember instanceof SalariedEmployee) {
                    System.out.println("Change salary: ");
                    double salary = sc.nextDouble();
                    ((SalariedEmployee) updateStaffMember).setSalary(salary);
                }
            } else if (updateColumn == 4) {
                if (updateStaffMember instanceof SalariedEmployee) {
                    System.out.println("Change bonus: ");
                    double bonus = sc.nextDouble();
                    ((SalariedEmployee) updateStaffMember).setBonus(bonus);
                } else if (updateStaffMember instanceof HourlySalaryEmployee) {
                    System.out.println("Change hours worked: ");
                    int hoursWorked = sc.nextInt();
                    ((HourlySalaryEmployee) updateStaffMember).setHourWorked(hoursWorked);
                }
            } else if (updateColumn == 5) {
                return;
            }

            tableUpdate(updateStaffMember, updateColumn);
        } else {
            System.out.println("Error: Employee with ID " + updateId + " not found!");
        }
    }


    public void removeEmployee() {
        System.out.println("================ Remove Employee ===================");
        System.out.print("Enter ID to remove: ");
        int removeId = sc.nextInt();

        // Try to remove the employee based on the given ID
        boolean removed = staffMembers.removeIf(x -> x.getId() == removeId);

        if (removed) {
            System.out.println("Removed employee with ID " + removeId + " successfully.");
        } else {
            System.out.println("Error: Employee with ID " + removeId + " not found!");
        }
    }


}
