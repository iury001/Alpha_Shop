package com.NTT.articoliwebservice.controllers;

import com.NTT.articoliwebservice.dtos.ArticoloDto;
import com.NTT.articoliwebservice.entities.Articolo;
import com.NTT.articoliwebservice.entities.InfoMsg;
import com.NTT.articoliwebservice.exceptions.BindingException;
import com.NTT.articoliwebservice.exceptions.DuplicateException;
import com.NTT.articoliwebservice.exceptions.NotFoundException;
import com.NTT.articoliwebservice.services.ArticoloService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/articoli")
@Log
@CrossOrigin("http://localhost:4200")
public class ArticoloController {
    @Autowired
    private ArticoloService articoliService;

    @Autowired
    private ResourceBundleMessageSource errMessage;

    @GetMapping(value = "/cerca/barcode/{ean}", produces = "application/json")
    public ResponseEntity<ArticoloDto> listArtByEan(@PathVariable("ean") String Ean) throws NotFoundException {
        log.info("****** Otteniamo l'articolo con barcode " + Ean + " *******");

        ArticoloDto articolo = articoliService.SelByBarcode(Ean);

        if (articolo == null) {
            String ErrMsg = String.format("Il barcode %s non � stato trovato!", Ean);

            log.warning(ErrMsg);

            throw new NotFoundException(ErrMsg);

            //return new ResponseEntity<Articoli>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ArticoloDto>(articolo, HttpStatus.OK);

    }

    @GetMapping(value = "/cerca/codice/{codart}", produces = "application/json")
    public ResponseEntity<ArticoloDto> listArtByCodArt(@PathVariable("codart") String CodArt) throws NotFoundException {
        log.info("****** Otteniamo l'articolo con codice " + CodArt + " *******");

        ArticoloDto articolo = articoliService.SelByCodArt(CodArt);

        if (articolo == null) {
            String ErrMsg = String.format("L'articolo con codice %s non � stato trovato!", CodArt);

            log.warning(ErrMsg);

            throw new NotFoundException(ErrMsg);
        }

        return new ResponseEntity<ArticoloDto>(articolo, HttpStatus.OK);
    }

    @GetMapping(value = "/cerca/descrizione/{filter}", produces = "application/json")
    public ResponseEntity<List<ArticoloDto>> listArtByDesc(@PathVariable("filter") String Filter) throws NotFoundException {
        log.info("****** Otteniamo gli articoli con Descrizione: " + Filter + " *******");

        List<ArticoloDto> articoli = articoliService.SelByDescrizione(Filter);

        if (articoli.isEmpty()) {
            String ErrMsg = String.format("Non è stato trovato alcun articolo avente descrizione %s", Filter);

            log.warning(ErrMsg);

            throw new NotFoundException(ErrMsg);

        }

        return new ResponseEntity<List<ArticoloDto>>(articoli, HttpStatus.OK);
    }

    // -------------------------- INSERIMENTO ARTICOLO ---------------------------------
    @PostMapping(value = "/inserisci")
    @SneakyThrows
    public ResponseEntity<InfoMsg> createArt(@Valid @RequestBody Articolo articolo, BindingResult bindingResult) {
        log.info("Salviamo l'articolo con codice " + articolo.getCodArt());

        //controllo validità dati articolo
        if (bindingResult.hasErrors()) {
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());

            log.warning(MsgErr);

            throw new BindingException(MsgErr);
        }

        //Disabilitare se si vuole gestire anche la modifica
        ArticoloDto checkArt = articoliService.SelByCodArt(articolo.getCodArt());

        if (checkArt != null) {
            String MsgErr = String.format("Articolo %s presente in anagrafica! " + "Impossibile utilizzare il metodo POST", articolo.getCodArt());

            log.warning(MsgErr);

            throw new DuplicateException(MsgErr);
        }
        Date data = new Date();
        articolo.setDataCreaz(data);
        articoliService.InsArticolo(articolo);

        return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), "Inserimento Articolo Eseguita con successo!"), HttpStatus.CREATED);
    }

    // ------------------- MODIFICA ARTICOLO ------------------------------------
    @RequestMapping(value = "/modifica", method = RequestMethod.PUT)
    @SneakyThrows
    public ResponseEntity<InfoMsg> updateArt(@Valid @RequestBody Articolo articolo, BindingResult bindingResult) {
        log.info("Modifichiamo l'articolo con codice " + articolo.getCodArt());

        if (bindingResult.hasErrors()) {
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());

            log.warning(MsgErr);

            throw new BindingException(MsgErr);
        }

        ArticoloDto checkArt = articoliService.SelByCodArt(articolo.getCodArt());

        if (checkArt == null) {
            String MsgErr = String.format("Articolo %s non presente in anagrafica! " + "Impossibile utilizzare il metodo PUT", articolo.getCodArt());

            log.warning(MsgErr);

            throw new NotFoundException(MsgErr);
        }
        Date data = new Date();
        articolo.setDataCreaz(data);
        articoliService.InsArticolo(articolo);

        return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), "Modifica Articolo Eseguita con successo!"), HttpStatus.CREATED);
    }


    // ------------------- ELIMINAZIONE ARTICOLO ------------------------------------
    @DeleteMapping(value = "/elimina/{codart}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<?> deleteArt(@PathVariable("codart") String CodArt) {
        log.info("Eliminiamo l'articolo con codice " + CodArt);

			/*
			//TODO DA ELIMINARE
			if (CodArt.equals("123Test"))
			{
				String MsgErr = String.format("Articolo %s NON Eliminabile! ", CodArt);

				log.warning(MsgErr);

				throw new DuplicateException(MsgErr);
			}
			//FINE DA ELIMINARE
			*/

        Articolo articolo = articoliService.SelByCodArt2(CodArt);

        if (articolo == null) {
            String MsgErr = String.format("Articolo %s non presente in anagrafica! ", CodArt);

            log.warning(MsgErr);

            throw new NotFoundException(MsgErr);
        }

        articoliService.DelArticolo(articolo);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Articolo " + CodArt + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }
}


