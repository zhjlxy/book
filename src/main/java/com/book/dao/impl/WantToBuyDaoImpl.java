package com.book.dao.impl;

import com.book.dao.UserDao;
import com.book.dao.WantToBuyDao;
import com.book.entity.User;
import com.book.entity.WantToBuy;
import com.book.vo.UserVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by admin on 2016/4/25.
 * 用户的dao实现类
 */
@Component
public class WantToBuyDaoImpl extends BaseDaoImpl implements WantToBuyDao {

    @Override
    public WantToBuy get(String s) {
        return (WantToBuy)getCurrentSession().get(WantToBuy.class, s);
    }

    @Override
    public List<WantToBuy> list() {
        return getCurrentSession().createQuery(getBaseSql()).list();
    }

    @Override
    public String save(WantToBuy entity) {
        return (String)getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(WantToBuy entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(String s) {
        WantToBuy wantToBuy = get(s);
        if(wantToBuy!=null){
            getCurrentSession().delete(wantToBuy);
        }
    }

    @Override
    public String getBaseSql() {
        return "from WantToBuy order by cts desc";
    }
}
