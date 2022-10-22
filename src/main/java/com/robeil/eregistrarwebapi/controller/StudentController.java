package com.robeil.eregistrarwebapi.controller;

import com.robeil.eregistrarwebapi.dto.StudentDTO;
import com.robeil.eregistrarwebapi.model.Student;
import com.robeil.eregistrarwebapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/eRegistrar/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

   // @GetMapping("/list/{pageNo}")
    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> addNewStudent(@RequestBody Student student) {
        var newStudent = studentService.addNewStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    @GetMapping("/get/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId){
        var studentById = studentService.getStudentByStudentId(studentId);
        return ResponseEntity.ok(studentById);
    }

    @PutMapping(path = {"/update/{studentId}"})
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        var updateStudent = studentService.updateStudentByStudentId(studentId, student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping(value = {"/delete/{studentId}"})
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Long studentId) {
        var deletedStudent = studentService.deleteStudentByStudentId(studentId);
        return ResponseEntity.ok(deletedStudent);
    }
}
