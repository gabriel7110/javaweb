package com.jinzhenguo.JDBCDemo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class Delete {
    public static void main(String[] args) throws Exception {

        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:\\study\\javaweb\\JDBCdemo\\src\\main\\resources\\db.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取数据库连接
        Connection con = dataSource.getConnection();
        //定义SQL
        String sql = "delete from teacher where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        //设置参数
        int id = 2;
        ps.setInt(1, id);
        //执行sql
        int count = ps.executeUpdate();
        //处理结果
        System.out.println(count>0);//是否成功
        //释放资源
        ps.close();
        con.close();
    }
}