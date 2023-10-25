package com.NTT.articoliwebservice.services;

import com.NTT.articoliwebservice.dtos.ArticoloDto;
import com.NTT.articoliwebservice.entities.Articolo;
import com.NTT.articoliwebservice.repositories.ArticoliRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ArticoloServiceImpl implements ArticoloService {
    @Autowired
    ArticoliRepository articoliRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<Articolo> SelTutti() {
        return articoliRepository.findAll();
    }

    @Override
    public List<Articolo> SelByDescrizione(String descrizione, Pageable pageable) {
        return articoliRepository.findByDescrizioneLike(descrizione, pageable);
    }

    @Override
    public List<ArticoloDto> SelByDescrizione(String descrizione) {
        String filter = "%" + descrizione.toUpperCase() + "%";

        List<Articolo> articoli = articoliRepository.SelByDescrizioneLike(filter);

        List<ArticoloDto> retVal = articoli
                .stream()
                .map(source -> modelMapper.map(source, ArticoloDto.class))
                .collect(Collectors.toList());

        return retVal;
    }

    private ArticoloDto ConvertToDto(Articolo articoli) {
        ArticoloDto articoliDto = null;


        if (articoli != null) {
            articoliDto = modelMapper.map(articoli, ArticoloDto.class);
        }

        return articoliDto;
    }

    @Override
    public ArticoloDto SelByBarcode(String barcode) {
        Articolo articoli = articoliRepository.SelByEan(barcode);

        return this.ConvertToDto(articoli);

    }

    @Override
    public ArticoloDto SelByCodArt(String codArt) {
        Articolo articoli = articoliRepository.findByCodArt(codArt);

        return this.ConvertToDto(articoli);
    }

    @Override
    public Articolo SelByCodArt2(String codArt) {

        return articoliRepository.findByCodArt(codArt);
    }

    @Override
    @Transactional
    public void DelArticolo(Articolo articolo) {
        articoliRepository.delete(articolo);
    }

    @Override
    @Transactional
    public void InsArticolo(Articolo articolo) {
        articolo.setDescrizione(articolo.getDescrizione().toUpperCase());

        articoliRepository.save(articolo);
    }
}
