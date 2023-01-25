package co.com.ias.SpringBootDia4.application.configuration;

import co.com.ias.SpringBootDia4.domain.model.gateways.IStudentRepository;
import co.com.ias.SpringBootDia4.domain.model.gateways.ISubjectRepository;
import co.com.ias.SpringBootDia4.domain.usecase.StudentUseCase;
import co.com.ias.SpringBootDia4.domain.usecase.SubjectUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {
    @Bean("studentService")
    public StudentUseCase studentUserCase (IStudentRepository studentRepository){
        return new StudentUseCase(studentRepository);
    }
    @Bean("subjectService")
    public SubjectUseCase subjectUseCase (ISubjectRepository subjectRepository){
        return new SubjectUseCase(subjectRepository);
    }

}
