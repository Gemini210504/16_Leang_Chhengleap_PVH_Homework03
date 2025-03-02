public class SalariedEmployee extends StaffMember {
    private double salary;
    private double bonus;

    public SalariedEmployee(String name, String address, int id, double salary, double bonus) {
        super(name, address,id);
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    double pay() {
        return salary + bonus;
    }

    @Override
    public String toString() {
        return "\n>>> Salaried Employee : "+
                super.toString() +
                "\nSalary = " + salary +
                "\nBonus = " + bonus +
                "\n------------------------------\n";
    }
}
