package org.stock.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "stock")
@NamedQuery(name="productquantitys",query = "select po.quantity from ProductOrder po where  po.productId in (:products)")
public class Order implements Serializable {
    private Long id;
    private String ref;
    private int price;
    private int tax;
    private int ttcPrice;
    private String status;
    private Date applicationDate;
    private Date deliveryDate;
    private String deliveryVendor;
    private Client client;
    private User user;
    private List<Payment> payments;
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
    @Column(name = "tax", nullable = false, precision = 0)
    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
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
    @Column(name = "status", nullable = false, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "application_date", nullable = false)
    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Basic
    @Column(name = "delivery_date", nullable = false)
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
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
        Order order = (Order) o;
        return price == order.price && tax == order.tax && ttcPrice == order.ttcPrice && Objects.equals(id, order.id) && Objects.equals(ref, order.ref) && Objects.equals(status, order.status) && Objects.equals(applicationDate, order.applicationDate) && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(deliveryVendor, order.deliveryVendor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ref, price, tax, ttcPrice, status, applicationDate, deliveryDate, deliveryVendor);
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "order")
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @ManyToMany
    @JoinTable(name = "product_order", catalog = "", schema = "stock", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false))
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
