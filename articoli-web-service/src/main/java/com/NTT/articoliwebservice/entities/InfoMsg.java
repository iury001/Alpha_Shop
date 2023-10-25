package com.NTT.articoliwebservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class InfoMsg {
    public LocalDate data;
    private String message;
}
