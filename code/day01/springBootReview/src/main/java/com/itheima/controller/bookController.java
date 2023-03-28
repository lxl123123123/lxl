package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.Book;
import com.itheima.service.bookService;
import com.itheima.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class bookController {
    @Autowired
    private bookService bookService;

    public void setBookService(bookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public R save(@RequestBody Book book){
        return new R(bookService.save(book));
    }
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        return new R(bookService.removeById(id));
    }
    @PutMapping
    public R update(@RequestBody Book book){
        return new R(bookService.updateById(book));
    }
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,bookService.getById(id));
    }
    @GetMapping
    public R getAll(){
        return new R(true,bookService.list());
    }
    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize,Book book){
        IPage<Book> page = bookService.getPage(currentPage, pageSize, book);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()){
            page = bookService.getPage((int)page.getPages(),pageSize,book);
        }
        return new R(true,page);
    }
}
