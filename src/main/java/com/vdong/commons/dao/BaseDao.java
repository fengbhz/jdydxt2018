package com.vdong.commons.dao;

import com.mysql.jdbc.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 基础dao
 */
@Repository("baseDao")
public class BaseDao {

    private final Logger logger = LoggerFactory.getLogger(BaseDao.class);
    private JdbcTemplate jdbc;

    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return this.jdbc;
    }

    /**
     * 添加操作日志
     *
     * @param map
     * @return
     */
    public int insertLog(Map<String, String> map) {
        int a = 0;
        final String time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .format(new Date());
        final String id = map.get("id");
        final String account = (map.get("account") == null ? "" : map
                .get("account"));
        final String create_sql = (map.get("sql") == null ? "" : map
                .get("sql"));
        final String originalCoin = (map.get("originalCoin") == null ? "" : map
                .get("originalCoin"));
        final String remark = (map.get("remark") == null ? "" : map
                .get("remark"));

        final String create_user = (map.get("create_user") == null ? "" : map
                .get("create_user"));
        final String sql = "insert into t_log_coin"
                + "(id, account, create_sql, originalCoin, remark,create_time,create_user)"
                + "  values(?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            a = this.getJdbc().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection conn)
                        throws SQLException {
                    PreparedStatement ps = conn.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, id);
                    ps.setString(2, account);
                    ps.setString(3, create_sql);
                    ps.setString(4, originalCoin);
                    ps.setString(5, remark);
                    ps.setString(6, time);
                    ps.setString(7, create_user);
                    return ps;
                }
            }, keyHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }


}
