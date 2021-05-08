package ru.job4j.exam;

import java.util.List;

/**
 * Вопрос с вариантами ответов и правильным ответом.
 * @author Valera Goncharenko (goncharikvv@gmail.com).
 * @version 1.
 * @since 23.04.2021.
 */

public class Question {

    private int id;
    private String text;
    private List<Option> options;
    private int answer;

    public Question(int id, String text, List<Option> options, int answer) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
