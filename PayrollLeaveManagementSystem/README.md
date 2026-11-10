# Payroll & Leave Management System

A Payroll, Attendance and Leave Management project built with **Java (OOP)** for the backend logic and **HTML / JavaScript** for the web interface.

## 📁 Project Structure

```
PayrollLeaveManagementSystem/
├── README.md
├── src/
│   ├── Main.java            → Menu-driven console program (login, register, admin & employee menus)
│   ├── Employee.java        → Employee details, salary and leave balance
│   ├── LeaveRequest.java    → One leave application (approve / reject)
│   ├── LeaveStatus.java     → Enum: PENDING, APPROVED, REJECTED
│   ├── PayrollRecord.java   → Payslip calculation (HRA, PF, Tax, unpaid leave)
│   └── Role.java            → Enum: ADMIN, EMPLOYEE
└── web/
    └── index.html           → Web dashboard
```

## 🚀 How to Run in Visual Studio Code

### Option 1: Web Interface
1. Open VS Code → **File > Open Folder** → choose `PayrollLeaveManagementSystem`.
2. Install the **Live Server** extension.
3. Right-click `web/index.html` → **Open with Live Server**.
4. Log in with a demo account, or click **Register** to create a new employee account.

| Role     | Email               | Password  |
|----------|---------------------|-----------|
| Admin    | admin@company.com   | admin123  |
| Employee | jane@company.com    | jane123   |
| Employee | alex@company.com    | alex123   |

> New registrations and changes are saved in the browser (localStorage). To reset to the demo data, clear the site data in your browser.

### Option 2: Java Console Program
1. Install Java JDK 11 or above.
2. Open a terminal in VS Code (`Ctrl + ~`) inside the project folder and run:
   ```bash
   javac -d bin src/*.java
   java -cp bin Main
   ```
3. Use the menu to Login / Register. The same demo accounts work here.

## 🌟 Features

**Login & Register**
- Login with email and password.
- New users can register as an employee (default salary is assigned; Admin can manage it).

**Admin**
- Employee Management: view all employees, **add new employees**, delete employees.
- Approve or reject leave requests.
- Generate payslips for any employee.
- Reports: department-wise payroll chart.

**Employee**
- Apply for leave (leave balance is checked).
- View own leave requests, attendance and payslip.
- Clock In / Clock Out.

**Dashboard**
- Click any card to see its details:
  - **Leave Balance** → leave quota, used and remaining days + leave history
  - **Monthly Base Salary** → salary breakdown (Admin sees all employees' salaries)
  - **Pending Requests** → list of pending leaves (Admin can approve/reject here)
  - **Total Staff Count** → list of all staff members

## 🧮 Salary Calculation (PayrollRecord.java)
```
Gross Salary    = Basic + HRA + Allowances
Leave Deduction = Unpaid leave days × (Gross / 30)
PF              = 12% of Basic
Tax             = 5% of (Gross − Leave Deduction)
Net Salary      = Gross − Leave Deduction − PF − Tax
```
All amounts are in Indian Rupees (₹).

## 🧱 OOP Concepts Used
- **Class & Object** – `Employee`, `LeaveRequest`, `PayrollRecord` objects are created in `Main`.
- **Encapsulation** – all fields are `private` and accessed through getters/setters.
- **Constructor** – used to initialise every object.
- **Enum** – `Role` and `LeaveStatus` for fixed values.
- **Abstraction** – salary calculation is hidden inside `PayrollRecord.calculateSalary()`.
- **Collections** – `ArrayList` stores employees and leave requests.
