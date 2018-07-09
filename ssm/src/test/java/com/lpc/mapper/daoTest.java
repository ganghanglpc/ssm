package com.lpc.mapper;


import com.lpc.po.Items;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class daoTest {

    @Test
    public void daoTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ctx.getBean("SqlSessionFactory");
        SqlSession session = sqlSessionFactory.openSession();
        ItemsMapper itemsMapper = (ItemsMapper) ctx.getBean("itemsMapper");
        Items items = itemsMapper.selectByPrimaryKey(1);
        System.out.println(items);
    }
}
