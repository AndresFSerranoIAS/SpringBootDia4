package co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.student;

import co.com.ias.SpringBootDia4.domain.model.gateways.IStudentRepository;
import co.com.ias.SpringBootDia4.domain.model.student.Student;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.entity.StudentDBO;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.entity.SubjectDBO;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.subject.ISubjectRepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentAdapterRepository implements IStudentRepository {
    private final IStudentRepositoryAdapater studentRepositoryAdapater;
    private final ISubjectRepositoryAdapter subjectRepositoryAdapter;

    public StudentAdapterRepository(IStudentRepositoryAdapater studentRepositoryAdapater, ISubjectRepositoryAdapter subjectRepositoryAdapter) {
        this.studentRepositoryAdapater = studentRepositoryAdapater;
        this.subjectRepositoryAdapter = subjectRepositoryAdapter;
    }

    @Override
    public Student saveStudent(Student student) {
        //Uso de transformación de DBO a Value Object desde JPA para guardado
        Optional<SubjectDBO> idSubjectSearch = subjectRepositoryAdapter.findById(student.getIdSuject().getValue());
        if(idSubjectSearch.isPresent()){
            return StudentDBO.toDomain(studentRepositoryAdapater.save(StudentDBO.fromDomain(student)));
        }
        return null;
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

    @Override
    public List<Student> getStudentsBySubjectId(Long id) {
        Optional<SubjectDBO> subjectFound = subjectRepositoryAdapter.findById(id);
        List<StudentDBO> studentDBOList = studentRepositoryAdapater.findBySubjectDBO_Id(id);
        if(subjectFound.isPresent()) {
            return studentDBOList.stream()
                    .map(StudentDBO::toDomain)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<StudentDBO> studentFound = studentRepositoryAdapater.findById(id);
        if(studentFound.isPresent()){
            return StudentDBO.toDomain(studentFound);
        }
        return null;
    }
}
