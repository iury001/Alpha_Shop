package com.NTT.articoliwebservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BARCODE")
@Data
public class Barcode implements Serializable {
    private static final long serialVersionUID = 1853763261962860635L;

    @Id
    @Column(name = "barcode")
    @NotNull(message = "{NotNull.Barcode.barcode.Validation}")
    @Size(min = 8, max = 13, message = "{Size.Barcode.barcode.Validation}")
    private String barcode;

    @Column(name = "IDTIPOART")
    @NotNull(message = "{NotNull.Barcode.idTipoArt.Validation}")
    private String idTipoArt;

    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "CODART",referencedColumnName = "codArt")
    @JsonBackReference
    private Articolo articolo;

}
