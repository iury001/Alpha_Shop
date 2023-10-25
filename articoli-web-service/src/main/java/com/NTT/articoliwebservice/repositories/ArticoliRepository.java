package com.NTT.articoliwebservice.repositories;

import com.NTT.articoliwebservice.entities.Articolo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ArticoliRepository extends PagingAndSortingRepository<Articolo, Long> {
    @Query(value = "SELECT * FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
    List<Articolo> SelByDescrizioneLike(@Param("desArt") String descrizione);

    List<Articolo> findByDescrizioneLike(String descrizione, Pageable pageable);

    Articolo findByCodArt(String codArt);

    //JPQL
    @Query(value = "SELECT a FROM Articolo a JOIN a.barcode b WHERE b.barcode IN (:ean)")
    Articolo SelByEan(@Param("ean") String ean);
}
