abstract class StaffMember {
    protected int id;
    protected String name;
    protected String address;
    private static int count = 0;

    public StaffMember(String name, String address, int id) {
        this.name = name;
        this.address = address;
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Volunteer setName(String name) {
        this.name = name;
        return null;
    }

    public String getAddress() {
        return address;
    }

    public Volunteer setAddress(String address) {
        this.address = address;
        return null;
    }

    public static int getCount() {
        return count;
    }

    abstract double pay();

    @Override
    public String toString() {
        return
                "\nID = " + id +
                "\nName = " + name +
                "\nAddress = " + address;
    }
}
