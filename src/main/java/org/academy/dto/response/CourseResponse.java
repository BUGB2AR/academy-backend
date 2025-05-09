package org.academy.dto.response;

public class CourseResponse {
    private Long id;
    private String name;
    private String code;
    private int durationSemesters;

    public CourseResponse(Long id, String name, String code, int durationSemesters) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.durationSemesters = durationSemesters;
    }

    public CourseResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
