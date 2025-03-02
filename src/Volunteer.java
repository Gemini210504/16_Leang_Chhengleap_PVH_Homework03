public class Volunteer extends StaffMember {
    private double salary;

    public Volunteer(String name, String address, int id, double salary) {
        super(name, address, id);

        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public Volunteer setSalary(double salary) {
        this.salary = salary;
        return null;
    }


    @Override
    double pay() {
        return salary;
    }

    @Override
    public String toString() {
        return ">>> Volunteer : \n" +
                " \nsalary= " + salary +
                " \n id= " + id +
                " \n name= " + name +
                " \n address= " + address + "\n------------------------------\n" ;
    }
}
