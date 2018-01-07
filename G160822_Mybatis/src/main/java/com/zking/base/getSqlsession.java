package com.zking.base;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author: longyt
 * @create: 2018-01-06 18:50
 * @desc:
 **/
public class getSqlsession {

    SqlSessionFactory  sqlSessionFactory;
    static {
        try {
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("Mybatis_config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }



}
