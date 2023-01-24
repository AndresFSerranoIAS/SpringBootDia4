package co.com.ias.ejercicioCA.infraestructure.adapters.jpa.student;

import co.com.ias.ejercicioCA.domain.model.gateways.IStudentRepository;
import co.com.ias.ejercicioCA.domain.model.student.Student;
import co.com.ias.ejercicioCA.infraestructure.adapters.jpa.entity.StudentDBO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentAdapterRepository implements IStudentRepository {
    private final IStudentRepositoryAdapater studentRepositoryAdapater;

    public StudentAdapterRepository(IStudentRepositoryAdapater studentRepositoryAdapater) {
        this.studentRepositoryAdapater = studentRepositoryAdapater;
    }

    @Override
    public Student saveStudent(Student student) {
        //Uso de transformación de DBO a Value Object desde JPA para guardado
        return StudentDBO.toDomain(studentRepositoryAdapater.save(StudentDBO.fromDomain(student)));
    }

    @Override
    public List<Student> getStudents() {
        //Uso de transformación de DBO a Value Object desde JPA para listado
        List<StudentDBO> list = studentRepositoryAdapater.findAll();
        return list.stream()
                .map(StudentDBO::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        //Uso de transformación de DBO a Value Object desde JPA para guardado
        Optional<StudentDBO> studentChange = studentRepositoryAdapater.findById(id);
        if(studentChange.isPresent()){
            studentChange.get().setName(student.getName().getValue());
            studentChange.get().setPhone(student.getPhone().getValue());
            studentChange.get().setEmail(student.getEmail().getValue());
            return StudentDBO.toDomain(studentRepositoryAdapater.save(StudentDBO.fromDomain(student)));
        }else{
            return null;
        }
    }
    @Override
    public boolean deleteStudent(Long id) {
        //Uso de transformación de DBO a Value Object desde JPA para eliminado
        Optional<StudentDBO> studentErase = studentRepositoryAdapater.findById(id);
        if(studentErase.isPresent()){
            studentRepositoryAdapater.deleteById(id);
            return true;
        }
        return false;
    }
}
