package GFO.Spring.service;

import GFO.Spring.domain.Student;
import GFO.Spring.repository.StudentRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;

    public void signUp() {
      studentRepo.save(new Student());
    }
}
