package co.com.ias.SpringBootDia4.infraestructure.entrypoint;

import co.com.ias.SpringBootDia4.domain.model.subject.dto.SubjectDTO;
import co.com.ias.SpringBootDia4.domain.usecase.SubjectUseCase;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions.StudentNotFoundException;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions.SubjectNotFoundException;
import co.com.ias.SpringBootDia4.infraestructure.entrypoint.utility.ResponseHandler;
import lombok.AllArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
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
            return ResponseHandler.generateResponse(String.format("Se ha almacenado correctamente la materia %s en la base de datos",subjectDTO.getName()), HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllSubjects(){
        try{
            return ResponseHandler.generateResponse(subjectUseCase.getSubjects(),HttpStatus.OK,String.format("En la base de datos se encuentran en total %d elementos",subjectUseCase.getSubjects().size()));
        }
        catch (Exception e){
            return ResponseHandler.generateResponse("Ha habido un problema al intentar acceder a la base de datos",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@RequestBody SubjectDTO subjectDTO,@PathVariable Long id){
        try{
            SubjectDTO subject = subjectUseCase.updateSubject(subjectDTO,id);
            return ResponseHandler.generateResponse(String.format("Se ha modificado la materia %s correctamente", subject.getName()),HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (SubjectNotFoundException e) {
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        try{
            if(!subjectUseCase.deleteSubject(id)){
                throw new StudentNotFoundException(String.format("La materia con ID %d no se encuentra en la base de datos por ende no puede ser eliminado",id));
            }
            return ResponseHandler.generateResponse(String.format("Se ha eliminado correctamente el estudiante con ID %d",id),HttpStatus.OK);
        }catch(StudentNotFoundException e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> subjectById(@PathVariable Long id) throws SubjectNotFoundException{
        try{
            return ResponseHandler.generateResponse(subjectUseCase.getSubjectById(id),HttpStatus.OK,String.format("Se ha encontrado satisfactoriamente la materia asociada al ID %d",id));
        }catch(SubjectNotFoundException e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
