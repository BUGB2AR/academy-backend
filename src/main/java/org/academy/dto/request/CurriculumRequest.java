package org.academy.dto.request;

import java.util.Set;

public class CurriculumRequest {
    private String name;
    private int year;
    private boolean active;
    private Long courseId;
    private Set<Long> disciplineIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Set<Long> getDisciplineIds() {
        return disciplineIds;
    }

    public void setDisciplineIds(Set<Long> disciplineIds) {
        this.disciplineIds = disciplineIds;
    }
}