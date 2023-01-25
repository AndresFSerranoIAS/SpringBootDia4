package co.com.ias.SpringBootDia4.infrastructure.entrypoint;

import co.com.ias.SpringBootDia4.domain.model.subject.dto.SubjectDTO;
import co.com.ias.SpringBootDia4.domain.usecase.SubjectUseCase;
import co.com.ias.SpringBootDia4.infraestructure.entrypoint.SubjectEntryPoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(SubjectEntryPoint.class)
public class SubjectEntryPointTest {

    @MockBean
    private SubjectUseCase subjectUseCase;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Save subject ok")
    void saveSubject() throws Exception {
    //Arrange
        SubjectDTO subjectDTO = new SubjectDTO(1L,"Ecuaciones");
        when(subjectUseCase.saveSubject(any(SubjectDTO.class))).thenReturn(subjectDTO);
        ObjectMapper mapper = new ObjectMapper();
        //Act && Assert
            mockMvc.perform(post("/subject/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(subjectDTO)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().
                            string(Matchers.
                                    containsString("Ecuaciones")));

    }

}
