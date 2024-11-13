package com.jinzhenguo.JDBCDemo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ResultSetRow {
    public static void main(String[] args) throws Exception {

        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:\\study\\javaweb\\JDBCdemo\\src\\main\\resources\\db.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取数据库连接
        //定义SQL
        String sql = "SELECT * FROM teacher WHERE id > ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            // 设置参数
            ps.setInt(1, 60);
            // 执行查询
            try (ResultSet rs = ps.executeQuery()) {

                // 移动到倒数第二行
                rs.absolute(-2);
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " "
                        + rs.getString("course") + " " + rs.getString("birthday"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();


        }
    }
}
