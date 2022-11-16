package org.stock.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payments", schema = "stock")
public class Payment {
    private Long id;
    private String ref;
    private String paymentDate;
    private String type;
    private String status;
    private Trade trade;
    private Order order;

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
    @Column(name = "payment_date", nullable = false, length = 255)
    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(ref, payment.ref) && Objects.equals(paymentDate, payment.paymentDate) && Objects.equals(type, payment.type) && Objects.equals(status, payment.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ref, paymentDate, type, status);
    }

    @ManyToOne
    @JoinColumn(name = "trade_id", referencedColumnName = "id")
    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
