package org.stock.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "stock")
public class Category {
    private Long id;
    private String code;
    private String lable;
    private List<Product> products;

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
    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "lable", nullable = false, length = 255)
    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return code == category.code && Objects.equals(id, category.id) && Objects.equals(lable, category.lable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, lable);
    }

    @OneToMany(mappedBy = "category")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
