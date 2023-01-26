package co.com.ias.SpringBootDia4.domain.model.subject;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.util.Assert;

import java.util.regex.Pattern;


public class SubjectName {
    private final String value;

    public SubjectName(String value) {
        this.value = value;
        Assert.isTrue(value!="","Por favor ingrese un nombre para la materia que quiere registrar en la base de datos");
        Assert.isTrue(value!=null,"Por favor ingrese un nombre para la materia que quiere registrar en la base de datos");
        Assert.isTrue(Pattern.matches("\\p{L}+",value),"Por favor sólo suministre letras del abecedario en el nombre de la materia");
    }

    public String getValue() {
        return value;
    }
}
