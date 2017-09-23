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
        /**
         * 1.加载mybatis的配置文件，初始化mybatis，创建出SqlSessionFactory，是创建SqlSession的工厂
         * 这里只是为了演示的需要，SqlSessionFactory临时创建出来，在实际的使用中，SqlSessionFactory只需要创建一次，当作单例来使用
         */
        String mybatisConfigPath = "spring/mybatisConfig-test.xml";
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigPath);
        //2. 从SqlSession工厂 SqlSessionFactory中创建一个SqlSession，进行数据库操作
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonQuestionType type = sqlSession.selectOne("org.test.dao.CommonQuestionTypeMapper.get", 1);
        System.out.println(type);
    }
}
