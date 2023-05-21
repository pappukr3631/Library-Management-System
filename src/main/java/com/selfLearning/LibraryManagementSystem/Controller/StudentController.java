package com.selfLearning.LibraryManagementSystem.Controller;

import com.selfLearning.LibraryManagementSystem.DTOs.RequestDtos.AddStudentRequestDto;
import com.selfLearning.LibraryManagementSystem.DTOs.ResponseDtos.StudentResponseDto;
import com.selfLearning.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    /* Working Fine */
    @PostMapping("/add")
    public String addStudent(@RequestBody AddStudentRequestDto addStudentRequestDto) {
        return studentService.addStudent(addStudentRequestDto);
    }
    /* Working Fine */
    @GetMapping("find")
    public StudentResponseDto getStudent(@RequestParam int admissionId) {
        return studentService.getStudent(admissionId);
    }

//    get student by particular age(using inbuilt custom method)
    /* Working Fine */
    @GetMapping("/find_by_age")
    public List<StudentResponseDto> findByAge(@RequestParam int inputAge) {
        return studentService.findByAge(inputAge);
    }

    /* Working Fine */
    @PutMapping("/update_email")
    public String updateEmail(@RequestParam int studentId, @RequestParam String newEmail) {
        return studentService.updateEmail(studentId,newEmail);
    }
    /* Working Fine */
    @PutMapping("/update_mobile")
    public String updateMobile(@RequestParam int studentId, @RequestParam String newMobileNo) {
        return studentService.updateMobileNo(studentId, newMobileNo);
    }
}
