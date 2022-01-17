package com.wangxb.mapper.service.impl;

import com.wangxb.mapper.entity.Book;
import com.wangxb.mapper.mapper.BookMapper;
import com.wangxb.mapper.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2021-10-20
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
