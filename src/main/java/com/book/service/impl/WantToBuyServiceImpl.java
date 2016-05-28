package com.book.service.impl;

import com.book.dao.WantToBuyDao;
import com.book.entity.WantToBuy;
import com.book.service.UserService;
import com.book.service.WantToBuyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * Created by lixuy on 2016/5/28.
 */
@Service
public class WantToBuyServiceImpl implements WantToBuyService {

    @Autowired
    private WantToBuyDao wantToBuyDao;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public WantToBuy getBuyId(String id) {
        WantToBuy wantToBuy =  wantToBuyDao.get(id);
        if(wantToBuy != null){
            wantToBuy.setUserName(userService.getUserNameById(wantToBuy.getUserId()));
        }

        return  wantToBuy;
    }

    @Override
    public String add(WantToBuy wantToBuy) {
        if(StringUtils.isBlank(wantToBuy.getTitle())){
            throw new RuntimeException("title is empty");
        }

        if(StringUtils.isBlank(wantToBuy.getContent())){
            throw new RuntimeException("content is empty");
        }
        String userId = (String) session.getAttribute(UserServiceImpl.USERID);
        if(StringUtils.isBlank(userId)){
            throw new RuntimeException("userId is empty");
        }
        wantToBuy.setId(UUID.randomUUID().toString().replace("-", ""));
        wantToBuy.setUserId(userId);
        return wantToBuyDao.save(wantToBuy);
    }

    @Override
    public List<WantToBuy> queryByPage(int pageSize, int pageNum){
        int firstNum = pageSize*(pageNum-1);
         List<WantToBuy> list = wantToBuyDao.queryWithPage(firstNum,pageSize, null);
        if(list != null){
            for(WantToBuy wantToBuy : list){
                wantToBuy.setUserName(userService.getUserNameById(wantToBuy.getUserId()));
                wantToBuy.setCtsStr(dateFormat.format(wantToBuy.getCts()));
            }
        }

        return list;
    }

    @Override
    public int queryPageTotal(){
        return wantToBuyDao.queryTotal(null);
    }
}
