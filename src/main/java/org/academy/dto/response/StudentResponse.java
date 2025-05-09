package org.academy.dto.response;

import java.time.LocalDate;

public class StudentResponse extends UserResponse {
    private String registrationNumber;
    private LocalDate enrollmentDate;

    public StudentResponse(Long id, String username, String email) {
        super(id, username, email);
    }

    public StudentResponse() {

    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
