public class HourlySalaryEmployee extends StaffMember{
    private int hourWorked;
    private double rate;

    public HourlySalaryEmployee(String name, String address, int id, int hourWorked, double rate) {
        super(name, address,id);
        this.hourWorked = hourWorked;
        this.rate = rate;
    }

    public int getHourWorked() {
        return hourWorked;
    }

    public double getRate() {
        return rate;
    }

    public void setHourWorked(int hourWorked) {
        this.hourWorked = hourWorked;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    double pay() {
        return hourWorked*rate;
    }

    @Override
    public String toString() {
        return "\n>>> HourlySalaryEmployee : " +
                super.toString()+
                "\nHourWorked= " + hourWorked +
                "\nRate= " + rate +
                "\n------------------------------\n";
    }
}
