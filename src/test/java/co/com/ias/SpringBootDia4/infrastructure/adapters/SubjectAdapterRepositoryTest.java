package co.com.ias.SpringBootDia4.infrastructure.adapters;

import co.com.ias.SpringBootDia4.domain.model.subject.Subject;
import co.com.ias.SpringBootDia4.domain.model.subject.SubjectId;
import co.com.ias.SpringBootDia4.domain.model.subject.SubjectName;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.subject.ISubjectRepositoryAdapter;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.subject.SubjectAdapterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class SubjectAdapterRepositoryTest {
    @InjectMocks
    private SubjectAdapterRepository subjectAdapterRepository;
    @Autowired
    ISubjectRepositoryAdapter repository;
    @BeforeAll
    void init(){
        subjectAdapterRepository = new SubjectAdapterRepository(repository);
    }

    @Test
    void saveSubject(){
        //Arange
        Subject subject = new Subject(new SubjectId(1L),new SubjectName("Ecuaciones"));
        //Act
        Subject res = subjectAdapterRepository.saveSubject(subject);
        //Assert
        Assertions.assertEquals("Ecuaciones",res.getName().getValue());
    }
}
