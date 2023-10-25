package com.NTT.articoliwebservice.webapp;

import com.NTT.articoliwebservice.dtos.ArticoloDto;
import com.NTT.articoliwebservice.dtos.BarcodeDto;
import com.NTT.articoliwebservice.entities.Articolo;
import com.NTT.articoliwebservice.entities.Barcode;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.addMappings(articoliMapping);
        modelMapper.addMappings(new PropertyMap<Barcode, BarcodeDto>() {
            @Override
            protected void configure() {
                map().setIdTipoArt(source.getIdTipoArt());
            }
        });
        modelMapper.addConverter(articoliConverter);
        return modelMapper;
    }

    PropertyMap<Articolo, ArticoloDto> articoliMapping = new PropertyMap<Articolo, ArticoloDto>() {
        protected void configure() {
            map().setDataCreazione(source.getDataCreaz());
        }
    };

    Converter<String, String> articoliConverter = new Converter<String, String>() {
        @Override
        public String convert(MappingContext<String, String> context) {
            return context.getSource() == null ? "" : context.getSource().trim();
        }
    };


}
