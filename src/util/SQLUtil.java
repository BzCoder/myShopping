package util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SQLUtil {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/shoppingdata";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";


    static private SQLUtil sqlUtil;

    static public SQLUtil getInstance() {
        if (sqlUtil == null) {
            sqlUtil = new SQLUtil();
        }
        return sqlUtil;
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeSQL(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }

    private PreparedStatement getPreparedStatement(String sql, List<Object> params) {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    if (params.get(i) instanceof String) {
                        ps.setString(i + 1, (String) params.get(i));
                    } else if (params.get(i) instanceof Integer) {
                        ps.setInt(i + 1, (Integer) params.get(i));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public int updateSQL(String sql) {
        return updateSQL(sql, null);
    }

    public int updateSQL(String sql, List<Object> params) {
        PreparedStatement ps = null;
        ps = getPreparedStatement(sql, params);
        try {
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public <T> List<T> executeSQL(String sql, Class<T> clazz) {
        return executeSQL(sql, null, clazz);
    }

    public <T> List<T> executeSQL(String sql, List<Object> params, Class<T> clazz) {
        Connection conn = getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<T> data = new ArrayList<>();
        try {
            ps = getPreparedStatement(sql, params);
            rs = ps.executeQuery();
            String fieldTypeName;
            Field[] fields = clazz.getDeclaredFields();
            while (rs.next()) {
                T temp = clazz.newInstance();
                for (int i = 1; i <= fields.length; i++) {
                    Field field = fields[i - 1];
                    field.setAccessible(true);
                    if (Modifier.toString(field.getModifiers()).contains("static")
                            || Modifier.toString(field.getModifiers()).contains("final")) {
                        continue;
                    }
                    fieldTypeName = field.getType().getSimpleName().toLowerCase(Locale.getDefault());
                    switch (fieldTypeName) {
                        case "int":
                        case "integer":
                            field.set(temp, rs.getInt(i));
                            break;
                        case "string":
                            field.set(temp, rs.getString(i));
                            break;
                        case "short":
                            field.set(temp, rs.getShort(i));
                            break;
                        case "long":
                            field.set(temp, rs.getLong(i));
                            break;
                        case "boolean":
                            field.set(temp, rs.getBoolean(i));
                            break;
                        case "float":
                            field.set(temp, rs.getFloat(i));
                            break;
                        case "double":
                            field.set(temp, rs.getDouble(i));
                            break;
                        default:
                            break;
                    }
                }
                data.add(temp);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            closeSQL(conn, ps, rs);
        }

        return null;
    }
}
