package co.com.ias.SpringBootDia4.domain.model.subject.dto;


import co.com.ias.SpringBootDia4.domain.model.subject.Subject;
import co.com.ias.SpringBootDia4.domain.model.subject.SubjectId;
import co.com.ias.SpringBootDia4.domain.model.subject.SubjectName;

public class SubjectDTO {

    private Long id;
    private String name;

    public SubjectDTO(Long id, String name)  {
        this.id = id;
        this.name = name;
    }

    public SubjectDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SubjectDTO fromDomain(Subject subject){
        return new SubjectDTO(subject.getId().getValue(),
                subject.getName().getValue());
    }

    public static Subject toDomain(SubjectDTO subjectDTO){
        return new Subject(new SubjectId(subjectDTO.getId()),
                new SubjectName(subjectDTO.getName()));
    }
}
