package com.robeil.eregistrarwebapi.service.impl;

import com.robeil.eregistrarwebapi.dto.StudentDTO;
import com.robeil.eregistrarwebapi.model.Student;
import com.robeil.eregistrarwebapi.repository.StudentRepository;
import com.robeil.eregistrarwebapi.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDTO addNewStudent(Student student) {

        var newStudent = studentRepository.save(student);
        return modelMapper.map(newStudent, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        var allStudents = studentRepository.findAll(Sort.by(Sort.Direction.ASC,"firstName"));
        var studentDTO = new ArrayList<StudentDTO>();

        allStudents.forEach(student -> {
            studentDTO.add(modelMapper.map(student, StudentDTO.class));
        });
        return studentDTO;
    }

    @Override
    public StudentDTO updateStudentByStudentId(Long studentID, Student student) {

        var existedStudent = studentRepository.findById(studentID);
        Student updatedStudent = null;

        if (existedStudent.isPresent()) {
            //setting the new data to the existed student
          updatedStudent =   existedStudent.stream()
                    .map(updateStudent -> {
                        updateStudent.setStudentId(student.getStudentId());
                        updateStudent.setStudentNumber(student.getStudentNumber());
                        updateStudent.setFirstName(student.getFirstName());
                        updateStudent.setLastName(student.getLastName());
                        updateStudent.setMiddleName(student.getMiddleName());
                        updateStudent.setCgpa(student.getCgpa());
                        updateStudent.setEnrollmentDate(student.getEnrollmentDate());
                        updateStudent.setIsInternational(student.getIsInternational());
                        return updateStudent;
                    })
                  .findFirst()
                  .get();
          // Saving the updated student to the database
            studentRepository.save(updatedStudent);
            return modelMapper.map(updatedStudent, StudentDTO.class);
        } else {
            throw new IllegalStateException("There is no student with the Id# " + studentID);
        }
    }

    @Override
    public StudentDTO deleteStudentByStudentId(Long studentID) {

        var deletedStudent = studentRepository.findById(studentID);

        if (!deletedStudent.isPresent()) {
            throw new IllegalStateException("There is no student with the Id# " + studentID);
        }

        studentRepository.deleteById(studentID);
        return modelMapper.map(deletedStudent, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentByStudentId(Long studentID) {

        if (studentRepository.findById(studentID).isPresent()) {
            var foundStudent = studentRepository.findById(studentID).get();
            return modelMapper.map(foundStudent, StudentDTO.class);
        }
        throw new IllegalStateException("There is no student with the Id# " + studentID);
    }
}
