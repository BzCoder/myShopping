package service;

import databean.BookBean;
import databean.UserBean;
import util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderService {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public void submitOrder(MyCartService cart, UserBean user) {
        String sql = "insert into `order`(user_id, total_price, order_date) values(?,?,default)";
        SQLUtil util;
        try {
            util = new SQLUtil();
            conn =util.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getId());
            ps.setDouble(2, cart.getMyCartSum());
            ps.executeUpdate();

            sql = "select MAX(order_id) from `order`";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int order_id = 0;
            if(rs.next())
            {
                order_id = rs.getInt(1);
            }

            ArrayList al = cart.showMyCart();
            for (int i = 0; i < al.size(); i++) {
                BookBean book = (BookBean) al.get(i);
                sql = "insert into `order_detail` values(null,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, order_id);
                ps.setString(2, book.getName());
                ps.setString(3, book.getId());
                ps.setInt(4, book.getNums());
                ps.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw  new RuntimeException(e.getMessage());
        }
        finally {
             SQLUtil.closeSQL(conn,ps,rs);
        }


        //批量提交

    }
}
