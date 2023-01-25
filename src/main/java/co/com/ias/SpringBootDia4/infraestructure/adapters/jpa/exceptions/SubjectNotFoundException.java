package co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions;

public class SubjectNotFoundException extends Exception {

    public SubjectNotFoundException(String message){
        super(message);
    }
}
