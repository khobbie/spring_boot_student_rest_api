package com.usg.demo.services;

import com.usg.demo.models.Baseresponse;
import com.usg.demo.models.InsertModel;
import com.usg.demo.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentService implements StudentRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Baseresponse count() {
        String sql = "SELECT COUNT(*) FROM students st ORDER BY st.st_id DESC";
        int results = jdbcTemplate.queryForObject(sql, Integer.class);
        String responseCode = "";
        String message = "";

        if(results < 1){
            responseCode = "99";
            message = "No students found";
        }else{
            responseCode = "00";
            message = "There are " + results + " students available";

        }

        Baseresponse baseresponse = new Baseresponse(responseCode, message, null);

        return baseresponse;
    }

    @Override
    public Baseresponse getAllStudent() {
        String sql = "SELECT * FROM students st ORDER BY st.st_id DESC";

        List students = jdbcTemplate.queryForList(sql);
        String responseCode = "";
        String message = "";
//        Object data = null;
        if(students.size() < 1){
            responseCode = "99";
            message = "No students found";
            students = null;
        }else{
            responseCode = "00";
            message = "Fetched students successfully";

        }

        Baseresponse baseresponse = new Baseresponse(responseCode, message, students);

        return baseresponse;
    }

    @Override
    public Baseresponse addStudent(InsertModel student) {
        String sql = "INSERT INTO students (st_name, st_gender, st_age) VALUES (? , ?, ?)";
        int results = jdbcTemplate.update(sql, student.getName(), student.getGender(), student.getAge());
        String responseCode = "";
        String message = "";
        Object data = null;
            if(results < 1){
                responseCode = "99";
                message = "API Server Error";
            }else{
                responseCode = "00";
                message = "New Student added successfully";
                data = student;

            }

            Baseresponse baseresponse = new Baseresponse(responseCode, message, data);

            return baseresponse;

    }

    @Override
    public Baseresponse deleteStudent(int student_id) {
        String sql_check = "SELECT COUNT(*) FROM students st WHERE  st.st_id = ?";
        Object[] args = new Object[] {student_id};

        int results_check = jdbcTemplate.queryForObject(sql_check,args, Integer.class);
        String responseCode = "";
        String message = "";


        // CHECK IF STUDENT WITH ID EXITS
        if(results_check < 1){
            responseCode = "99";
            message = "Student with id: {#STI00" + student_id + "} does not exist";
            Baseresponse baseresponse = new Baseresponse(responseCode, message, null);

            return baseresponse;
        }
        //END CHECK

        String sql = "DELETE FROM students st WHERE  st.st_id = ?";
        int results = jdbcTemplate.update(sql, student_id);

        if(results < 1){
            responseCode = "99";
            message = "API Server Error";
        }else{
            responseCode = "00";
            message = "Student with id: {#STI00" + student_id + "} deleted  successfully";
        }

        Baseresponse baseresponse = new Baseresponse(responseCode, message, null);

        return baseresponse;

    }
}
