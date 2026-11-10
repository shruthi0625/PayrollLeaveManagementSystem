// Employee.java
// This class stores the details of one employee.
// OOP concept used: Encapsulation (private variables + public getters/setters)

public class Employee {

    private String id;
    private String name;
    private String email;
    private String password;
    private String department;
    private String designation;
    private double basicSalary;
    private double hra;
    private double allowances;
    private int totalLeaves;
    private int usedLeaves;
    private Role role;

    // Constructor
    public Employee(String id, String name, String email, String password, String department,
                    String designation, double basicSalary, double hra, double allowances,
                    int totalLeaves, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.designation = designation;
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.allowances = allowances;
        this.totalLeaves = totalLeaves;
        this.usedLeaves = 0;
        this.role = role;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public double getBasicSalary() { return basicSalary; }
    public double getHra() { return hra; }
    public double getAllowances() { return allowances; }
    public int getTotalLeaves() { return totalLeaves; }
    public int getUsedLeaves() { return usedLeaves; }
    public Role getRole() { return role; }

    // Setters
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
    public void setDepartment(String department) { this.department = department; }

    // Check login password
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    // Leave balance left
    public int getRemainingLeaves() {
        return totalLeaves - usedLeaves;
    }

    // Reduce leave balance when a leave is approved
    public void deductLeave(int days) {
        usedLeaves = usedLeaves + days;
    }

    // Gross salary = basic + HRA + allowances
    public double getGrossSalary() {
        return basicSalary + hra + allowances;
    }

    // Print employee details
    public void display() {
        System.out.println("ID          : " + id);
        System.out.println("Name        : " + name);
        System.out.println("Email       : " + email);
        System.out.println("Department  : " + department);
        System.out.println("Designation : " + designation);
        System.out.println("Gross Salary: Rs. " + getGrossSalary());
        System.out.println("Leave Left  : " + getRemainingLeaves() + " / " + totalLeaves);
        System.out.println("Role        : " + role);
    }
}
