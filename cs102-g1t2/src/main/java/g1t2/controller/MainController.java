package g1t2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g1t2.entities.Course;
import g1t2.entities.Registration;
import g1t2.entities.Student;
import g1t2.repositories.CourseRepository;
import g1t2.repositories.RegistrationRepository;
import g1t2.repositories.StudentRepository;

@RestController
@RequestMapping("/dothack")
public class MainController {

//    @Autowired
//    // This means to get the bean called userRepository
//    // Which is auto-generated by Spring, we will use it to handle the data
//    StudentRepository studentRepository;
//    @Autowired
//    CourseRepository courseRepository;
//    @Autowired
//    RegistrationRepository rRepository;
//
//    @GetMapping(path = "/student/list")
//    public ResponseEntity<?> listStudent() {
//        return ResponseEntity.ok(studentRepository.findAll());
//    }
//
//    @PostMapping(path = "/student/add")
//    public ResponseEntity<?> addStudent(@RequestBody String name) {
//        Student s = new Student();
//        s.setSname(name);
//        return ResponseEntity.ok(studentRepository.save(s));
//    }
//
//    @GetMapping(path = "/course/list")
//    public ResponseEntity<?> listCourse() {
//        return ResponseEntity.ok(studentRepository.findAll());
//    }
//
//    @PostMapping(path = "/course/add")
//    public ResponseEntity<?> addCorse(@RequestBody String cname) {
//        Course s = new Course();
//        s.setCname(cname);
//        return ResponseEntity.ok(courseRepository.save(s));
//    }
//
//    @GetMapping(path = "/registration/list")
//    public ResponseEntity<?> listRegistration() {
//        return ResponseEntity.ok(rRepository.findAll());
//    }
//
////    @PostMapping(path = "/registration/map")
////    public ResponseEntity<?> register(@RequestBody MappingDTO mDTO) {
////        //duplication checking
////        // -> looping to check (not recommended)
////        // -> using query
////        Registration r = new Registration();
////        r.setCourse(courseRepository.findById(mDTO.getCid()).get());
////        r.setStudent(studentRepository.findById(mDTO.getSid()).get());
////        r.setSemester(mDTO.getSemester());
////        return ResponseEntity.ok(rRepository.save(r));
////    }
//
//    @GetMapping(path = "/registration/listbycourse/{name}")
//    public ResponseEntity listByCourseName(@PathVariable String name) {
//        return ResponseEntity.ok(rRepository.findAllStudentByCourseName(name));
//    }
//
//    @GetMapping(path = "/registration/listbystudent/{name}")
//    public ResponseEntity listByStudentName(@PathVariable String name) {
//        return ResponseEntity.ok(rRepository.findAllCourseByStudentName(name));
//    }
}
