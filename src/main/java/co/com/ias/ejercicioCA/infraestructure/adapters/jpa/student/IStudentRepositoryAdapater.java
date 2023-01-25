package co.com.ias.ejercicioCA.infraestructure.adapters.jpa.student;

import co.com.ias.ejercicioCA.infraestructure.adapters.jpa.entity.StudentDBO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IStudentRepositoryAdapater extends JpaRepository<StudentDBO,Long> {
    List<StudentDBO> findBySubjectDBO_Id(Long id);
}
