package com.NTT.articoliwebservice.services;

import com.NTT.articoliwebservice.dtos.CategoriaDto;
import com.NTT.articoliwebservice.entities.FamAssort;
import com.NTT.articoliwebservice.repositories.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class CategorieServiceImpl implements CategorieService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<CategoriaDto> SelTutti() {
        List<FamAssort> categorie = categoriaRepository.findAll();

        List<CategoriaDto> retVal = categorie
                .stream()
                .map(source -> modelMapper.map(source, CategoriaDto.class))
                .collect(Collectors.toList());

        return retVal;
    }

}
