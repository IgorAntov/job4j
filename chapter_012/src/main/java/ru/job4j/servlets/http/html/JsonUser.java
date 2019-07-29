package ru.job4j.servlets.http.html;

import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class JsonUser {
    private String email;
    private String name;
    private String surname;
    private String comment;
    private String sex;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonUser jsonUser = (JsonUser) o;
        return Objects.equals(email, jsonUser.email)
                && Objects.equals(name, jsonUser.name)
                && Objects.equals(surname, jsonUser.surname)
                && Objects.equals(comment, jsonUser.comment)
                && Objects.equals(sex, jsonUser.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, surname, comment, sex);
    }
}
