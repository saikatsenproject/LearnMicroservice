package com.saikatsenportfolio.accounts.exception;
import com.saikatsenportfolio.accounts.constant.CustomerConstant;
import com.saikatsenportfolio.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjectError> listErrors=ex.getBindingResult().getAllErrors();
        Map<String,String> errors=new HashMap<>();
        for(ObjectError err:listErrors){
            errors.put(((FieldError)err).getField(),err.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,WebRequest webRequest){
        String apiPath= webRequest.getDescription(false);
        String msg= exception.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDto(apiPath,HttpStatus.INTERNAL_SERVER_ERROR,msg,LocalDate.now()));
    }
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerException(CustomerException customerException, WebRequest webRequest){
        String apiPath= webRequest.getDescription(false);
        String msg= customerException.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(apiPath,HttpStatus.BAD_REQUEST,msg,LocalDate.now()));
    }
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponseDto> handleAccountException(AccountException accountException, WebRequest webRequest){
        String apiPath= webRequest.getDescription(false);
        String msg= accountException.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(apiPath,HttpStatus.BAD_REQUEST,msg,LocalDate.now()));
    }
}
