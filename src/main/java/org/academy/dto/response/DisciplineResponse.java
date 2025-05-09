package org.academy.dto.response;

public class DisciplineResponse {
    private Long id;
    private String name;
    private String code;
    private int workload;
    private int semester;
    private Long courseId;
    private Long teacherId;

    public DisciplineResponse(Long id, String name, String code, int workload, int semester, Long courseId, Long teacherId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.workload = workload;
        this.semester = semester;
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public DisciplineResponse() {}

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

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}