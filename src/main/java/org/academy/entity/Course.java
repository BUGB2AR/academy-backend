package org.academy.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course extends PanacheEntity {
    public String name;
    public String code;
    public int durationSemesters;

    @OneToMany(mappedBy = "course")
    public Set<Curriculum> curricula = new HashSet<>();

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

    public Set<Curriculum> getCurricula() {
        return curricula;
    }

    public void setCurricula(Set<Curriculum> curricula) {
        this.curricula = curricula;
    }
}

