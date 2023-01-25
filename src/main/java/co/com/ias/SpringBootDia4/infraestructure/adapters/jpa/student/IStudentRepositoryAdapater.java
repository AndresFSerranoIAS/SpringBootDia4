package co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.student;

import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.entity.StudentDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepositoryAdapater extends JpaRepository<StudentDBO,Long> {
    List<StudentDBO> findBySubjectDBO_Id(Long id);
}
