package co.com.ias.ejercicioCA.infraestructure.adapters.jpa.entity;

import co.com.ias.ejercicioCA.domain.model.subject.Subject;
import co.com.ias.ejercicioCA.domain.model.subject.SubjectId;
import co.com.ias.ejercicioCA.domain.model.subject.SubjectName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subjects")
@Setter
@Getter
public class SubjectDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBJECT_ID")
    private Long id;
    @Column(name="SUBJECT_NAME",nullable = false)
    private String name;

    public SubjectDBO(Long idSubject) {
        this.id = idSubject;
    }


    public static Subject toDomain(SubjectDBO subjectDBO){
        return new Subject(new SubjectId(subjectDBO.getId()),
                new SubjectName(subjectDBO.getName()));
    }

    public static SubjectDBO fromDomain(Subject subject){
        return new SubjectDBO(subject.getId().getValue(),
                subject.getName().getValue());
    }

}
