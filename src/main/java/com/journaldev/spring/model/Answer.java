package com.journaldev.spring.model;

import javax.persistence.*;

/**
 * Created by Hanna Kolesava on 12/15/2016.
 */

@Entity
@Table(name="Answer")
public class Answer {
    @Id
    @Column(name="id")
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int id_question;
    private String choice;
    private boolean is_right;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_question() { return id_question; }

    public void setId_question(int id_question) { this.id_question = id_question; }

    public String getChoice() {
        return choice;
    }

    public void setСhoice(String choice) {
        this.choice = choice;
    }

    public boolean getIs_right() {
        return is_right;
    }

    public void setId(boolean is_right) {
        this.is_right = is_right;
    }
}
