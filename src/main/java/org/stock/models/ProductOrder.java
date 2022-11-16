package org.stock.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_order", schema = "stock")
public class ProductOrder {
    private Long id;
    private int productId;
    private int orderId;
    private int quantity;
    private Product product ;


    public ProductOrder(int productId, int orderId, int quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

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
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
        ProductOrder that = (ProductOrder) o;
        return productId == that.productId && orderId == that.orderId && quantity == that.quantity && Objects.equals(id, that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, productId, orderId, quantity);
    }
}
