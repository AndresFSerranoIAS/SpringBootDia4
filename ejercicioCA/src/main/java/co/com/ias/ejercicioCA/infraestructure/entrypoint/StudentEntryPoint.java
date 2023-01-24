package co.com.ias.ejercicioCA.infraestructure.entrypoint;

import co.com.ias.ejercicioCA.domain.model.student.dto.StudentDTO;
import co.com.ias.ejercicioCA.domain.usercase.StudentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/")
@AllArgsConstructor
public class StudentEntryPoint {

    private StudentUseCase studentUseCase;

    @PostMapping("/new")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDTO studentDTO){
        try {
            studentUseCase.saveStudent(studentDTO);
            return new ResponseEntity<>(String.format("Se ha almacenado correctamente el estudiante %s en la base de datos",studentDTO.getName()), HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("Por favor ingrese el nombre del estudiante que desea almacenar en la base de datos", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllStudents(){
        try{
            return new ResponseEntity<>(studentUseCase.getStudents(),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Ha habido un problema al intentar acceder a la base de datos",HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody StudentDTO studentDTO,@PathVariable Long id){

        try{
            StudentDTO student = studentUseCase.updateStudent(studentDTO,id);
            return new ResponseEntity<>(String.format("Se ha modificado el estudiante %s correctamente", student.getName()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Sucedió un problema al momento de modificar el estudiante",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        try{
            if(!studentUseCase.deleteStudent(id)){
                throw new Exception();
            }
            return new ResponseEntity<>(String.format("Se ha eliminado correctamente el estudiante con ID %d",id),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(String.format("El estudiante con ID %d no se encuentra en la base de datos por ende no puede ser eliminado",id),HttpStatus.BAD_REQUEST);
        }
    }
}
