package com.controller;


import com.domain.Department;
import com.domain.User;
import com.domain.Vo.BorrowingBooksVo;
import com.service.IBookService;
import com.service.IBorrowingBooksRecordService;
import com.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private IBorrowingBooksRecordService borrowingBooksRecordService;

    @Resource
    private IBookService bookService;


    // User login
    // @param userName
    // @return
     
    @PostMapping("/userLogin")
    public String userLogin(@Param("userName") String userName,
                            @Param("password") String password, HttpServletRequest request) {
        User user = userService.userLogin(userName, password);
        
        if (null != user) {
        	// flag = 0 indicates username and password verification success (for frontend validation)
            request.getSession().setAttribute("flag", 0);

            request.getSession().setAttribute("user", user);
            return "user/index";
        }

        // flag = 1 indicates login failure (for frontend validation)
        request.getSession().setAttribute("flag", 1);
        return "index";
    }


     // Verify whether the user exists
     // @param userName
     // @return

    @RequestMapping("/isUserExist")
    @ResponseBody
    public String isUserExist(@Param("userName") String userName) {
        List<User> users = userService.findUserByUserName(userName);
        if (null == users) {
            return "false";
        }
        if (users.size() < 1) {
            return "false";
        }
        return "true";
    }

    
     // Find all departments
     
    @RequestMapping("/getDepts")
    @ResponseBody
    public List<Department> getDepts() {
        List<Department> depts = userService.findAllDepts();
        return depts;
    }


    /*
     * Return the user's borrowing records page
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/userBorrowBookRecord")
    public String userBorrowBookRecord(Model model, HttpServletRequest request) {
        ArrayList<BorrowingBooksVo> res = borrowingBooksRecordService.selectAllBorrowRecord(request);
        model.addAttribute("borrowingBooksList", res);

        return "user/borrowingBooksRecord";
    }

     // Return the return book page

    @RequestMapping("/userReturnBooksPage")
    public String userReturnBooksPage() {
        return "user/returnBooks";
    }

    
     // Return the personal information page
     
    @RequestMapping("/userMessagePage")
    public String userMessagePage(Model model, HttpServletRequest request) {
        User session_user = (User) request.getSession().getAttribute("user");
        User user = userService.findUserById(session_user.getUserId());
        model.addAttribute("message_user", user);
        return "user/userMessage";
    }

     // Return the borrow book page
    
    @RequestMapping("/borrowingPage")
    public String borrowing() {
        return "user/borrowingBooks";
    }

     //Return the user homepage
    
    @RequestMapping("/userIndex")
    public String userIndex() {
        return "user/index";
    }

    /*
     * @param user
     * @param request
     * @return
     * @author 
     * Update user information
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public boolean updateUser(User user, HttpServletRequest request) {
        return userService.updateUser(user, request);
    }

    /**
     * User returns book
     *
     * @param bookId
     * @param request
     * @return
     */
    @RequestMapping("/userReturnBook")
    @ResponseBody
    public boolean returnBook(int bookId, HttpServletRequest request) {
        return userService.userReturnBook(bookId, request);
    }

    /*
     * User borrows book
     *
     * @param bookId
     * @param request
     * @return
     */
    @RequestMapping("/userBorrowingBook")
    @ResponseBody
    public boolean borrowingBook(int bookId, HttpServletRequest request) {
        System.out.println(bookId);
        return userService.userBorrowingBook(bookId, request);
    }

    // Return admin login page
    
    @RequestMapping("/adminLoginPage")
    public String adminLoginPage() {
        return "adminLogin";
    }

    /*
     * user logout
     *
     * @param request
     * @return
     */
    @RequestMapping("/userLogOut")
    public String userLogOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    //Return the user book search page
    @RequestMapping("/findBookPage")
    public String findBookPage() {
        return "user/findBook";
    }


    /*
     * Delete user by ID
     *
     * @param userId
     * @return
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUserByUserId(@RequestParam("userId") int userId) {
        int res = userService.deleteUserById(userId);
        if (res > 0) {
            return "true";
        }
        return "false";
    }

    /*
     * Add user
     *
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User user) {
        int res = userService.insertUser(user);
        if (res > 0) {
            return "true";
        } else {
            return "false";
        }
    }
}
