package com.example.demo.entity;

import com.example.demo.status.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;


@Entity
@Getter
// TODO: 6. Dynamic Insert
@DynamicInsert
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'PENDING'")
    private ReservationStatus status;

    public Item(String name, String description, User manager, User owner) {
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.owner = owner;
    }

    public Item() {}
}
