package vip.websky.core.boot;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Copyright yang2048@qq.com @沐之永
 * @Author: YangYong
 * @Date: 2018/7/1 0:41
 * @Description: 启动时运行 SQL 脚本
 */
public class RunnerSql {
    @Value("${url}")
    private static String url;

    @Value("${driver}")
    private static String driver;

    @Value("${username}")
    private static String username;

    @Value("${password}")
    private static String password;

    /**
     * 更新数据库脚本
     * @param sqlPath sql 脚本路径 "sql/CC20-01.sql"
     */
    public static void runSqlScript(String sqlPath) {
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            ScriptRunner runner = new ScriptRunner(conn);
            Resources.setCharset(Charset.forName("UTF-8")); //设置字符集,不然中文乱码插入错误
            //runner.setLogWriter(null);//设置是否输出日志
            runner.runScript(Resources.getResourceAsReader(sqlPath));
            runner.closeConnection();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
