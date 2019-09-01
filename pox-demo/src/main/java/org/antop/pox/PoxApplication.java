package org.antop.pox;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@SpringBootApplication
public class PoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoxApplication.class, args);
    }

    @RestControllerAdvice
    static class RestErrorHandler {

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.OK)
        ErrorResponse exception(Exception e) {
            return ErrorResponse.of(e.getMessage());
        }

        @XmlRootElement(name = "error")
        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        static class ErrorResponse {
            @XmlAttribute(name = "message")
            private String message;

            static ErrorResponse of(String message) {
                ErrorResponse response = new ErrorResponse();
                response.message = message;
                return response;
            }
        }
    }

}
