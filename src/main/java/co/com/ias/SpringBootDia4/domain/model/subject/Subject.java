package co.com.ias.SpringBootDia4.domain.model.subject;

public class Subject {
    private final SubjectId id;
    private final SubjectName name;

    public Subject(SubjectId id, SubjectName name) {
        this.id = id;
        this.name = name;
    }

    public SubjectId getId() {
        return id;
    }

    public SubjectName getName() {
        return name;
    }
}
