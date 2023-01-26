package co.com.ias.SpringBootDia4.domain.usercase;

import co.com.ias.SpringBootDia4.domain.model.gateways.ISubjectRepository;
import co.com.ias.SpringBootDia4.domain.model.subject.Subject;
import co.com.ias.SpringBootDia4.domain.model.subject.dto.SubjectDTO;
import co.com.ias.SpringBootDia4.domain.usecase.SubjectUseCase;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubjectUseCaseTest {
    @InjectMocks
    private SubjectUseCase subjectUseCase;
    @Mock
    private ISubjectRepository iSubjectRepository;
    @Test
    @Order(1)
    @DisplayName("Test 1 - Save Subject")
    void saveSubject() throws Exception {
        //Arange
        SubjectDTO subjectDTO = new SubjectDTO(1L,"Ecuaciones");
        Subject subject = SubjectDTO.toDomain(subjectDTO);
        when(iSubjectRepository.saveSubject((any(Subject.class)))).thenReturn(subject);
        //Act && Assert
        SubjectDTO subjectActual = subjectUseCase.saveSubject(subjectDTO) ;
        Assertions.assertNotNull(subjectActual);
        assertEquals(subjectActual.getName(),"Ecuaciones");
    }
    @Test
    @Order(2)
    @DisplayName("Test 2 - Update Subject")
    void updateSubject() throws Exception {
        //Arange
        SubjectDTO subjectDTO = new SubjectDTO(1L,"Ecuaciones");
        Subject subject = SubjectDTO.toDomain(subjectDTO);
        when(iSubjectRepository.updateSubject(any(Subject.class),eq(1L))).thenReturn(subject);
        //Act && Assert
        SubjectDTO subjectActual = subjectUseCase.updateSubject(subjectDTO,1L) ;
        Assertions.assertNotNull(subjectActual);
        assertEquals(subjectActual.getName(),"Ecuaciones");
    }
    @Test
    @Order(3)
    @DisplayName("Test 3 - Delete Subject")
    void deleteSubject() throws Exception {
        //Arange
        when(iSubjectRepository.deleteSubject(1L))
                .thenReturn(true);
        //Act && Assert
        boolean response = subjectUseCase.deleteSubject(1L) ;
        assertTrue(response);
    }
    @Test
    @Order(4)
    @DisplayName("Test 4 - Get Subjects")
    void getSubjects() throws Exception {
        //Arange
        List<Subject> list = new ArrayList<>();
        when(iSubjectRepository.getSubjects()).thenReturn(list);
        //Act && Assert
        List<SubjectDTO> response = subjectUseCase.getSubjects();
        assertEquals(response,new ArrayList<>());
    }
    @Test
    @Order(5)
    @DisplayName("Test 5 - Get One Subject")
    void getSubjectById() throws Exception {
        //Arange
        SubjectDTO subjectDTO = new SubjectDTO(1L,"Ecuaciones");
        Subject subject = SubjectDTO.toDomain(subjectDTO);
        when(iSubjectRepository.getStudentById(1L)).thenReturn(subject);
        //Act && Assert
        SubjectDTO subjectActual = subjectUseCase.getStudentById(1L) ;
        Assertions.assertNotNull(subjectActual);
        assertEquals(subjectActual.getName(),"Ecuaciones");
    }
}
