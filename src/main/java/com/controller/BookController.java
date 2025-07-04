package com.controller;

import com.domain.Book;
import com.domain.BookCategory;
import com.domain.Vo.BookVo;
import com.service.IAdminService;
import com.service.IBookCategoryService;
import com.service.IBookService;
import com.utils.page.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BookController {
    @Resource
    private IAdminService adminService;
    @Resource
    private IBookService bookService;
    @Resource
    private IBookCategoryService bookCategoryService;

    /*
     * Admin  enter new book
     *
     * @param book
     * @return
    */
    @RequestMapping("/addBook")
    @ResponseBody
    public String addBook(Book book) {
        boolean res = adminService.addBook(book);
        if (res) {
            return "true";
        }
        return "false";
    }

    /*
     * Return  book query results page
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/showBooksResultPageByCategoryId")
    public String showBooksResultPageByCategoryId(@RequestParam("pageNum") int pageNum, @RequestParam("bookCategory") int bookCategory, Model model) {
        Page<BookVo> page = bookService.findBooksByCategoryId(bookCategory, pageNum);
        model.addAttribute("page", page);
        model.addAttribute("bookCategory", bookCategory);
        return "admin/showBooks";
    }

    /**
     * Return user  book query results page
     *
     * @param bookPartInfo
     * @return
     */
    @RequestMapping("/findBookByBookPartInfo")
    public String findBooksResultPage(@RequestParam("bookPartInfo") String bookPartInfo, Model model) {
        
        List<BookVo> bookVos = bookService.selectBooksByBookPartInfo(bookPartInfo);

        model.addAttribute("bookList", bookVos);
        return "user/findBook";
    }

    /**
     * Query all book categories
     *
     * @return
     */
    @RequestMapping("/findAllBookCategory")
    @ResponseBody
    public List<BookCategory> findAllBookCategory() {
        return adminService.getBookCategories();
    }

    /*
     * Create new book category
     *
     * @param bookCategory
     * @return
     */
    @RequestMapping("/addBookCategory")
    @ResponseBody
    public String addBookCategory(BookCategory bookCategory) {
        boolean b = adminService.addBookCategory(bookCategory);
        if (b) {
            return "true";
        }
        return "false";
    }

    /*
     * Delete category by book category ID
     *
     * @param bookCategoryId
     * @return
     */
    @RequestMapping("/deleteCategory")
    @ResponseBody
    public String deleteBookCategoryById(@RequestParam("bookCategoryId") int bookCategoryId) {
        int res = bookCategoryService.deleteBookCategoryById(bookCategoryId);
        if (res > 0) {
            return "true";
        }
        return "false";
    }

}
