package org.academy.dto.response;

public class TeacherResponse extends UserResponse {

    private String department;
    private String qualification;

    public TeacherResponse(Long id, String username, String email, String department, String qualification) {
        super(id, username, email);
        this.department = department;
        this.qualification = qualification;
    }

    public TeacherResponse(Long id, String username, String email) {
        super(id, username, email);
    }

    public TeacherResponse() {

    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}