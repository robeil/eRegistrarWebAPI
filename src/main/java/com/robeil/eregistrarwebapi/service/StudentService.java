package com.robeil.eregistrarwebapi.service;

import com.robeil.eregistrarwebapi.dto.StudentDTO;
import com.robeil.eregistrarwebapi.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    StudentDTO addNewStudent(Student student);
    //Page<StudentDTO> getAllStudents(int pageNo);
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudentByStudentId(Long studentID, Student student);
    StudentDTO deleteStudentByStudentId(Long studentId);
    StudentDTO getStudentByStudentId(Long studentId);

}
