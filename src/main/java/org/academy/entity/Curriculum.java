package org.academy.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "curriculums")
public class Curriculum extends PanacheEntity {
    public String name;
    public int year;
    public boolean active;

    @ManyToOne
    public Course course;

    @ManyToMany
    @JoinTable(
            name = "curriculum_discipline",
            joinColumns = @JoinColumn(name = "curriculum_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id"))
    public Set<Discipline> disciplines = new HashSet<>();

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}