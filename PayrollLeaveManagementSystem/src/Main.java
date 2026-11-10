// Main.java
// Menu driven console program for Payroll & Leave Management System.

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<LeaveRequest> leaves = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int empCounter = 103;
    static int leaveCounter = 103;

    public static void main(String[] args) {

        // Some default data
        employees.add(new Employee("ADM001", "Admin", "admin@company.com", "admin123",
                "Administration", "Administrator", 90000, 30000, 15000, 24, Role.ADMIN));
        employees.add(new Employee("EMP101", "Jane Doe", "jane@company.com", "jane123",
                "Engineering", "Senior Developer", 60000, 20000, 10000, 18, Role.EMPLOYEE));
        employees.add(new Employee("EMP102", "Alex Smith", "alex@company.com", "alex123",
                "Marketing", "Lead Specialist", 50000, 16000, 8000, 18, Role.EMPLOYEE));

        leaves.add(new LeaveRequest("LR-101", "EMP101", "Casual Leave",
                "2026-11-12", "2026-11-13", 2, "Personal work"));

        int choice;
        do {
            System.out.println("\n======================================");
            System.out.println("   PAYROLL & LEAVE MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1: login(); break;
                case 2: register(); break;
                case 3: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }

    // ---------------- LOGIN & REGISTER ----------------

    static void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        for (Employee e : employees) {
            if (e.getEmail().equalsIgnoreCase(email) && e.checkPassword(password)) {
                System.out.println("Login successful! Welcome " + e.getName());
                if (e.getRole() == Role.ADMIN) {
                    adminMenu();
                } else {
                    employeeMenu(e);
                }
                return;
            }
        }
        System.out.println("Wrong email or password!");
    }

    static void register() {
        System.out.println("\n--- Register New Employee ---");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        if (findByEmail(email) != null) {
            System.out.println("Email already registered!");
            return;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Department: ");
        String dept = sc.nextLine();
        System.out.print("Designation: ");
        String desig = sc.nextLine();

        String id = "EMP" + empCounter++;
        // New users get a default salary; Admin can change it later
        employees.add(new Employee(id, name, email, password, dept, desig,
                30000, 10000, 5000, 18, Role.EMPLOYEE));
        System.out.println("Registered successfully! Your ID is " + id + ". Please login.");
    }

    // ---------------- ADMIN MENU ----------------

    static void adminMenu() {
        int ch;
        do {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View All Employees");
            System.out.println("2. Add Employee");
            System.out.println("3. View Leave Requests");
            System.out.println("4. Approve / Reject Leave");
            System.out.println("5. Generate Payslip");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            ch = readInt();

            switch (ch) {
                case 1:
                    for (Employee e : employees) {
                        System.out.println("-------------------------");
                        e.display();
                    }
                    break;
                case 2: addEmployee(); break;
                case 3: showLeaves(null); break;
                case 4: processLeave(); break;
                case 5: generatePayslip(); break;
                case 6: System.out.println("Logged out."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (ch != 6);
    }

    static void addEmployee() {
        System.out.println("\n--- Add Employee ---");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Department: ");
        String dept = sc.nextLine();
        System.out.print("Designation: ");
        String desig = sc.nextLine();
        System.out.print("Basic Salary: ");
        double basic = readDouble();
        System.out.print("HRA: ");
        double hra = readDouble();
        System.out.print("Allowances: ");
        double allow = readDouble();

        String id = "EMP" + empCounter++;
        employees.add(new Employee(id, name, email, password, dept, desig,
                basic, hra, allow, 18, Role.EMPLOYEE));
        System.out.println("Employee added with ID " + id);
    }

    static void processLeave() {
        showLeaves(null);
        System.out.print("Enter Request ID: ");
        String rid = sc.nextLine();

        for (LeaveRequest lr : leaves) {
            if (lr.getRequestId().equalsIgnoreCase(rid)) {
                if (lr.getStatus() != LeaveStatus.PENDING) {
                    System.out.println("This request is already " + lr.getStatus());
                    return;
                }
                System.out.print("1. Approve  2. Reject : ");
                int c = readInt();
                if (c == 1) {
                    lr.approve(findById(lr.getEmployeeId()));
                    System.out.println("Leave Approved.");
                } else {
                    lr.reject();
                    System.out.println("Leave Rejected.");
                }
                return;
            }
        }
        System.out.println("Request not found!");
    }

    static void generatePayslip() {
        System.out.print("Enter Employee ID: ");
        Employee e = findById(sc.nextLine());
        if (e == null) {
            System.out.println("Employee not found!");
            return;
        }
        System.out.print("Number of unpaid leave days: ");
        int unpaid = readInt();
        PayrollRecord pr = new PayrollRecord(e, "November", 2026, unpaid);
        pr.printPayslip();
    }

    // ---------------- EMPLOYEE MENU ----------------

    static void employeeMenu(Employee emp) {
        int ch;
        do {
            System.out.println("\n--- EMPLOYEE MENU ---");
            System.out.println("1. View My Profile");
            System.out.println("2. Apply Leave");
            System.out.println("3. View My Leaves");
            System.out.println("4. View My Payslip");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            ch = readInt();

            switch (ch) {
                case 1: emp.display(); break;
                case 2: applyLeave(emp); break;
                case 3: showLeaves(emp.getId()); break;
                case 4: new PayrollRecord(emp, "November", 2026, 0).printPayslip(); break;
                case 5: System.out.println("Logged out."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (ch != 5);
    }

    static void applyLeave(Employee emp) {
        System.out.print("Leave Type (Casual/Sick/Earned): ");
        String type = sc.nextLine();
        System.out.print("Start Date (YYYY-MM-DD): ");
        String start = sc.nextLine();
        System.out.print("End Date (YYYY-MM-DD): ");
        String end = sc.nextLine();
        System.out.print("Number of days: ");
        int days = readInt();
        System.out.print("Reason: ");
        String reason = sc.nextLine();

        if (days > emp.getRemainingLeaves()) {
            System.out.println("Not enough leave balance! You have " + emp.getRemainingLeaves() + " days.");
            return;
        }

        String id = "LR-" + leaveCounter++;
        leaves.add(new LeaveRequest(id, emp.getId(), type, start, end, days, reason));
        System.out.println("Leave applied. Request ID: " + id + " (PENDING)");
    }

    // ---------------- HELPER METHODS ----------------

    // empId == null means show all leaves
    static void showLeaves(String empId) {
        System.out.println("\n--- Leave Requests ---");
        boolean found = false;
        for (LeaveRequest lr : leaves) {
            if (empId == null || lr.getEmployeeId().equals(empId)) {
                lr.display();
                found = true;
            }
        }
        if (!found) System.out.println("No leave requests.");
    }

    static Employee findById(String id) {
        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(id)) return e;
        }
        return null;
    }

    static Employee findByEmail(String email) {
        for (Employee e : employees) {
            if (e.getEmail().equalsIgnoreCase(email)) return e;
        }
        return null;
    }

    static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    static double readDouble() {
        try {
            return Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
