package co.com.ias.SpringBootDia4.infraestructure.entrypoint.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<?> generateResponse(String message, HttpStatus status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<?> generateResponse(Object response, HttpStatus status,String message){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", response);
        map.put("message", message);
        map.put("status", status.value());
        return new ResponseEntity<Object>(map,status);
    }



}
