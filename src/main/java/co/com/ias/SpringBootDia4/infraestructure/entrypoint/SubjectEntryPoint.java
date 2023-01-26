package co.com.ias.SpringBootDia4.infraestructure.entrypoint;

import co.com.ias.SpringBootDia4.domain.model.subject.dto.SubjectDTO;
import co.com.ias.SpringBootDia4.domain.usecase.SubjectUseCase;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions.StudentNotFoundException;
import co.com.ias.SpringBootDia4.infraestructure.entrypoint.exceptions.StringDataFailedFormatException;
import co.com.ias.SpringBootDia4.infraestructure.entrypoint.exceptions.SubjectInputNameNotFound;
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
            if(subjectDTO.getName() == null | subjectDTO.getName() == ""){
                throw new SubjectInputNameNotFound("Por favor ingrese el nombre de la materia");
            }
            if(!Pattern.matches("[a-zA-z]+",subjectDTO.getName())){
                throw new StringDataFailedFormatException("Por favor sólo suministre letras del abecedario en el nombre de la materia");
            }
            subjectUseCase.saveSubject(subjectDTO);
            return new ResponseEntity<>(String.format("Se ha almacenado correctamente la materia %s en la base de datos",subjectDTO.getName()), HttpStatus.CREATED);
        } catch(StringDataFailedFormatException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(SubjectInputNameNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(Exception e){
            return new ResponseEntity<>("Por favor ingrese el nombre de la materia que desea almacenar en la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
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
            if(subjectDTO.getName() == null | subjectDTO.getName() == ""){
                throw new SubjectInputNameNotFound("Por favor ingrese el nombre de la materia");
            }
            if(!Pattern.matches("[a-zA-z]+",subjectDTO.getName())){
                throw new StringDataFailedFormatException("Por favor sólo suministre letras del abecedario en el nombre de la materia");
            }
            SubjectDTO subject = subjectUseCase.updateSubject(subjectDTO,id);
            return new ResponseEntity<>(String.format("Se ha modificado la materia %s correctamente", subject.getName()), HttpStatus.CREATED);
        }catch(SubjectInputNameNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(StringDataFailedFormatException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            return new ResponseEntity<>("Sucedió un problema al momento de modificar la materia", HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>(String.format("El estudiante con ID %d no se encuentra en la base de datos por ende no puede ser eliminado",id),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> subjectById(@PathVariable Long id){
        try{
            if(subjectUseCase.getStudentById(id).getId()==null){
                throw new Exception("No existe ninguna materia asociada con ese ID");
            }
            return new ResponseEntity<>(subjectUseCase.getStudentById(id),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
