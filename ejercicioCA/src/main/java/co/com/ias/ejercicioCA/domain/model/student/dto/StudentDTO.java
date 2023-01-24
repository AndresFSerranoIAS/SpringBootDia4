package co.com.ias.ejercicioCA.domain.model.student.dto;

import co.com.ias.ejercicioCA.domain.model.student.*;
import co.com.ias.ejercicioCA.domain.model.subject.dto.SubjectDTO;

public class StudentDTO {

    private Long id;
    private String name;
    private String phone;
    private String email;

    public StudentDTO(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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


    public static StudentDTO fromDomain(Student student){
        return new StudentDTO(student.getId().getValue(),
                student.getName().getValue(),
                student.getPhone().getValue(),
                student.getEmail().getValue());
    }

    public static Student toDomain(StudentDTO studentDTO){
        return new Student(new StudentId(studentDTO.getId()),
                new StudentName(studentDTO.getName()),
                new StudentPhone(studentDTO.getPhone()),
                new StudentEmail(studentDTO.getEmail()));
    }

}

