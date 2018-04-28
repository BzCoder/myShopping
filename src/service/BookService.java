package service;

import databean.BookBean;
import util.SQLUtil;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    public static List<BookBean> getAllBook() {
        String sql = "select * from book";
        return SQLUtil.getInstance().executeSQL(sql, BookBean.class);
    }

    public static List<BookBean> getBookBeanByID(String id) {
        String sql = "select * from book where id=?";
        ArrayList param = new ArrayList();
        param.add(id);
        return SQLUtil.getInstance().executeSQL(sql, param, BookBean.class);
    }
}
