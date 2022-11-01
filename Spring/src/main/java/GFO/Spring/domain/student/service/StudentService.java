package GFO.Spring.domain.student.service;

import GFO.Spring.domain.student.Student;
import GFO.Spring.domain.student.repository.StudentRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;

    public void signUp() {
      studentRepo.save(new Student());
    }
}
