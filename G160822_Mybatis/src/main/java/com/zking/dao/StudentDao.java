package com.zking.dao;

import com.zking.Entity.StudentEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao {


    public List<StudentEntity> getAllStudnet(StudentEntity studentEntity);

    public StudentEntity getStudent(int id);

    public void InsertStudent(StudentEntity studentEntity);

    public void updateStudent(StudentEntity studentEntity);

    public void deleteStudent(int sid);

    public StudentEntity SearchStudent(StudentEntity studentEntity);
}
