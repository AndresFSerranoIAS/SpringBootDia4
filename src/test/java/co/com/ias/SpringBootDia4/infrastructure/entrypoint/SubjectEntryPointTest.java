package co.com.ias.SpringBootDia4.infrastructure.entrypoint;

import co.com.ias.SpringBootDia4.domain.model.subject.dto.SubjectDTO;
import co.com.ias.SpringBootDia4.domain.usecase.SubjectUseCase;
import co.com.ias.SpringBootDia4.infraestructure.entrypoint.SubjectEntryPoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubjectEntryPoint.class)
public class SubjectEntryPointTest {

    @MockBean
    private SubjectUseCase subjectUseCase;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 1 (Post) - Save subject good case")
    void saveSubject1() throws Exception {
    //Arrange
        SubjectDTO subjectDTO = new SubjectDTO(1L,"Ecuaciones");
        when(subjectUseCase.saveSubject(any(SubjectDTO.class))).thenReturn(subjectDTO);
        ObjectMapper mapper = new ObjectMapper();
        //Act && Assert
            mockMvc.perform(post("/subject/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(subjectDTO)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().
                            string(Matchers.
                                    containsString("Ecuaciones")));

    }
    @Test

    @DisplayName("Test 2 (Post) - Save subject number in input name")
    void saveSubject2() throws Exception{
        //Arrange
        SubjectDTO subjectDTO = new SubjectDTO(1L,"20");
        when(subjectUseCase.saveSubject(any(SubjectDTO.class))).thenReturn(subjectDTO);
        ObjectMapper mapper = new ObjectMapper();
        //Act && Assert
        mockMvc.perform(post("/subject/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(subjectDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().
                        string(Matchers.
                                containsString("Por favor sólo suministre letras del abecedario en el nombre de la materia")));
    }
    @Test
    @DisplayName("Test 3 (Post) - Save subject a special character like @ in subject name")
    void saveSubject3() throws Exception{
        //Arrange
        SubjectDTO subjectDTO = new SubjectDTO(1L,"@");
        when(subjectUseCase.saveSubject(any(SubjectDTO.class))).thenReturn(subjectDTO);
        ObjectMapper mapper = new ObjectMapper();
        //Act && Assert
        mockMvc.perform(post("/subject/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(subjectDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().
                        string(Matchers.
                                containsString("Por favor sólo suministre letras del abecedario en el nombre de la materia")));
    }
    @Test
    @DisplayName("Test 4 (Post) - Save subject without input in name")
    void saveSubject4() throws Exception{
        //Arrange
        SubjectDTO subjectDTO = new SubjectDTO(1L,"");
        when(subjectUseCase.saveSubject(any(SubjectDTO.class))).thenReturn(subjectDTO);
        ObjectMapper mapper = new ObjectMapper();
        //Act && Assert
        mockMvc.perform(post("/subject/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(subjectDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().
                        string(Matchers.
                                containsString("Por favor ingrese el nombre de la materia")));
    }
    @Test
    @DisplayName("Test 5 (Get) - Get subjects list")
    void getSubjects() throws Exception{
        mockMvc.perform(get("/subject/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().
                        string(Matchers.
                                containsString("[]")));
    }
    @Test
    @DisplayName("Test 6 (Put) - Change subject name")
    void updateSubject1() throws Exception{
        //Arrange
        SubjectDTO subjectDTOUpdated = new SubjectDTO(1L,"Termodinamica");
        when(subjectUseCase.updateSubject(any(SubjectDTO.class),eq(1L))).thenReturn(subjectDTOUpdated);
        ObjectMapper mapper = new ObjectMapper();
        //Act && Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/subject/{id}", 1L)
                .content(mapper.writeValueAsString(subjectDTOUpdated))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().
                        string(Matchers.
                                containsString("Se ha modificado la materia Termodinamica correctamente")));

    }
    @Test
    @DisplayName("Test 7 (Put) - Change subject name with blank new name")
    void updateSubject2() throws Exception{
        //Arrange
        SubjectDTO subjectDTOUpdated = new SubjectDTO(1L,"");
        when(subjectUseCase.updateSubject(any(SubjectDTO.class),eq(1L))).thenReturn(subjectDTOUpdated);
        ObjectMapper mapper = new ObjectMapper();
        //Act && Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/subject/{id}", 1L)
                        .content(mapper.writeValueAsString(subjectDTOUpdated))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().
                        string(Matchers.
                                containsString("Por favor ingrese el nombre de la materia")));

    }
    @Test
    @DisplayName("Test 8 (Delete) - Delete an existent Subject")
    void deleteSubject1() throws Exception{
        //Arrange
        when(subjectUseCase.deleteSubject(1L))
                .thenReturn(true);
        //Act && Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/subject/delete/{id}",1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string(Matchers
                                .containsString("Se ha eliminado correctamente el estudiante con ID 1")));

    }
    @Test
    @DisplayName("Test 9 (Delete) - Delete a not existent Subject")
    void deleteSubject2() throws Exception{
        //Arrange
        when(subjectUseCase.deleteSubject(1L))
                .thenReturn(false);
        //Act && Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/subject/delete/{id}",1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string(Matchers
                                .containsString("El estudiante con ID 1 no se encuentra en la base de datos por ende no puede ser eliminado")));
    }
    @Test
    @DisplayName("Test 10 (Get) - Get one existent subject")
    void getOneSubject1() throws Exception{
        SubjectDTO subjectFound = new SubjectDTO(1L,"Ecuaciones");
        when(subjectUseCase.getStudentById(1L))
                .thenReturn(subjectFound);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(get("/subject/{id}",1L).content(mapper.writeValueAsString(subjectFound))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("{\"id\":1,\"name\":\"Ecuaciones\"}")));
    }
    @Test
    @DisplayName("Test 11 (Get) - Get one non existent subject")
    void getOneSubject2() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(get("/subject/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError());
    }
}
