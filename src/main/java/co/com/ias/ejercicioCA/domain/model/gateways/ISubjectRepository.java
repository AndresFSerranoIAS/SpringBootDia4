package co.com.ias.ejercicioCA.domain.model.gateways;

import co.com.ias.ejercicioCA.domain.model.student.Student;
import co.com.ias.ejercicioCA.domain.model.subject.Subject;

import java.util.List;

public interface ISubjectRepository {
    //Create
    public Subject saveSubject(Subject subject);
    //Read
    public List<Subject> getSubjects();
    //Update
    public Subject updateSubject(Subject subject, Long id);
    //Delete
    public boolean deleteSubject(Long id);
}
