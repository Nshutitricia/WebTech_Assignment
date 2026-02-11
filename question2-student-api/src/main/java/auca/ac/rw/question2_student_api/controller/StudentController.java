package auca.ac.rw.question2_student_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.question2_student_api.model.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();
    
    public StudentController() {
        students.add(new Student(1L, "John", "Doe", "john@example.com", "Computer Science", 3.8));
        students.add(new Student(2L, "Jane", "Smith", "jane@example.com", "Mathematics", 3.2));
        students.add(new Student(3L, "Alice", "Johnson", "alice@example.com", "Physics", 3.9));
        students.add(new Student(4L, "Bob", "Brown", "bob@example.com", "Computer Science", 2.5));
        students.add(new Student(5L, "Charlie", "Davis", "charlie@example.com", "Computer Science", 3.6));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
   
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> studentsByMajor = new ArrayList<>();
        for (Student s : students) {
            if (s.getMajor().equalsIgnoreCase(major)) {
                studentsByMajor.add(s);
            }
        }
        return new ResponseEntity<>(studentsByMajor, HttpStatus.OK);
    }
   
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> getStudentsByGpa(@RequestParam Double gpa) {
        List<Student> studentsByGpa = new ArrayList<>();
        for (Student s : students) {
            if (s.getGpa() >= gpa) {
                studentsByGpa.add(s);
            }
        }
        return new ResponseEntity<>(studentsByGpa, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student studentDetails) {
        for (Student existingStudent : students) {
            if (existingStudent.getStudentId().equals(studentId)) {
                existingStudent.setFirstName(studentDetails.getFirstName());
                existingStudent.setLastName(studentDetails.getLastName());
                existingStudent.setEmail(studentDetails.getEmail());
                existingStudent.setMajor(studentDetails.getMajor());
                existingStudent.setGpa(studentDetails.getGpa());
                
                return new ResponseEntity<>(existingStudent, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}