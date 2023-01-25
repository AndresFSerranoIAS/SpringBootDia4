package co.com.ias.ejercicioCA.domain.model.student.dto;

import co.com.ias.ejercicioCA.domain.model.student.*;

public class StudentDTO {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private Long idSubject;

    public StudentDTO(Long id, String name, String phone, String email,Long idSubject) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.idSubject = idSubject;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getIdSubject() {
        return idSubject;
    }

    public static StudentDTO fromDomain(Student student){
        return new StudentDTO(student.getId().getValue(),
                student.getName().getValue(),
                student.getPhone().getValue(),
                student.getEmail().getValue(),student.getIdSuject().getValue());
    }

    public static Student toDomain(StudentDTO studentDTO){
        return new Student(new StudentId(studentDTO.getId()),
                new StudentName(studentDTO.getName()),
                new StudentPhone(studentDTO.getPhone()),
                new StudentEmail(studentDTO.getEmail()),
                new StudentIdSubject(studentDTO.getIdSubject()));
    }

}

