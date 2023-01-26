package co.com.ias.SpringBootDia4.domain.model.gateways;

import co.com.ias.SpringBootDia4.domain.model.subject.Subject;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions.SubjectNotFoundException;

import java.util.List;

public interface ISubjectRepository {
    //Create
    public Subject saveSubject(Subject subject);
    //Read
    public List<Subject> getSubjects();
    //Update
    public Subject updateSubject(Subject subject, Long id) throws SubjectNotFoundException;
    //Delete
    public boolean deleteSubject(Long id);
    // Find subject by id
    public Subject getStudentById(Long id);
}
