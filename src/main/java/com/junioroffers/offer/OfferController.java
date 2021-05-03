package com.junioroffers.offer;

import com.junioroffers.offer.domain.OfferService;
import com.junioroffers.offer.domain.dto.OfferDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/offers")
@AllArgsConstructor
public class OfferController {

    OfferService offerService;

    @GetMapping
    public ResponseEntity<List<OfferDto>> findAllOffers(){
        return ResponseEntity.ok(offerService.findAllOffers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> findOfferById(@PathVariable long id){
        return ResponseEntity.ok(offerService.findOfferById(id));
    }
}
