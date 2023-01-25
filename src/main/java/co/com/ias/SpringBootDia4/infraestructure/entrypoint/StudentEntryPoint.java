package co.com.ias.SpringBootDia4.infraestructure.entrypoint;

import co.com.ias.SpringBootDia4.domain.model.student.dto.StudentDTO;
import co.com.ias.SpringBootDia4.domain.usecase.StudentUseCase;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/")
@AllArgsConstructor
public class StudentEntryPoint {

    private StudentUseCase studentUseCase;

    @PostMapping("/new")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDTO studentDTO){
        try {
            StudentDTO student = studentUseCase.saveStudent(studentDTO);
            return new ResponseEntity<>(String.format("Se ha almacenado correctamente el estudiante %s en la base de datos en la materia",studentDTO.getName()), HttpStatus.CREATED);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>("No existe ninguna materia asociada en la base de datos, por favor relacione el estudiante con una que si exista", HttpStatus.PRECONDITION_FAILED);
        }
        catch (PropertyValueException e){
            return new ResponseEntity<>("Por favor suministre los datos obligatorios para el correcto registro de un estudiante", HttpStatus.BAD_REQUEST);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>("Debe asignar de manera obligatoria una materia al estudiante", HttpStatus.BAD_REQUEST);
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

    @GetMapping("/subject/{id}")
    public ResponseEntity<?> studentBySubjectId(@PathVariable Long id){
        try {
            List<StudentDTO> studentList = studentUseCase.getStudentsBySubjectId(id);
            if(studentList==null){
                return new ResponseEntity<>("[]",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(studentList,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>("No se ha encontrado ninguna materia con ese ID",HttpStatus.PRECONDITION_FAILED);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(studentUseCase.getStudentById(id),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("No hay ningún estudiante asociado con el ID",HttpStatus.BAD_REQUEST);
        }
    }
}
