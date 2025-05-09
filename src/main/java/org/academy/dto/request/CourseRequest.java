package org.academy.dto.request;

public class CourseRequest {
    private String name;
    private String code;
    private int durationSemesters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDurationSemesters() {
        return durationSemesters;
    }

    public void setDurationSemesters(int durationSemesters) {
        this.durationSemesters = durationSemesters;
    }
}
