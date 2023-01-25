package co.com.ias.ejercicioCA.infraestructure.adapters.jpa.subject;

import co.com.ias.ejercicioCA.domain.model.gateways.ISubjectRepository;
import co.com.ias.ejercicioCA.domain.model.subject.Subject;
import co.com.ias.ejercicioCA.domain.model.subject.dto.SubjectDTO;
import co.com.ias.ejercicioCA.infraestructure.adapters.jpa.entity.StudentDBO;
import co.com.ias.ejercicioCA.infraestructure.adapters.jpa.entity.SubjectDBO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectAdapterRepository implements ISubjectRepository {
    private final ISubjectRepositoryAdapter subjectRepositoryAdapter;

    public SubjectAdapterRepository(ISubjectRepositoryAdapter subjectRepositoryAdapter) {
        this.subjectRepositoryAdapter = subjectRepositoryAdapter;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return SubjectDBO.toDomain(subjectRepositoryAdapter.save(SubjectDBO.fromDomain(subject)));
    }

    @Override
    public List<Subject> getSubjects() {
        List<SubjectDBO> list = subjectRepositoryAdapter.findAll();
        return list.stream()
                .map(SubjectDBO::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Subject updateSubject(Subject subject, Long id) {
        Optional<SubjectDBO> subjectChange = subjectRepositoryAdapter.findById(id);
        if(subjectChange.isPresent()){
            subjectChange.get().setName(subject.getName().getValue());
            return SubjectDBO.toDomain(subjectRepositoryAdapter.save(SubjectDBO.fromDomain(subject)));
        }else{
            return null;
        }
    }


    @Override
    public boolean deleteSubject(Long id) {
        Optional<SubjectDBO> subjectErase = subjectRepositoryAdapter.findById(id);
        if(subjectErase.isPresent()){
            subjectRepositoryAdapter.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Subject getStudentById(Long id) {
        Optional<SubjectDBO> subjectFound = subjectRepositoryAdapter.findById(id);
        if(subjectFound.isPresent()){
            return SubjectDBO.toDomain(subjectFound);
        }
        return null;
    }
}
