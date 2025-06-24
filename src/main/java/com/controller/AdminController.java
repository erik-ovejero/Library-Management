package com.controller;

import com.domain.Admin;
import com.domain.BookCategory;
import com.domain.User;
import com.domain.Vo.BookVo;
import com.service.IAdminService;
import com.service.IBookCategoryService;
import com.service.IUserService;
import com.utils.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Resource
    private IAdminService adminService;
    @Resource
    private IBookCategoryService bookCategoryService;
    @Resource
    private IUserService userService;

    /**
     * Determines whether the admin exists
     *
     * @param adminName
     * @return
     */
    @RequestMapping("/isAdminExist")
    @ResponseBody
    public String adminIsExist(@Param("adminName") String adminName) {
        boolean b = adminService.adminIsExist(adminName);
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }

    /*
     * Admin Login
     *
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("/adminLogin")
    public String adminLogin(@Param("userName") String userName, @Param("password") String password, HttpServletRequest request) {
        Admin admin = adminService.adminLogin(userName, password);

        if (admin == null) {
            // Flag = 1 indicates login failure
            request.getSession().setAttribute("flag", 1);
            return "index";
        }

        // flag = 0 indicates username and password verification succeeded
        request.getSession().setAttribute("flag", 0);
        request.getSession().setAttribute("admin", admin);
        return "admin/index";
    }

    /*
     * Return the add book page
     */
    @RequestMapping("/addBookPage")
    public String addBookPage() {
        return "admin/addBook";
    }

    /*
     * Return the add category page
     */
    @RequestMapping("/addCategoryPage")
    public String addCategoryPage(@RequestParam("pageNum") int pageNum, Model model) {
        Page<BookCategory> page = bookCategoryService.selectBookCategoryByPageNum(pageNum);
        model.addAttribute("page", page);
        return "admin/addCategory";
    }

    /**
     * Return the query status page
     */
    @RequestMapping("/showStausPage")
    public String showStatusPage() {
        return "admin/showStaus";
    }

    /*
     * Return the admin homepage
     */
    @RequestMapping("/adminIndex")
    public String returnAdminIndexPage() {
        return "admin/index";
    }


    /*
     * Return the user query page 
     */
    @RequestMapping("/showUsersPage")
    public String showUsersPage(Model model, @RequestParam("pageNum") int pageNum) {
        Page<User> page = userService.findUserByPage(pageNum);
        model.addAttribute("page", page);
        return "admin/showUsers";
    }

    /*
     * Return the  book query page
     */
    @RequestMapping("/showBooksPage")
    public String showBooksPage(Model model) {
        Page<BookVo> page = new Page<BookVo>();
        page.setPageCount(1);
        page.setPageNum(1);
        model.addAttribute("page", page);
        return "admin/showBooks";
    }


    /*
     * Admin logout
     *
     * @param request
     * @return
     */
    @RequestMapping("/adminLogOut")
    public String userLogOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    /*
     * Return the add new user page
     */
    @RequestMapping("/addUserPage")
    public String addUserPage() {
        return "admin/addUser";
    }

    @RequestMapping("/adminInfoPage")
    public String adminInfo() {
        return "admin/adminInfo";
    }
    
    /*
     * Update admin information
     *
     * @param admin
     * @param request
     * @return
     */
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public boolean updateAdmin(Admin admin, HttpServletRequest request) {
        return adminService.updateAdmin(admin, request);
    }

}
