/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.datastore.shared.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(final ResourceNotFoundException exception) {

        log.info(exception.getMessage());

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotReadyException.class)
    protected ResponseEntity<Object> handleResourceNotReadyException(final ResourceNotReadyException exception) {

        log.info(exception.getMessage());

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.TOO_EARLY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(final Exception exception) {
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception);
        log.error("Detected unhandled exception.", exception);

        return new ResponseEntity<>(apiError.getMessage(), apiError.getStatus());
    }
}
