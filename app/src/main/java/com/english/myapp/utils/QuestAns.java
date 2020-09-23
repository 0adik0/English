package com.english.myapp.utils;

import java.util.ArrayList;

/**
 * Created by Ramakrishna on 05-Aug-20.
 */
public class QuestAns {

    String question;
    String answer;
    ArrayList<Options> optionsList;

    public QuestAns(String question, String answer, ArrayList<Options> optionsList) {
        this.question = question;
        this.answer = answer;
        this.optionsList = optionsList;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public ArrayList<Options> getOptionsList() {
        return optionsList;
    }
}
