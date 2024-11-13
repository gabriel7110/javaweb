package com.jinzhenguo.JDBCDemo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class AddBatch {
    public static void main(String[] args) throws Exception {

        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:\\study\\javaweb\\JDBCdemo\\src\\main\\resources\\db.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取数据库连接
        Connection con = dataSource.getConnection();
        //定义SQL
        String sql = "INSERT INTO teacher (id,name,course,birthday) VALUES (?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql);) {
            // 设置参数
            int id = 3;
            for (int i = 0; i < 500; i++,id++) {
                ps.setInt(1, id);
                ps.setString(2, "李华");
                ps.setString(3, "高级数据库");
                Calendar calendar = Calendar.getInstance();//创建实例对象
                calendar.set(Calendar.YEAR, 1993);
                calendar.set(Calendar.MONTH, 5);
                calendar.set(Calendar.DAY_OF_MONTH, 4); //设置具体日期
                Date date = calendar.getTime();//转换为Date类型
                java.sql.Date birthday = new java.sql.Date(date.getTime());
                ps.setDate(4, birthday);
                // 添加到批处理
                ps.addBatch();
                if (i % 100 == 0) { // 每100条记录执行一次批处理
                    ps.executeBatch();
                    ps.clearBatch();
                    System.out.println("完成100条数据批量插入");
                }
            }
            ps.executeBatch();
            con.commit();
        }
         catch(SQLException e){
                con.rollback();
                e.printStackTrace();
            }

    }
}


