package co.com.ias.SpringBootDia4.domain.model.gateways;

import co.com.ias.SpringBootDia4.domain.model.student.Student;

import java.util.List;

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
    //Find student by id
    public Student getStudentById(Long id);
}
