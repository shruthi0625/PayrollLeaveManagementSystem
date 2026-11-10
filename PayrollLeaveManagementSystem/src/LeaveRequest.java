// LeaveRequest.java
// This class stores one leave application made by an employee.

public class LeaveRequest {

    private String requestId;
    private String employeeId;
    private String leaveType;
    private String startDate;
    private String endDate;
    private int days;
    private String reason;
    private LeaveStatus status;

    // Constructor - every new request starts as PENDING
    public LeaveRequest(String requestId, String employeeId, String leaveType,
                        String startDate, String endDate, int days, String reason) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.reason = reason;
        this.status = LeaveStatus.PENDING;
    }

    // Getters
    public String getRequestId() { return requestId; }
    public String getEmployeeId() { return employeeId; }
    public String getLeaveType() { return leaveType; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public int getDays() { return days; }
    public String getReason() { return reason; }
    public LeaveStatus getStatus() { return status; }

    // Admin approves the leave
    public void approve(Employee emp) {
        status = LeaveStatus.APPROVED;
        emp.deductLeave(days);
    }

    // Admin rejects the leave
    public void reject() {
        status = LeaveStatus.REJECTED;
    }

    public void display() {
        System.out.println(requestId + " | " + employeeId + " | " + leaveType + " | "
                + startDate + " to " + endDate + " | " + days + " day(s) | " + status);
    }
}
