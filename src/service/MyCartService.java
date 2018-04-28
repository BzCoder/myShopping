package service;

import databean.BookBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MyCartService {
    HashMap<String, BookBean> hm = new HashMap<>();

    //添加书籍
    public void addBook(String id) {
        if (hm.containsKey(id)) {
            BookBean t = hm.get(id);
            t.setNums(t.getNums() + 1);
        } else {
            if (BookService.getBookBeanByID(id).size() > 0) {
                hm.put(id, BookService.getBookBeanByID(id).get(0));
            }
        }
    }

    //删除书籍
    public void delBook(String id) {
        BookBean t = hm.get(id);
        if(t.getNums()>1)
        {
            t.setNums(t.getNums() - 1);
        }
        else
        {
            hm.remove(id);
        }
    }

    //更新书籍
    public void updateBook(String id, String num) {
        BookBean bean = hm.get(id);
        bean.setNums(Integer.parseInt(num));
    }

    //获取购物车的所有商品
    public ArrayList showMyCart() {
        ArrayList<BookBean> temp = new ArrayList<>();
        Iterator iterator = hm.keySet().iterator();
        while (iterator.hasNext()) {
            String id = (String) iterator.next();
            BookBean bean = hm.get(id);
            temp.add(bean);
        }
        return temp;
    }

    public double getMyCartSum() {
        double sum = 0;
        Iterator iterator = hm.keySet().iterator();
        while (iterator.hasNext()) {
            String id = (String) iterator.next();
            BookBean bean = hm.get(id);
            sum += bean.getPrice()*bean.getNums();
        }
        return sum;
    }

    //清空购物车
    public void clearBook() {
        hm.clear();
    }
}
