package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.service.UserServiceImpl;
import edu.sdp.project.pethospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Result getUser()
    {
        return userService.selectAllUser();
    }
    /**
     * 根据用户名userName返回用户信息
     */
    @GetMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public Result getUserByUserName(@PathVariable("userID") Integer userID) {
        return userService.selectByID(userID);
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public Result registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 用户登录
     * @param telephone
     * @param password
     * @return
     */

    @PostMapping("/login")
    public Result loginUser(String telephone, String password, HttpSession session){
        return userService.login(telephone,password);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteUser(@PathVariable("userID") Integer ID){
        return userService.deleteByID(ID);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Result updateUser(@RequestBody User user){
        return userService.update(user);
    }
}
