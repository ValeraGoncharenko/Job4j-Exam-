package ru.job4j.exam;

/**
 * Вариант ответа.
 * @author Valera Goncharenko (goncharikvv@gmail.com).
 * @version 1.
 * @since 23.04.2021.
 */

public class Option {

    /**
     * id варианта ответа.
     */
    private int id;

    /**
     * Текст варианта ответа.
     */
    private String text;

    public Option(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }


}
