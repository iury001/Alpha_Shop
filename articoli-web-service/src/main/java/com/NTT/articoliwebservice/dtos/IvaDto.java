package com.NTT.articoliwebservice.dtos;

import lombok.Data;

@Data
public class IvaDto {
    private String idIva;
    private String descrizione;
    private int aliquota;
}
