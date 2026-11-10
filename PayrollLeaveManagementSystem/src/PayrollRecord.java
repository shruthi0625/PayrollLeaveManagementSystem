// PayrollRecord.java
// This class calculates the monthly salary (payslip) of an employee.

public class PayrollRecord {

    // Fixed rates used for calculation
    private static final double PF_RATE = 0.12;   // 12% of basic
    private static final double TAX_RATE = 0.05;  // 5% of gross

    private Employee employee;
    private String month;
    private int year;
    private int unpaidLeaves;

    private double grossSalary;
    private double leaveDeduction;
    private double pf;
    private double tax;
    private double netSalary;

    // Constructor
    public PayrollRecord(Employee employee, String month, int year, int unpaidLeaves) {
        this.employee = employee;
        this.month = month;
        this.year = year;
        this.unpaidLeaves = unpaidLeaves;
        calculateSalary();
    }

    // Salary calculation logic
    private void calculateSalary() {
        grossSalary = employee.getGrossSalary();

        double perDaySalary = grossSalary / 30;
        leaveDeduction = unpaidLeaves * perDaySalary;

        double salaryAfterLeave = grossSalary - leaveDeduction;
        pf = employee.getBasicSalary() * PF_RATE;
        tax = salaryAfterLeave * TAX_RATE;

        netSalary = salaryAfterLeave - pf - tax;
    }

    public double getNetSalary() { return netSalary; }

    // Print the payslip
    public void printPayslip() {
        System.out.println("---------------- PAYSLIP ----------------");
        System.out.println("Month       : " + month + " " + year);
        System.out.println("Employee    : " + employee.getName() + " (" + employee.getId() + ")");
        System.out.println("Department  : " + employee.getDepartment());
        System.out.println("-----------------------------------------");
        System.out.printf("Basic Salary      : Rs. %.2f%n", employee.getBasicSalary());
        System.out.printf("HRA               : Rs. %.2f%n", employee.getHra());
        System.out.printf("Allowances        : Rs. %.2f%n", employee.getAllowances());
        System.out.printf("Gross Salary      : Rs. %.2f%n", grossSalary);
        System.out.printf("Unpaid Leave (%d) : Rs. %.2f%n", unpaidLeaves, leaveDeduction);
        System.out.printf("PF (12%%)          : Rs. %.2f%n", pf);
        System.out.printf("Tax (5%%)          : Rs. %.2f%n", tax);
        System.out.println("-----------------------------------------");
        System.out.printf("NET SALARY        : Rs. %.2f%n", netSalary);
        System.out.println("-----------------------------------------");
    }
}
