package co.com.ias.ejercicioCA.domain.model.student;

public class Student {
    private final StudentId id;
    private final StudentName name;
    private final StudentPhone phone;
    private final StudentEmail email;

    private final StudentIdSubject idSuject;

    public Student(StudentId id, StudentName name, StudentPhone phone, StudentEmail email, StudentIdSubject idSuject) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.idSuject = idSuject;
    }

    public StudentId getId() {
        return id;
    }

    public StudentName getName() {
        return name;
    }

    public StudentPhone getPhone() {
        return phone;
    }

    public StudentEmail getEmail() {
        return email;
    }

    public StudentIdSubject getIdSuject() {
        return idSuject;
    }
}
