package com.vdong.commons.db;

import org.springframework.core.io.ClassPathResource;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 数据库管理类
 *
 * @author win7
 */
public class DBManager {
    //public static final String DEFAULT_DRIVER_NAME = "com.mysql.jdbc.Driver";
    //public static final String DEFAULT_DB_URL = "jdbc:mysql://127.0.0.1:3306/jdydxt?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";

    public static String DB_URL = null;

    public static Properties prop = null;


    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

	
	/*public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
	}*/

	/*//获取数据连接
    public static Connection getConnection() {
		ClassPathResource cr = new ClassPathResource("config.properties");//会重新加载spring框架
	    Properties pros = new Properties();
		Connection coon = null;
		try {
			 pros.load(cr.getInputStream());
			Class.forName(DEFAULT_DRIVER_NAME);
			coon = DriverManager.getConnection(pros.getProperty("jdbc_Url"),pros.getProperty("jdbc_name"),pros.getProperty("jdbc_pwd") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coon;
	}*/


    public static Connection getConnection() {
        //会重新加载spring框架
        ClassPathResource cr = new ClassPathResource("config.properties");
        Properties pros = new Properties();
        Connection coon = null;
        try {
            pros.load(cr.getInputStream());
            coon = DriverManager.getConnection(pros.getProperty("jdbc_Url"), pros.getProperty("jdbc_name"), pros.getProperty("jdbc_pwd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coon;
    }

    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public boolean login(String username, String pwd) {

        Connection coon = getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = coon
                    .prepareStatement("select * from admin where userName='"
                            + username + "' and userPw='" + pwd + "'");

            rs = pstmt.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (coon != null) {
                    coon.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public List find(String strsql) {
        List list = new LinkedList();
        Connection connect = null;
        ResultSet rs = null;
        try {
            connect = getConnection();
            rs = connect.createStatement().executeQuery(strsql);
//		System.out.println("JDBC查询语句："+strsql);
            ResultSetMetaData metadata = rs.getMetaData();
            while (rs.next()) {
                HashMap result = new HashMap();
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    String key = metadata.getColumnName(i);
                    result.put(key, rs.getObject(i));
                }
                list.add(result);
            }
            rs.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public boolean loginUser(String username, String pwd) {

        Connection coon = getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = coon
                    .prepareStatement("select * from userinfo where tel='"
                            + username + "' and pwd='" + pwd + "'");

            rs = pstmt.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (coon != null) {
                    coon.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public String getUserType(String tel) {

        Connection coon = getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = coon
                    .prepareStatement("select * from userinfo where tel='"
                            + tel + "'");

            rs = pstmt.executeQuery();
            while (rs.next()) {
                return rs.getString("type");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (coon != null) {
                    coon.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    public static String TextToHtml(String sourcestr) {
        int strlen;
        String restring = "", destr = "";
        strlen = sourcestr.length();
        for (int i = 0; i < strlen; i++) {
            char ch = sourcestr.charAt(i);
            switch (ch) {
                case '<':
                    destr = "<";
                    break;
                case '>':
                    destr = ">";
                    break;
                case '\"':
                    destr = "\"";
                    break;
                case '&':
                    destr = "&";
                    break;
                case 13:
                    destr = "<br>";
                    break;
                case 32:
                    destr = "&nbsp;";
                    break;
                default:
                    destr = "" + ch;
                    break;
            }
            restring = restring + destr;
        }
        return "" + restring;
    }

    public static void main(String[] args) {
        DBManager db = new DBManager();
        db.loginUser("admin", "admin");


    }

}
