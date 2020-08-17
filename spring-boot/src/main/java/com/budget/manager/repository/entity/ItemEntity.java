package com.budget.manager.repository.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = -1410555778645100595L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "item_sequence_generator")
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String itemId;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity userDetails;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", itemId='" + itemId + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", userDetails=" + userDetails +
                '}';
    }
}
