package com.NTT.articoliwebservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "INGREDIENTI")
@Data
public class Ingrediente implements Serializable {
    private static final long serialVersionUID = 659732485001138522L;

    @Id
    @Column(name = "CODART")
    private String codart;

    @Column(name = "INFO")
    private String info;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Articolo articolo;
}
