package com.usg.demo.controllers;

import com.usg.demo.models.InsertModel;
import com.usg.demo.services.StudentService;
import com.usg.demo.models.Baseresponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentCtrl {

    @Autowired
    StudentService studentService;


    @GetMapping(value = "/number-of-students")
    public Baseresponse number_of_students() {
        return studentService.count();
    }

    @GetMapping(value = "/get-all-students")
    public Baseresponse get_all_students() {
        return studentService.getAllStudent();
    }

    @PostMapping(value = "add-student")
    public Baseresponse add_student(@RequestBody InsertModel students) {
        return studentService.addStudent(students);
    }

    @PostMapping(value = "check-headers", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> check_headers(
            @RequestHeader(name = "x-api-key",required = false) String x_api_key,
            @RequestHeader(name = "x-api-secret",required = false) String x_api_secret,
            @RequestBody InsertModel students
    ) {
        String responseCode = "99";
        String message = "Unauthorized";
        if(x_api_key == null || x_api_secret == null || x_api_key.isEmpty() || x_api_secret.isEmpty()){
            Baseresponse baseresponse = new Baseresponse(responseCode, message, null);
            return new ResponseEntity(baseresponse, HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().body(students);
    }

    @DeleteMapping(value = "delete-student/{student_id}")
    public Baseresponse delete_student(@PathVariable int student_id) {
        return  studentService.deleteStudent(student_id);
    }
}
