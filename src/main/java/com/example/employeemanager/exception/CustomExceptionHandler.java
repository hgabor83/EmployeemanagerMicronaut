package com.example.employeemanager.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Produces
@Singleton
@Requires(classes = { UserNotFoundException.class, ExceptionHandler.class })
@Slf4j
public class CustomExceptionHandler implements ExceptionHandler<UserNotFoundException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, UserNotFoundException exception) {
        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return HttpResponse.notFound(errorMessage);
    }

}