package co.com.ias.SpringBootDia4.infrastructure.adapters;

import co.com.ias.SpringBootDia4.domain.model.subject.Subject;
import co.com.ias.SpringBootDia4.domain.model.subject.SubjectId;
import co.com.ias.SpringBootDia4.domain.model.subject.SubjectName;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.entity.SubjectDBO;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.exceptions.SubjectNotFoundException;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.subject.ISubjectRepositoryAdapter;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.subject.SubjectAdapterRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @DisplayName("Test 1 - Save Subject")
    void saveSubject(){
        //Arange
        Subject subject = new Subject(new SubjectId(1L),new SubjectName("Ecuaciones"));
        //Act
        Subject res = subjectAdapterRepository.saveSubject(subject);
        //Assert
        Assertions.assertEquals("Ecuaciones",res.getName().getValue());
    }
    @Test
    @DisplayName("Test 2 - Update Subject")
    void updateSubject() throws SubjectNotFoundException {
        //Arrange
        Subject subjectX = new Subject(new SubjectId(1L),new SubjectName("Ecuaciones"));
        subjectX = subjectAdapterRepository.saveSubject(subjectX);
        Subject newSubject = new Subject(new SubjectId(1L),new SubjectName("Álgebra"));
        //Act
        Subject res = subjectAdapterRepository.updateSubject(newSubject,newSubject.getId().getValue());
        //Assert
        Assertions.assertEquals("Álgebra",res.getName().getValue());
    }

    //¿Pregunta prueba unitaria??
    @Test
    @DisplayName("Test 3- Delete Subject")
    void deleteSubject(){
        //Arrange
        Subject subject = new Subject(new SubjectId(1L),new SubjectName("Ecuaciones"));
        subject = subjectAdapterRepository.saveSubject(subject);
        //Act
        boolean res = subjectAdapterRepository.deleteSubject(1L);
        //Assert
        Assertions.assertTrue(res);
    }
    @Test
    @DisplayName("Test 4 - Get Subjects")
    void getSubjects(){
        //Arrange
        Subject subject1 = new Subject(new SubjectId(1L), new SubjectName("Ecuaciones"));
        Subject subject2 = new Subject(new SubjectId(2L), new SubjectName("Álgebra"));
        Subject subject3 = new Subject(new SubjectId(3L), new SubjectName("Geometría"));
        subjectAdapterRepository.saveSubject(subject1);
        subjectAdapterRepository.saveSubject(subject2);
        subjectAdapterRepository.saveSubject(subject3);
        //Act
        List<Subject> subjects = subjectAdapterRepository.getSubjects();
        //Assert
        Assertions.assertEquals(3, subjects.size());
        List<Long> subjectIds = subjects.stream().map(s -> s.getId().getValue()).collect(Collectors.toList());
        List<Long> subjectIdsFound = new ArrayList<>();
        subjectIdsFound.add(subject1.getId().getValue());
        subjectIdsFound.add(subject2.getId().getValue());
        subjectIdsFound.add(subject3.getId().getValue());

        List<String> subjectNames = subjects.stream().map(s -> s.getName().getValue()).collect(Collectors.toList());
        List<String> subjectNamesFound = new ArrayList<>();
        subjectNamesFound.add(subject1.getName().getValue());
        subjectNamesFound.add(subject2.getName().getValue());
        subjectNamesFound.add(subject3.getName().getValue());

        Assertions.assertEquals(subjectIds, subjectIdsFound);
        Assertions.assertEquals(subjectNames, subjectNamesFound);

    }

}
