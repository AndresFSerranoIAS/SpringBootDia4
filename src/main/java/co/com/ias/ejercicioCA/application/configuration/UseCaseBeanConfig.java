package co.com.ias.ejercicioCA.application.configuration;

import co.com.ias.ejercicioCA.domain.model.gateways.IStudentRepository;
import co.com.ias.ejercicioCA.domain.model.gateways.ISubjectRepository;
import co.com.ias.ejercicioCA.domain.usercase.StudentUseCase;
import co.com.ias.ejercicioCA.domain.usercase.SubjectUseCase;
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
