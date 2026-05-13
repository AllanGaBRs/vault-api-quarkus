package org.acme.entity;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "secrets")
public class Secret extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String secretContent;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;
}