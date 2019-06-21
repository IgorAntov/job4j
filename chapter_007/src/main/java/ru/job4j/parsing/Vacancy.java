package ru.job4j.parsing;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Vacancy {

    private String status;
    private String name;
    private String text;
    private String link;
    private String data;

    public Vacancy(String name, String text, String link, String data) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.data = data;
    }

    public Vacancy(String status) {
        this.status = "new";

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
