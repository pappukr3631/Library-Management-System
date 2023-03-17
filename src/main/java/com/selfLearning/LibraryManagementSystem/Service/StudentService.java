package com.selfLearning.LibraryManagementSystem.Service;

import com.selfLearning.LibraryManagementSystem.DTOs.RequestDtos.AddStudentRequestDto;
import com.selfLearning.LibraryManagementSystem.DTOs.ResponseDtos.StudentResponseDto;
import com.selfLearning.LibraryManagementSystem.Entities.LibraryCard;
import com.selfLearning.LibraryManagementSystem.Entities.Student;
import com.selfLearning.LibraryManagementSystem.Enums.CardStatus;
import com.selfLearning.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String addStudent(AddStudentRequestDto addStudentRequestDto) {
        Student student = new Student();

        //set attributes of student
        student.setName(addStudentRequestDto.getName());
        student.setAge(addStudentRequestDto.getAge());
        student.setEmail(addStudentRequestDto.getEmail());
        student.setMobileNo(addStudentRequestDto.getMobileNo());
        student.setDepartment(addStudentRequestDto.getDepartment());

        //Issue the libraryCard
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        //set card to the student object
        student.setLibraryCard(card);
        //save student and card will be saved automatically by Cascading effect

        studentRepository.save(student);

        return student.getName() + " now you are a member of " + student.getDepartment() + " department.";
    }

    public StudentResponseDto getStudent(int admissionId) {
        Student student;
        try {
            student = studentRepository.findById(admissionId).get();
        }catch (Exception e) {
            System.out.println("Invalid admissionId! " + e.getMessage());
            return null;
        }

        StudentResponseDto studentResponseDto = new StudentResponseDto();
        //setting attributes
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setMobileNe(student.getMobileNo());
        studentResponseDto.setDepartment(student.getDepartment());
        studentResponseDto.setLibraryCardId(student.getLibraryCard().getCardId());

        return studentResponseDto;
    }

    public List<StudentResponseDto> findByAge(int inputAge) {
        List<Student> studentList = studentRepository.findByAge(inputAge);

        //to make studentDto list
        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for(Student student : studentList) {
            StudentResponseDto responseDto = new StudentResponseDto();
            //setting attributes
            responseDto.setName(student.getName());
            responseDto.setAge(student.getAge());
            responseDto.setEmail(student.getEmail());
            responseDto.setMobileNe(student.getMobileNo());
            responseDto.setDepartment(student.getDepartment());
            responseDto.setLibraryCardId(student.getLibraryCard().getCardId());
            //add to the list
            studentResponseDtoList.add(responseDto);
        }
        return studentResponseDtoList;
    }

    public String updateEmail(int studentId, String newEmail) {
        Student student;
        try {
            student = studentRepository.findById(studentId).get();
        }catch (Exception e) {
            System.out.println("Invalid StudentId! " + e.getMessage());
            return null;
        }

        student.setEmail(newEmail);

        student = studentRepository.save(student);

        return "Email updated successfully. \n Your new email is: " + student.getEmail();
    }

    public String updateMobileNo(int studentId, String newMobileNo) {
        Student student;
        try {
            student = studentRepository.findById(studentId).get();
        }catch (Exception e) {
            System.out.println("Invalid StudentId! " + e.getMessage());
            return null;
        }

        student.setMobileNo(newMobileNo);

        student = studentRepository.save(student);

        return "Mobile no. updated successfully. \n Your new mobile no. is: " + student.getMobileNo();
    }
}
