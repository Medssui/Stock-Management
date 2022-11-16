package org.stock.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trades", schema = "stock")
public class Trade {
    private Long id;
    private String ref;
    private int unitPrice;
    private int quantity;
    private int ttcPrice;
    private String applicationDate;
    private String deliveryDate;
    private String deliveryVendor;
    private List<Payment> payments;
    private Supplier supplier;
    private Product product;
    private User user;

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
    @Column(name = "ref", nullable = false, length = 255)
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Basic
    @Column(name = "unit_price", nullable = false, precision = 0)
    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "TTC_price", nullable = false, precision = 0)
    public int getTtcPrice() {
        return ttcPrice;
    }

    public void setTtcPrice(int ttcPrice) {
        this.ttcPrice = ttcPrice;
    }

    @Basic
    @Column(name = "application_date", nullable = false, length = 255)
    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Basic
    @Column(name = "delivery_date", nullable = false, length = 255)
    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Basic
    @Column(name = "delivery_vendor", nullable = false, length = 255)
    public String getDeliveryVendor() {
        return deliveryVendor;
    }

    public void setDeliveryVendor(String deliveryVendor) {
        this.deliveryVendor = deliveryVendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return unitPrice == trade.unitPrice && quantity == trade.quantity && ttcPrice == trade.ttcPrice && Objects.equals(id, trade.id) && Objects.equals(ref, trade.ref) && Objects.equals(applicationDate, trade.applicationDate) && Objects.equals(deliveryDate, trade.deliveryDate) && Objects.equals(deliveryVendor, trade.deliveryVendor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ref, unitPrice, quantity, ttcPrice, applicationDate, deliveryDate, deliveryVendor);
    }

    @OneToMany(mappedBy = "trade")
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
