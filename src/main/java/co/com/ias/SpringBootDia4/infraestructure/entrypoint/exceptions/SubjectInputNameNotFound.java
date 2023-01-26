package co.com.ias.SpringBootDia4.infraestructure.entrypoint.exceptions;

public class SubjectInputNameNotFound extends NullPointerException {

    public SubjectInputNameNotFound(String message){
        super(message);
    }
}
