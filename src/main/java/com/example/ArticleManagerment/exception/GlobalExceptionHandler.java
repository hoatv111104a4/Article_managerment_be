package com.example.ArticleManagerment.exception;

import com.example.ArticleManagerment.dto.reponse.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRunTimeException(RuntimeException  exception){
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setCode(Errorcode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setResult(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        Errorcode errorcode = exception.getErrorcode();
        ApiResponse apiResponse =new ApiResponse();
        apiResponse.setCode(errorcode.getCode());
        apiResponse.setResult(errorcode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
        ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){
            String enumKey = exception.getFieldError().getDefaultMessage();
            Errorcode errorcode  = Errorcode.KEY_INVALID;
            try {
                errorcode =Errorcode.valueOf(enumKey);
            }catch (IllegalArgumentException e){

            }
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setCode(errorcode.getCode());
            apiResponse.setResult(errorcode.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleInvalidFormat(HttpMessageNotReadableException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(Errorcode.KEY_INVALID.getCode());
        apiResponse.setResult(Errorcode.PRICE_MUST_BE_NUMERIC.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
