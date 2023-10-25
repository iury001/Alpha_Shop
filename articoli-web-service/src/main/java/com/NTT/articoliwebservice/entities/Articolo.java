package com.NTT.articoliwebservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ARTICOLI")
@Data
public class Articolo implements Serializable {
    private static final long serialVersionUID = 291353626011036772L;

    @Id
    @Column(name = "CODART")
    @Size(min = 5, max = 20, message = "{Size.Articolo.codArt.Validation}")
    @NotNull(message = "{NotNull.Articolo.codArt.Validation}")
    private String codArt;

    @Column(name = "DESCRIZIONE")
    @Size(min = 5, max = 80, message = "{Size.Articolo.descrizione.Validation}")
    private String descrizione;

    @Column(name = "UM")
    private String um;

    @Column(name = "CODSTAT")
    private String codstat;

    @Column(name = "PZCART")
    @Max(value = 99, message = "{Size.Articolo.pzCart.Validation}")
    private Integer pzCart;

    @Column(name = "PESONETTO")
    @Min(value = (long) 0.01, message = "{Min.Articolo.pesoNetto.Validation}")
    private Double pesoNetto;

    @Column(name = "IDSTATOART")
    @NotNull(message = "{NotNull.Articolo.idStatoArt.Validation}")
    private String idStatoArt;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATACREAZIONE")
    private Date dataCreaz;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articolo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Barcode> barcode = new HashSet<>();

    @OneToOne(mappedBy = "articolo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "IDIVA", referencedColumnName = "idIva")
    private Iva iva;

    @ManyToOne
    @JoinColumn(name = "FAMASSORT", referencedColumnName = "ID")
    private FamAssort famAssort;

    public Articolo() {
    }

    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getCodstat() {
        return codstat;
    }

    public void setCodstat(String codstat) {
        this.codstat = codstat;
    }

    public Integer getPzCart() {
        return pzCart;
    }

    public void setPzCart(Integer pzCart) {
        this.pzCart = pzCart;
    }

    public Double getPesoNetto() {
        return pesoNetto;
    }

    public void setPesoNetto(Double pesoNetto) {
        this.pesoNetto = pesoNetto;
    }

    public String getIdStatoArt() {
        return idStatoArt;
    }

    public void setIdStatoArt(String idStatoArt) {
        this.idStatoArt = idStatoArt;
    }

    public Date getDataCreaz() {
        return dataCreaz;
    }

    public void setDataCreaz(Date dataCreaz) {
        this.dataCreaz = dataCreaz;
    }

    public Set<Barcode> getBarcode() {
        return barcode;
    }

    public void setBarcode(Set<Barcode> barcode) {
        this.barcode = barcode;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Iva getIva() {
        return iva;
    }

    public void setIva(Iva iva) {
        this.iva = iva;
    }

    public FamAssort getFamAssort() {
        return famAssort;
    }

    public void setFamAssort(FamAssort famAssort) {
        this.famAssort = famAssort;
    }
}
