package com.NTT.articoliwebservice.services;

import com.NTT.articoliwebservice.dtos.ArticoloDto;
import com.NTT.articoliwebservice.entities.Articolo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticoloService {
    public Iterable<Articolo> SelTutti();

    public List<ArticoloDto> SelByDescrizione(String descrizione);

    public List<Articolo> SelByDescrizione(String descrizione, Pageable pageable);

    public ArticoloDto SelByCodArt(String codArt);

    public Articolo SelByCodArt2(String codArt);

    public ArticoloDto SelByBarcode(String barcode);

    public void DelArticolo(Articolo articolo);

    public void InsArticolo(Articolo articolo);

}
