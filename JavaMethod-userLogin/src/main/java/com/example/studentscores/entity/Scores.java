package com.example.studentscores.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-04
 */
public class Scores implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成绩表主键
     */
    private Integer id;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 科目
     */
    private String subject;

    /**
     * 成绩
     */
    private String score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Scores{" +
            "id=" + id +
            ", studentId=" + studentId +
            ", subject=" + subject +
            ", score=" + score +
        "}";
    }
}
