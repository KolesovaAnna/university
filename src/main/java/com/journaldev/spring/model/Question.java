package com.journaldev.spring.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanna Kolesava on 12/15/2016.
 */

@Entity
@Table(name="Question")
public class Question {

    @Id
    @Column(name="id")
   // @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int id_test;
    private String title;
    private int right_answers;
    @Embedded
    private List<Answer> answers = new ArrayList<Answer>();

    public List<Answer> getAnswers() {return answers;}

    public void setAnswers(List<Answer> answers) {this.answers = answers;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRight_answers(){return right_answers;}

    public void setRight_answers(int right_answers){this.right_answers=right_answers;}
}
