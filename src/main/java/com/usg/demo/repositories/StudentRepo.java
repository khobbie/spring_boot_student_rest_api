package com.usg.demo.repositories;

import com.usg.demo.models.Baseresponse;
import com.usg.demo.models.InsertModel;

public interface StudentRepo {
    Baseresponse count();
    Baseresponse getAllStudent();
    Baseresponse addStudent(InsertModel student);
    Baseresponse deleteStudent(int student_id);
    Baseresponse getOneStudent(int student_id);
}
