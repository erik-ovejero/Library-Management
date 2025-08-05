package com.mapper;

import com.domain.BorrowingBooks;
import com.domain.BorrowingBooksExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowingBooksMapper {
    long countByExample(BorrowingBooksExample example);

    int deleteByExample(BorrowingBooksExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BorrowingBooks record);

    int insertSelective(BorrowingBooks record);

    List<BorrowingBooks> selectByExample(BorrowingBooksExample example);

    BorrowingBooks selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BorrowingBooks record, @Param("example") BorrowingBooksExample example);

    int updateByExample(@Param("record") BorrowingBooks record, @Param("example") BorrowingBooksExample example);

    int updateByPrimaryKeySelective(BorrowingBooks record);

    int updateByPrimaryKey(BorrowingBooks record);

    // Query all borrowing records of the user with userId
    List<BorrowingBooks> selectAllBorrowRecord(@Param("userId") int userId);

    // Query the total number of borrowing records for the user with userId
    int selectAllRecordCount(@Param("userId") int userId);

    // Paginated query for all records
    List<BorrowingBooks> selectAllByPage(@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    // Query total number of all records
    int selectAll();
}