package com.book.service;

import com.book.entity.WantToBuy;

import java.util.List;

/**
 * Created by lixuy on 2016/5/28.
 */
public interface WantToBuyService {

    public WantToBuy getBuyId(String id);

    public String add(WantToBuy wantToBuy);

    public List<WantToBuy> queryByPage(int pageSize, int pageNum);

    public int queryPageTotal();
}
