package com.redhat.atomic.fruit;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "new_entity")
public class NewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dia")
    private Instant dia;

    public Instant getDia() {
        return dia;
    }

    public void setDia(Instant dia) {
        this.dia = dia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}