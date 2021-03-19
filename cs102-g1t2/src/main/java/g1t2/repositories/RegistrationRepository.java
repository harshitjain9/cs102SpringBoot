package g1t2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import g1t2.entities.Course;
import g1t2.entities.Registration;
import g1t2.entities.Student;

public interface RegistrationRepository extends CrudRepository<Registration, Integer> {
    @Async
    @Query("SELECT r.student from Registration r WHERE r.course.cname = :name")
    List<Student> findAllStudentByCourseName(String name);

    @Async
    @Query("SELECT r.course from Registration r WHERE r.student.sname = :name")
    List<Course> findAllCourseByStudentName(String name);
}
