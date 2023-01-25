package co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}