package service;

import databean.UserBean;
import util.SQLUtil;

import java.util.ArrayList;
import java.util.List;

public class LoginService {
    public static UserBean checkUser(int id, String pwd) {
        String sql = "select * from users where id=? and pwd=?";
        ArrayList param = new ArrayList();
        param.add(id);
        param.add(pwd);
        List<UserBean> result = SQLUtil.getInstance().executeSQL(sql, param, UserBean.class);
        if (result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }
}
