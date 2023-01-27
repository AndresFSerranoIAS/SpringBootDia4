package co.com.ias.SpringBootDia4.domain.usecase;

import co.com.ias.SpringBootDia4.domain.model.gateways.ISubjectRepository;
import co.com.ias.SpringBootDia4.domain.model.subject.Subject;
import co.com.ias.SpringBootDia4.domain.model.subject.dto.SubjectDTO;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions.SubjectNotFoundException;
import co.com.ias.SpringBootDia4.infraestructure.entrypoint.exceptions.SubjectInputNameNotFound;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectUseCase {
    private final ISubjectRepository subjectRepository;

    public SubjectUseCase(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    public SubjectDTO saveSubject(SubjectDTO subjectDTO){
        return SubjectDTO.fromDomain(subjectRepository.saveSubject(SubjectDTO.toDomain(subjectDTO)));
    }

    public SubjectDTO updateSubject(SubjectDTO subjectDTO,Long id) throws SubjectNotFoundException {
        return SubjectDTO.fromDomain(subjectRepository.updateSubject(SubjectDTO.toDomain(subjectDTO),id));
    }

    public List<SubjectDTO> getSubjects(){
        List<Subject> list = (List<Subject>) subjectRepository.getSubjects();
        return list.stream()
                .map(SubjectDTO::fromDomain)
                .collect(Collectors.toList());
    }
    public boolean deleteSubject(Long id) {
        return subjectRepository.deleteSubject(id);
    }

    public SubjectDTO getSubjectById(Long id){
        return SubjectDTO.fromDomain(subjectRepository.getSubjectById(id));
    }
}
