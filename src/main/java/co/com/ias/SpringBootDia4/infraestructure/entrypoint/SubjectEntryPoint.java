package co.com.ias.SpringBootDia4.infraestructure.entrypoint;

import co.com.ias.SpringBootDia4.domain.model.subject.dto.SubjectDTO;
import co.com.ias.SpringBootDia4.domain.usecase.SubjectUseCase;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions.StudentNotFoundException;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions.SubjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
@AllArgsConstructor
public class SubjectEntryPoint {
    private SubjectUseCase subjectUseCase;
    @PostMapping("/new")
    public ResponseEntity<?> saveSubject(@RequestBody SubjectDTO subjectDTO){
        try {
            subjectUseCase.saveSubject(subjectDTO);
            return new ResponseEntity<>(String.format("Se ha almacenado correctamente la materia %s en la base de datos",subjectDTO.getName()), HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>(String.format("Se ha modificado la materia %s correctamente", subject.getName()), HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (SubjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        try{
            if(!subjectUseCase.deleteSubject(id)){
                throw new StudentNotFoundException(String.format("La materia con ID %d no se encuentra en la base de datos por ende no puede ser eliminado",id));
            }
            return new ResponseEntity<>(String.format("Se ha eliminado correctamente el estudiante con ID %d",id),HttpStatus.CREATED);
        }catch(StudentNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> subjectById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(subjectUseCase.getStudentById(id),HttpStatus.OK);
        }catch(SubjectNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
