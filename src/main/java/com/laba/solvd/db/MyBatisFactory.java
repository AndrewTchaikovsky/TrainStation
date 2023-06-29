package com.laba.solvd.db;

import com.laba.solvd.db.dao.interfaces.*;
import com.laba.solvd.db.mapper.EmployeeMapper;
import com.laba.solvd.db.mapper.PlatformMapper;
import com.laba.solvd.db.mapper.PlatformStatusMapper;
import com.laba.solvd.db.mapper.TrainStationMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisFactory implements IFactory {
    private static SqlSessionFactory sqlSessionFactory;

    private MyBatisFactory() {
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            synchronized (MyBatisFactory.class) {
                if (sqlSessionFactory == null) {
                    String resource = "mybatis-config.xml";
                    InputStream inputStream;
                    try {
                        inputStream = Resources.getResourceAsStream(resource);
                    } catch (IOException e) {
                        throw new RuntimeException("Error loading MyBatis configuration file.", e);
                    }
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                }
            }
        }
        return sqlSessionFactory;
    }

    @Override
    public ITrainStationDAO createITrainStationDAO() {
        return new TrainStationMapper(createSqlSession());
    }

    @Override
    public IEmployeeDAO createIEmployeeDAO() {
        return new EmployeeMapper(createSqlSession());
    }

    @Override
    public IPlatformDAO createIPlatformDAO() {
        return new PlatformMapper(createSqlSession());
    }

    @Override
    public IPlatformStatusDAO createIPlatformStatusDAO() {
        return new PlatformStatusMapper(createSqlSession());
    }

    public SqlSession createSqlSession() {
        return sqlSessionFactory.openSession();
    }

}
