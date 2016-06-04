package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.entity.Book;
import com.book.service.BookService;
import com.book.vo.BookListVo;
import com.book.vo.BookVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixuy on 2016/4/16.
 */
@Service
public class BookServiceImpl  implements BookService{

    @Autowired
    private BookDao bookDao;

    @Autowired
    private HttpSession session;


    @Override
    public List<Book> list(String type, String bookName) {
        return bookDao.list(type,bookName);
    }

    @Override
    public List<Book> list(int pageSize, int pageNum, String type, String bookName) {
        int firstNum = pageSize*(pageNum-1);
        return bookDao.list(firstNum, pageSize, type,bookName);
    }

    @Override
    public BookVo getById(int id) {
        Book book =  bookDao.get(id);

        return EntityToVo(book);
    }

    /**
     * 添加书的service 方法
     *      返回添加成功后的id；
     * @param bookVo 要添加书的对象
     * @return 添加的书的Id
     */
    @Override
    public int saveOrUpdate(BookVo bookVo) {
        Book book = voToEntity(bookVo);

        if(bookVo.getId()==null){
            // add
            return bookDao.save(book);
        }else{
            //update
            bookDao.saveOrUpdate(book);
            return bookVo.getId();
        }
    }

    @Override
    public List<BookListVo> querySellBook(int pageSize, int pageNum) {
        Map<String, Object> criteria = getSellUserCriteria();
        int firstNum = pageSize*(pageNum-1);
        List<Book> result = bookDao.queryWithPage(firstNum, pageSize, criteria);
        List<BookListVo> bookListVos = toBookListVoList(result);
        return bookListVos;
    }


    private List<BookListVo> toBookListVoList(List<Book> result) {
        List<BookListVo> bookListVos = new ArrayList<BookListVo>();
        if(result!= null){
            for(Book book : result){
                BookListVo bookListVo = toBookListVo(book);
                bookListVos.add(bookListVo);
            }
        }

        return bookListVos;
    }

    private BookListVo toBookListVo(Book book) {
        BookListVo blvo = new BookListVo();
        blvo.setId(book.getId());
        blvo.setName(book.getName());
        blvo.setAuthor(book.getAuthor());
        blvo.setDesc(book.getDesc());
        blvo.setNewStatus(book.getNewStatus());
        blvo.setPicture(book.getPicture());
        blvo.setPrice(book.getPrice());
        blvo.setSellStatus(book.getSellStatus());
        blvo.setSellStatusDesc(getBookSellStatusDesc(book.getSellStatus()));
        blvo.setSellUserId(book.getSellUserId());
        blvo.setTypeId(book.getTypeId());


        return blvo;
    }

    private String getBookSellStatusDesc(String bookSellStatus){
        String result = StringUtils.EMPTY;
        if(Book.SELL_IN.equalsIgnoreCase(bookSellStatus)){
            result = Book.SELL_IN_DESC;
        }
        if(Book.SELL_AUDIT.equalsIgnoreCase(bookSellStatus)){
            result = Book.SELL_AUDIT_DESC;
        }
        if(Book.SELL_AUDIT_FAIL.equalsIgnoreCase(bookSellStatus)){
            result = Book.SELL_AUDIT_FAIL_DESC;
        }
        if(Book.SELL_OFF.equalsIgnoreCase(bookSellStatus)){
            result = Book.SELL_OFF_DESC;
        }
        if(Book.SELL_OVER.equalsIgnoreCase(bookSellStatus)){
            result = Book.SELL_OVER_DESC;
        }

        return result;
    }

    private Map<String, Object> getSellUserCriteria() {
        String userId = (String) session.getAttribute("userId");
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("sellUserId", userId);
        return criteria;
    }

    @Override
    public int querySellBookTotal() {
        return bookDao.queryTotal(getSellUserCriteria());
    }

    /**
     *  更新销售书本的状态
     * @param bookId
     * @param sellStatus
     */
    @Override
    public void updateSellStatus(int bookId, String sellStatus) {
        checkSellStatus(sellStatus);
        Book book = bookDao.get(bookId);
        book.setSellStatus(sellStatus);
        bookDao.saveOrUpdate(book);
    }

    @Override
    public List<BookListVo> queryAuthBook(int pageSize, int pageNum) {
        Map<String, Object> criteria = getAuthUserCriteria();
        int firstNum = pageSize*(pageNum-1);
        List<Book> result = bookDao.queryWithPage(firstNum, pageSize, criteria);
        List<BookListVo> bookListVos = toBookListVoList(result);

        return bookListVos;
    }

    private Map<String,Object> getAuthUserCriteria() {
        Map<String,Object> criteria = new HashMap<String, Object>();
        criteria.put("sellStatus", Book.SELL_AUDIT);
        return criteria;
    }

    @Override
    public int queryAuthBookTotal() {
        return bookDao.queryTotal(getAuthUserCriteria());
    }

    /**
     * 校验更新状态
     * @param sellStatus
     */
    private void checkSellStatus(String sellStatus) {
        if(!Book.SELL_OFF.equalsIgnoreCase(sellStatus) && !Book.SELL_IN.equalsIgnoreCase(sellStatus)
                && !Book.SELL_OVER.equalsIgnoreCase(sellStatus) && !Book.SELL_AUDIT.equalsIgnoreCase(sellStatus)){
            new RuntimeException("sellStatus error");
        }
    }

    private Book voToEntity(BookVo bookVo) {
        Book book = new Book();
        book.setAuthor(bookVo.getAuthor());
        book.setName(bookVo.getName());
        book.setNewStatus(bookVo.getNew_status());
        book.setDesc(bookVo.getDesc());
        book.setPicture("/"+bookVo.getPicture());
        book.setPrice(bookVo.getPrice());
        book.setTypeId(bookVo.getType());

        // update
        if(bookVo.getId()!= null){
            book.setId(bookVo.getId());
        }

        // 用户
        String userId = (String) session.getAttribute("userId");
        book.setSellUserId(userId);
        book.setSellStatus(Book.SELL_AUDIT);

        return book;
    }

    public BookVo EntityToVo(Book book){
        BookVo vo = new BookVo();
        if(book != null){
            vo.setId(book.getId());
            vo.setPicture(book.getPicture());
            vo.setDesc(book.getDesc());
            vo.setAuthor(book.getAuthor());
            vo.setName(book.getName());
            vo.setNew_status(book.getNewStatus());
            vo.setPrice(book.getPrice());
            vo.setType(book.getTypeId());
        }
        return vo;
    }
}
