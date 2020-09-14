package com.springboot.exception.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.error.ErrorResponse;
import com.springboot.exception.EmailAlreadyExistsException;
import com.springboot.exception.UserNotFoundException;
import com.springboot.exception.UserUnSupportedFieldPatchException;

@ControllerAdvice
@RestController
public class AllExceptionsHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * ########################################################################################################################
	 * Handling User defined Exceptions / custom exceptions 
	 * ########################################################################################################################
	 */

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e, WebRequest req) {
		
		//way-01, here we are returning exception obj , This is not good.
		//return new ResponseEntity<Object>(e, HttpStatus.NOT_FOUND);
		//OR
		//return new ResponseEntity<Object>(e.getMessage(),new HttpHeaders(), HttpStatus.NOT_FOUND);
		
		//way-02, Here wea re returning our custom error class obj as response.This is good approach
		String errMsg = e.getLocalizedMessage();
		
		if (errMsg == null)
			errMsg = e.toString();
		
		List<String> errDetails = new ArrayList<String>();
		errDetails.add(errMsg);

		ErrorResponse errResp = new ErrorResponse(e.getMessage(), errDetails,new Date());
		return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public final ResponseEntity<Object> handleUserCreationException(UserNotFoundException ex, WebRequest req) {
		
		//way-01, here we are returning exception obj ,This is not good approach
		//return new ResponseEntity<Object>(e.getMessage(),HttpStatus.CONFLICT);
		
		//OR
		//way-02, Here wea re returning our custom error class obj as response.This is good approach
		String errMsg = ex.getLocalizedMessage();
		if (errMsg == null)
			errMsg = ex.toString();
		
		List<String> errDetails = new ArrayList<String>();
		errDetails.add(ex.getLocalizedMessage());

		ErrorResponse errResp = new ErrorResponse("New User creation conflict", errDetails,new Date());
		//return new ResponseEntity<>(errResp, HttpStatus.CONFLICT);//OK
		//OR
		return new ResponseEntity<>(errResp,new HttpHeaders(), HttpStatus.CONFLICT);

	}
	
	/**
	 * ########################################################################################################################
	 * Handling invalid data validation failed 
	 * ########################################################################################################################
	 */
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       
		//way-01, , Here wea re returning exception class obj as response.
		//return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); //OK
		//OR
		//return new ResponseEntity<Object>(ex.getMessage(),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); //OK
		
		//way-02, , Here wea re returning our custom error class obj as response.
		List<String> errDetails = new ArrayList<>();
		
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
        	errDetails.add(error.getDefaultMessage());
        }
        
        ErrorResponse error = new ErrorResponse("Input request data Validation Failed", errDetails,new Date());
        // return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST); //OK
        return new ResponseEntity<Object>(error, new HttpHeaders(),HttpStatus.BAD_REQUEST);
        
        
    }
	
	//OR
	
	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class)
	 * ResponseEntity<List<ErrResponse>>
	 * exceptionHandler(MethodArgumentNotValidException ex){ List<FieldError>
	 * fieldErrors = ex.getBindingResult().getFieldErrors(); List<ErrResponse> list
	 * = fieldErrors.stream().map(fieldError -> new
	 * ErrResponse(fieldError.getField(),fieldError.getDefaultMessage())).collect(
	 * Collectors.toList()); return new
	 * ResponseEntity<>(list,HttpStatus.BAD_REQUEST); }
	 */
	
	
	/**
	 * ########################################################################################################################
	 * Handling predefined exceptions 
	 * ########################################################################################################################
	 */
	
	//@ExceptionHandler(Exception.class) //OK
	@ExceptionHandler(value={NullPointerException.class,Exception.class}) //OK
	public final ResponseEntity<Object> handleExceptions(Exception e, WebRequest req) {
		
		//way-01,here we are returning exception obj directly. This is not good approach
		//return new ResponseEntity<Object>(e.getMessage(),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		//OR
		//way-02 , here we are returning our custom error class obj as response. This is good approach
		String errMsg = e.getLocalizedMessage();
		if (errMsg == null)
			errMsg = e.toString();
		
		List<String> errDetails = new ArrayList<String>();
		errDetails.add(errMsg);
		
		ErrorResponse errResp = new ErrorResponse("Exception raised", errDetails,new Date());
		//return new ResponseEntity<>(errResp, HttpStatus.INTERNAL_SERVER_ERROR); //OK
		//OK
		return new ResponseEntity<>(errResp,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	/**
	 * ########################################################################################################################
	 * Handling exceptions for patch operation 
	 * ########################################################################################################################
	 */
	
	//during patch operation
	@ExceptionHandler(UserUnSupportedFieldPatchException.class)
    public void springUnSupportedFieldPatch(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value());
    }
	
	
	/**
	 * ########################################################################################################################
	 * @pathVariable validation along with @Validated at class level
	 * ########################################################################################################################
	 */
	
	@ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
	
}
