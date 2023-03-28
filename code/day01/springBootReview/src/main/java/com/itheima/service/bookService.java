package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.domain.Book;

public interface bookService extends IService<Book> {
    public IPage<Book> getPage(int currentPage,int pageSize,Book book);
}
