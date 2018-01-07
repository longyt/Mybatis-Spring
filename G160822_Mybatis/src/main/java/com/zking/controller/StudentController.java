package com.zking.controller;

import com.zking.Entity.StudentEntity;
import com.zking.base.getSqlsession;
import com.zking.dao.StudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @author: longyt
 * @create: 2018-01-06 18:34
 * @desc:
 **/
@Controller
public class StudentController {

    public SqlSession getSqlsession(){
        SqlSessionFactory sqlSessionFactory= null;
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("Mybatis_config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
    @ResponseBody
    @RequestMapping("getAllStudent")
    public List<StudentEntity>  getAllStudnet(@RequestBody StudentEntity studentEntity) throws IOException {
        SqlSession sqlSession = getSqlsession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        System.out.println(studentDao.getAllStudnet(studentEntity).toString());
        return studentDao.getAllStudnet(studentEntity);
    }

    @RequestMapping("insertStudent")
    public void insertStudent(@RequestBody StudentEntity studentEntity){
        SqlSession sqlSession = getSqlsession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        studentDao.InsertStudent(studentEntity);
        sqlSession.commit();
        sqlSession.close();
    }

    @ResponseBody
    @RequestMapping("queryoneStudent")
    public StudentEntity queryOneStudent(@RequestParam String id){
        SqlSession sqlSession = getSqlsession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        StudentEntity studentEntity = studentDao.getStudent(Integer.parseInt(id));
        return studentEntity;
    }

    @RequestMapping("updateStudent")
    public void updateStudent(@RequestBody StudentEntity studentEntity){
        SqlSession sqlSession = getSqlsession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        studentDao.updateStudent(studentEntity);
        sqlSession.commit();
        sqlSession.close();
    }

    @RequestMapping("deleteStudent")
    public void deleteStudent(@RequestParam String studentid){
        SqlSession sqlSession = getSqlsession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        System.out.println(studentid);
        studentDao.deleteStudent(Integer.parseInt(studentid));
        sqlSession.commit();
        sqlSession.close();
    }

    @ResponseBody
    @RequestMapping("SearchStudent")
    public StudentEntity SeachStudent(@RequestBody StudentEntity studentEntity){
        System.out.println("params:"+studentEntity.toString());
        SqlSession sqlSession = getSqlsession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        System.out.println("result:"+studentDao.SearchStudent(studentEntity).toString());
        return studentDao.SearchStudent(studentEntity);
    }
}
