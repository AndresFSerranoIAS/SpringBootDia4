package co.com.ias.ejercicioCA.infraestructure.adapters.jpa.entity;

import co.com.ias.ejercicioCA.domain.model.student.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Setter
@Getter

public class StudentDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;
    @Column(name= "STUDENT_NAME", nullable = false)
    private String name ;
    @Column(name = "STUDENT_PHONE")
    private String phone;
    @Column(name = "STUDENT_EMAIL")
    private String email;
    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "SUBJECT_ID")
    private SubjectDBO subjectDBO;

    public StudentDBO(){
    }
    public StudentDBO(Long id,String name, String phone, String email){
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
    }

    public StudentDBO(Long id,String name, String phone, String email,Long idSubject){
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.subjectDBO = new SubjectDBO(idSubject);
    }

    public static Student toDomain(StudentDBO studentDBO){
        return new Student(new StudentId(studentDBO.id),
                new StudentName(studentDBO.name),
                new StudentPhone(studentDBO.phone),
                new StudentEmail(studentDBO.email), new StudentIdSubject(studentDBO.subjectDBO.getId()));
    }
    public static StudentDBO fromDomain(Student student){
        return new StudentDBO(student.getId().getValue(),
                student.getName().getValue(),
                student.getPhone().getValue(),
                student.getEmail().getValue(),student.getIdSuject().getValue());
    }
}
