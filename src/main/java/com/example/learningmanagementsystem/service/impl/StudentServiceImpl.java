package com.example.learningmanagementsystem.service.impl;

import com.example.learningmanagementsystem.entity.Student;
import com.example.learningmanagementsystem.exception.ResourceNotFoundException;
import com.example.learningmanagementsystem.repository.StudentRepository;
import com.example.learningmanagementsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Студент", id));
    }

    @Override
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student student){
        Student existingStudent = getStudentById(id);
        existingStudent.setName(student.getName());
        existingStudent.setLastName(student.getLastName());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
}
