package ru.job4j.servlets.http;

import java.util.Date;
import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class User {
    private final String id;
    private String name;
    private String login;
    private String email;
    private Date createDate;

    public String toString() {
        return "User{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", login='" + this.login + '\'' + ", email='" + this.email + '\'' + ", createDate=" + this.createDate + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            User user = (User) o;
            return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name) && Objects.equals(this.login, user.login) && Objects.equals(this.email, user.email) && Objects.equals(this.createDate, user.createDate);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.name, this.login, this.email, this.createDate});
    }

    public User(String id, String name, String login, String email, Date createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = null;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
