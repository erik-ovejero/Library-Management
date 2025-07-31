package com.mapper;

import com.domain.Book;
import com.domain.BookExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    long countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    List<Book> selectBooksByPartInfo(String bookPartInfo);

    Book selectByPrimaryKey(Integer bookId);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    // Find by book category with pagination
    List<Book> selectByCategoryId(@Param("categoryId") int categoryId, @Param("currIndex") int currIndex, @Param("pageSize") int PageSize);

    // Find the total number of books in a specific category
    int selectBookCountByCategoryId(@Param("categoryId") int categoryId);
}