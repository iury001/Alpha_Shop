package com.NTT.articoliwebservice.dtos;

import com.NTT.articoliwebservice.entities.Barcode;
import com.NTT.articoliwebservice.entities.FamAssort;
import com.NTT.articoliwebservice.entities.Ingrediente;
import com.NTT.articoliwebservice.entities.Iva;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class ArticoloDto {
    private String codArt;
    private String descrizione;
    private String um;
    private String codStat;
    private Integer pzCart;
    private double pesoNetto;
    private String idStatoArt;
    private Date dataCreazione;
    private double prezzo = 0;

    private Set<Barcode> barcode = new HashSet<>();
    private Ingrediente ingrediente;
    private FamAssort famAssort;
    private Iva iva;


}
