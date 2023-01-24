package co.com.ias.ejercicioCA.infraestructure.adapters.jpa.exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}