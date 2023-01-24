package co.com.ias.ejercicioCA.infraestructure.adapters.jpa.student;

import co.com.ias.ejercicioCA.infraestructure.adapters.jpa.entity.StudentDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepositoryAdapater extends JpaRepository<StudentDBO,Long> {
}
