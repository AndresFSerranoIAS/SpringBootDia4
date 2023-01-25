package co.com.ias.SpringBootDia4.infraestructure.entrypoint;

import co.com.ias.SpringBootDia4.domain.model.subject.dto.SubjectDTO;
import co.com.ias.SpringBootDia4.domain.usecase.SubjectUseCase;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions.StudentNotFoundException;
import co.com.ias.SpringBootDia4.infraestructure.entrypoint.exceptions.StringDataFailedFormatException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/subject/")
@AllArgsConstructor
public class SubjectEntryPoint {
    private SubjectUseCase subjectUseCase;

    @PostMapping("/new")
    public ResponseEntity<?> saveSubject(@RequestBody SubjectDTO subjectDTO){
        try {
            //if(!Pattern.matches("[a-zA-z]",subjectDTO.getName())){
            //    throw new StringDataFailedFormatException("Envió un número en el nombre de la materia");
            //}
            subjectUseCase.saveSubject(subjectDTO);
            return new ResponseEntity<>(String.format("Se ha almacenado correctamente la materia %s en la base de datos",subjectDTO.getName()), HttpStatus.CREATED);
        //} catch(StringDataFailedFormatException e){
        //    return new ResponseEntity<>("Por favor ingrese un nombre en el formato correcto", HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            return new ResponseEntity<>("Por favor ingrese el nombre de la materia que desea almacenar en la base de datos", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllSubjects(){
        try{
            return new ResponseEntity<>(subjectUseCase.getSubjects(),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Ha habido un problema al intentar acceder a la base de datos",HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@RequestBody SubjectDTO subjectDTO,@PathVariable Long id){

        try{
            SubjectDTO subject = subjectUseCase.updateSubject(subjectDTO,id);
            return new ResponseEntity<>(String.format("Se ha modificado la materia  %s correctamente", subject.getName()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Sucedió un problema al momento de modificar la materia",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        try{
            if(!subjectUseCase.deleteSubject(id)){
                throw new Exception();
            }
            return new ResponseEntity<>(String.format("Se ha eliminado correctamente el estudiante con ID %d",id),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(String.format("El estudiante con ID %d no se encuentra en la base de datos por ende no puede ser eliminado",id),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> subjectById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(subjectUseCase.getStudentById(id),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("No existe ninguna materia asociada con ese ID",HttpStatus.BAD_REQUEST);
        }
    }
}
