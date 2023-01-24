package co.com.ias.ejercicioCA.infraestructure.adapters.jpa.subject;
import co.com.ias.ejercicioCA.infraestructure.adapters.jpa.entity.SubjectDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepositoryAdapter extends JpaRepository<SubjectDBO,Long> {
}
