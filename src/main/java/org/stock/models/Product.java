package org.stock.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "stock")
public class Product {
    private Long id;
    private String ref;
    private int price;
    private int quantity;
    private String lable;
    private Category category;
    private List<Trade> trades;
    private List<Order> orders;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }




    @Basic
    @Column(name = "lable", nullable = true, length = 255)
    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }



    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ref", nullable = false, length = 255)
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && quantity == product.quantity && Objects.equals(id, product.id) && Objects.equals(ref, product.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ref, price, quantity);
    }

    @ManyToOne
    @JoinColumn(name = "categorie_id", referencedColumnName = "id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "product")
    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    @ManyToMany(mappedBy = "products")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
