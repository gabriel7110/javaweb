package com.jinzhenguo.JDBCDemo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class Update {
    public static void main(String[] args) throws Exception {

        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:\\study\\javaweb\\JDBCdemo\\src\\main\\resources\\db.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取数据库连接
        Connection con = dataSource.getConnection();
        //定义SQL
        String sql = "UPDATE teacher SET name = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        int id = 1;
        String name = "李阳";
        //设置参数
        ps.setString(1,name);
        ps.setInt(2, id);
        //执行sql
        int count = ps.executeUpdate();//影响行数
        //处理结果
        System.out.println(count>0);//是否成功
        //释放资源
        ps.close();
        con.close();
    }
}