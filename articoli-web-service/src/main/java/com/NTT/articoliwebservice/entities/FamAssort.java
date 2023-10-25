package com.NTT.articoliwebservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "famassort")
@Data
public class FamAssort {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @OneToMany(mappedBy = "famAssort", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Articolo> articoli = new HashSet<>();

}
