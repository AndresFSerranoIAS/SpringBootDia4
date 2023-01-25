package co.com.ias.SpringBootDia4.domain.usercase;

import co.com.ias.SpringBootDia4.domain.model.gateways.ISubjectRepository;
import co.com.ias.SpringBootDia4.domain.model.subject.Subject;
import co.com.ias.SpringBootDia4.domain.model.subject.dto.SubjectDTO;
import co.com.ias.SpringBootDia4.domain.usecase.SubjectUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubjectUseCaseTest {
    @InjectMocks
    private SubjectUseCase subjectUseCase;
    @Mock
    private ISubjectRepository iSubjectRepository;
    @Test
    @DisplayName("Test 1")
    void saveCourse() throws Exception {
        //Arange
        SubjectDTO subjectDTO = new SubjectDTO(1L,"Ecuaciones");
        Subject subject = SubjectDTO.toDomain(subjectDTO);
        when(iSubjectRepository.saveSubject((any(Subject.class)))).thenReturn(subject);
        //Act && Assert
        SubjectDTO subjectActual = subjectUseCase.saveSubject(subjectDTO) ;
        Assertions.assertNotNull(subjectActual);
        assertEquals(subjectActual.getName(),"Ecuaciones");
    }
}
