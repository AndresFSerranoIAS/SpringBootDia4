package co.com.ias.ejercicioCA.domain.model.gateways;

import co.com.ias.ejercicioCA.domain.model.student.Student;
import co.com.ias.ejercicioCA.domain.model.student.StudentName;

import java.util.List;
import java.util.Locale;

public interface IStudentRepository {

    //Create
    public Student saveStudent(Student student);
    //Read
    public List<Student> getStudents();
    //Update
    public Student updateStudent(Student student, Long id);
    //Delete
    public boolean deleteStudent(Long id);
    //Find students by subejct id
    public List<Student> getStudentsBySubjectId(Long id);
}
