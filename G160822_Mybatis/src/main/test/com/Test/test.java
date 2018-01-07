package com.Test;

import com.zking.Entity.StudentEntity;
import com.zking.dao.StudentDao;
import org.apache.ibatis.javassist.ClassPath;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.List;

/**
 * @author: longyt
 * @create: 2018-01-06 10:58
 * @desc:
 **/
public class test {

    @Test
    public void testMybatis(){
        try {
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("Mybatis_config.xml"));
            SqlSession sqlSession = sqlSessionFactory.openSession();
            /*List<StudentEntity> list = sqlSession.selectList("student.getallStudent");
            for (StudentEntity s : list) {
                System.out.println(s.toString());
            }*/
           /* StudentEntity studentEntity = sqlSession.selectOne("student.getoneStudent",12);
            System.out.println(studentEntity.toString());*/

          /* StudentEntity studentEntity=new StudentEntity(21,"女","sbb");
           int a = sqlSession.insert("student.insertStudent",studentEntity);
           System.out.println(a);
           sqlSession.commit();
            sqlSession.close();*/
          StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
         /* List<StudentEntity> list = studentDao.getAllStudnet();
            for (StudentEntity s : list) {
                System.out.println(s.toString());
            }*/
         /*StudentEntity studentEntity=new StudentEntity(14,21,"女","saa");
         studentDao.updateStudent(studentEntity);*/
        studentDao.deleteStudent(15);
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
