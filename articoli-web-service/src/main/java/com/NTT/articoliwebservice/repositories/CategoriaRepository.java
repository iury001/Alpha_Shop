package com.NTT.articoliwebservice.repositories;

import com.NTT.articoliwebservice.entities.FamAssort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<FamAssort, Integer> {
}
