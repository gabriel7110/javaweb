package com.jinzhenguo.JDBCDemo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class Retrieve {
    public static void main(String[] args) throws Exception {

        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:\\study\\javaweb\\JDBCdemo\\src\\main\\resources\\db.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取数据库连接
        Connection con = dataSource.getConnection();
        //定义SQL
        String sql = "select * from teacher";
        PreparedStatement ps = con.prepareStatement(sql);

        //执行sql
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "
                    +rs.getString(3)+" "+rs.getString(4));
        }
        //释放资源
        ps.close();
        con.close();
    }
}