package com.junioroffers.offer.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class OfferErrorResponse {

    private final String message;
    private final HttpStatus status;
}
