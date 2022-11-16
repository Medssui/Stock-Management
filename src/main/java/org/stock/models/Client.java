package org.stock.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients", schema = "stock")
public class Client {
    private Long id;
    private String firstname;
    private String lastname;
    private String tel;
    private String email;
    private List<Order> orders;

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

    @Basic
    @Column(name = "tel", nullable = false, length = 255)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(firstname, client.firstname) && Objects.equals(lastname, client.lastname) && Objects.equals(tel, client.tel) && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, tel, email);
    }

    @OneToMany(mappedBy = "client")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
