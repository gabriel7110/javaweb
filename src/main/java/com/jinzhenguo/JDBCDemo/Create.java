package com.jinzhenguo.JDBCDemo;

import com.alibaba.druid.pool.DruidDataSource;
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

public class Create {
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
        PreparedStatement ps = con.prepareStatement(sql);

        int id = 2;
        String name = "王刚";
        String course = "线性代数";

        //创建日期对象(利用java多态实现Date内容)
        //使用Calendar类
        Calendar calendar = Calendar.getInstance();//创建实例对象
        calendar.set(Calendar.YEAR,1994);
        calendar.set(Calendar.MONTH,6);
        calendar.set(Calendar.DAY_OF_MONTH,4); //设置具体日期
        Date date = calendar.getTime();//转换为Date类型
        java.sql.Date birthday = new java.sql.Date(date.getTime());//将不同Date进行强制转化
        //设置参数
        ps.setInt(1, id);
        ps.setString(2,name);
        ps.setString(3,course);
        ps.setDate(4,birthday);
        //执行sql
        int count = ps.executeUpdate();//影响行数
        //处理结果
        System.out.println(count>0);//是否成功

        //释放资源
        ps.close();
        con.close();

    }
}