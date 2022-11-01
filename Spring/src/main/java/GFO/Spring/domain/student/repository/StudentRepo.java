package GFO.Spring.domain.student.repository;

import GFO.Spring.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, String> {

}
