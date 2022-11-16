package org.stock.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "stock")
@NamedQuery(name="authenticate",query = "select count(u) from User u where u.username =:username and u.password=:password")
public class User {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private List<Order> orders;
    private List<Trade> trades;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 255)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 255)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstname, lastname);
    }

    @OneToMany(mappedBy = "user")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @OneToMany(mappedBy = "user")
    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }
}
