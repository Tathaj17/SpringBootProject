package com.myfirstintellij.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Project")
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Message can not be blank")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    public ProjectTask(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
