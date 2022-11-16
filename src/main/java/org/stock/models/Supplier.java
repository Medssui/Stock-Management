package org.stock.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "suppliers", schema = "stock")
public class Supplier {
    private Long id;
    private String email;
    private String tel;
    private String adresse;
    private String name;
    private String type;
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
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "tel", nullable = false)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "adresse", nullable = false, length = 255)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return email == supplier.email && tel == supplier.tel && Objects.equals(id, supplier.id) && Objects.equals(adresse, supplier.adresse) && Objects.equals(name, supplier.name) && Objects.equals(type, supplier.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, tel, adresse, name, type);
    }

    @OneToMany(mappedBy = "supplier")
    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }
}
