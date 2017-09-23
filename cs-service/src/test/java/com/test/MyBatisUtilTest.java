package com.test;

import com.test.model.CommonQuestionType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author esther
 * @create 2017-09-23 12:09
 * $DESCRIPTION}
 */

public class MyBatisUtilTest {
    public static void main(String[] args) {
        String[] xmls = {"/spring/applicationContext.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(xmls);
        SqlSessionFactory sqlSessionFactory = context.getBean("sqlSessionFactory", SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonQuestionType type = sqlSession.selectOne("org.test.dao.CommonQuestionTypeMapper.get", 1);
        System.out.println(type);
    }
}
