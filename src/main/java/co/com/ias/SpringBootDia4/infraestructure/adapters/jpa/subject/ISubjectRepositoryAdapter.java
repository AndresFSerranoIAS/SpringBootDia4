package co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.subject;
import co.com.ias.SpringBootDia4.infraestructure.adapters.jpa.entity.SubjectDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubjectRepositoryAdapter extends JpaRepository<SubjectDBO,Long> {
}
