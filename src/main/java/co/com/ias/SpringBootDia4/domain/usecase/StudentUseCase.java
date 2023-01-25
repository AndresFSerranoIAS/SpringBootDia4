package co.com.ias.SpringBootDia4.domain.usecase;

import co.com.ias.SpringBootDia4.domain.model.gateways.IStudentRepository;
import co.com.ias.SpringBootDia4.domain.model.student.Student;
import co.com.ias.SpringBootDia4.domain.model.student.dto.StudentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StudentUseCase {
    private final IStudentRepository studentRepository;

    public StudentUseCase(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public StudentDTO saveStudent(StudentDTO studentDTO){
        //Logíca de almacenamiento de estudiante
        return StudentDTO.fromDomain(studentRepository.saveStudent(StudentDTO.toDomain(studentDTO)));
    }
    public StudentDTO updateStudent(StudentDTO studentDTO, Long id){
        //Lógica de actualización de estudiantes
        return StudentDTO.fromDomain(studentRepository.updateStudent(StudentDTO.toDomain(studentDTO), id));
    }
    public List<StudentDTO> getStudents(){
        //Logica de listado de estudiantes
        List<Student> list = (List<Student>) studentRepository.getStudents();
       return list.stream()
               .map(StudentDTO::fromDomain)
               .collect(Collectors.toList());
    }
    public boolean deleteStudent(Long id) {
        //Lógica eliminado de estudiante
        return studentRepository.deleteStudent(id);
    }
    public List<StudentDTO>  getStudentsBySubjectId(Long id){
        List<Student> list = (List<Student>) studentRepository.getStudentsBySubjectId(id);
        return list.stream()
                .map(StudentDTO::fromDomain)
                .collect(Collectors.toList());
    }
    public StudentDTO getStudentById(Long id){
        return StudentDTO.fromDomain(studentRepository.getStudentById(id));
    }
}
