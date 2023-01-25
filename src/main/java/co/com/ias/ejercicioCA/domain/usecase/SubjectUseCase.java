package co.com.ias.ejercicioCA.domain.usecase;

import co.com.ias.ejercicioCA.domain.model.gateways.ISubjectRepository;
import co.com.ias.ejercicioCA.domain.model.student.dto.StudentDTO;
import co.com.ias.ejercicioCA.domain.model.subject.Subject;
import co.com.ias.ejercicioCA.domain.model.subject.dto.SubjectDTO;

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

    public SubjectDTO updateSubject(SubjectDTO subjectDTO,Long id){
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

    public SubjectDTO getStudentById(Long id){
        return SubjectDTO.fromDomain(subjectRepository.getStudentById(id));
    }
}
