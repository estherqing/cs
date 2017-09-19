package com.test.util;

import com.test.model.CommonQuestionType;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author esther
 * @create 2017-09-19 14:18
 * $DESCRIPTION}
 */

public class MybatisUtil {
    public static void main(String[] args) throws IOException {
        String mybatisConfigPath = "spring/datasource.xml";
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigPath);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonQuestionType type = sqlSession.selectOne("org.test.dao.CommonQuestionTypeMapper.get",1);
        System.out.println(type);
    }
}
