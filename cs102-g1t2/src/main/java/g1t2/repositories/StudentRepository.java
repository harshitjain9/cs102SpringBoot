package g1t2.repositories;

import org.springframework.data.repository.CrudRepository;

import g1t2.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
