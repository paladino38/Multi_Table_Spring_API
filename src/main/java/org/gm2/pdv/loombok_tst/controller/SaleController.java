package org.gm2.pdv.loombok_tst.controller;

import org.gm2.pdv.loombok_tst.dto.SaleDTO;
import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
import org.gm2.pdv.loombok_tst.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/sale")
public class SaleController {

    private SaleService saleService;
    private UserRepository userRepository;
    public SaleController(@Autowired SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody SaleDTO saleDTO) {
        try{


           System.out.println("Valor do Userid é" + saleDTO.getUserId());
            System.out.println("Valor do product é" + saleDTO.getItems());
            long id = saleService.save(saleDTO);
            return new ResponseEntity("Venda realisada com sucesso" + id, HttpStatus.CREATED);

        }catch (Exception e) {
                    return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
