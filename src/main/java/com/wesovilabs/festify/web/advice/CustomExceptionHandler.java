package com.wesovilabs.festify.web.advice;

import com.wesovilabs.festify.util.exception.ArtistNotFoundException;
import com.wesovilabs.festify.util.exception.FestivalNotFoundException;
import com.wesovilabs.festify.util.exception.InvalidIdException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidIdException.class)
    ProblemDetail handleInvalidIdException(InvalidIdException ex, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        pd.setTitle("El formato del id no es válido");
        pd.setProperty("path", request.getRequestURI());
        return pd;
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    ProblemDetail handleArtistNotFoundException(ArtistNotFoundException ex, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        pd.setTitle("Artista con id proporcionada no existe");
        pd.setProperty("path", request.getRequestURI());
        return pd;
    }
    @ExceptionHandler(FestivalNotFoundException.class)
    ProblemDetail handleFestivalNotFoundException(FestivalNotFoundException ex, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        pd.setTitle("Festival con id proporcionada no existe");
        pd.setProperty("path", request.getRequestURI());
        return pd;
    }
}
