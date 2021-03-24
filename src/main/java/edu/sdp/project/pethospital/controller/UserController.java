package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/admin/user/getAll")
    ResponseMsg getAllUsers(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<User> users = userService.traverseAllUsers();
        if(users!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",users);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/user/getAdmins")
    ResponseMsg getAdmins(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<User> admins = userService.traverseAllAdmins();
        if(admins!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",admins);
        return msg;
    }

    @ResponseBody
    @GetMapping("/admin/user/getNormal")
    ResponseMsg getNormalUsers(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<User> users = userService.traverseNormalUsers();
        if(users!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",users);
        return msg;
    }

    @ResponseBody
    @GetMapping("/admin/user/getById")
    ResponseMsg getUserById(@RequestParam("userId") int userId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        User user = userService.getUserById(userId);
        if(user!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",user);
        return msg;
    }

    @ResponseBody
    @PutMapping("/admin/user/newUser")
    ResponseMsg addUser(@RequestBody Map map){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!(map.containsKey("account")&&map.containsKey("password")&&map.containsKey("role")))
        return msg;
        String account = map.get("account").toString();
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        String role = map.get("role").toString();
        int result = userService.addUser(account,name,password,role);
        if(result>0) msg.setStatus(200);
        return msg;
    }

    @ResponseBody
    @PostMapping("/admin/user/updateInfo")
    /**
      注意掉这个接口更新用户数据的时候一定要给userId用于验证用户是否存在（userId也应该是永远不变的，不要尝试改变这一属性）
      剩下的属性如果有修改就用key一一对应传过来，没有修改可以不用传
     */
    ResponseMsg updateUser(@RequestBody Map map){
        ResponseMsg msg =new ResponseMsg();
        msg.setStatus(400);
        if(!(map.containsKey("userId")))
            return msg;
        msg.setStatus(404);
        User user = userService.getUserById(Integer.parseInt((map.get("userId").toString())));
        if(user==null) return msg;
        user.updateUser(map);
        userService.changeUserByModel(user);
        msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @DeleteMapping("/admin/user/delete")
    ResponseMsg deleteUserById(@RequestParam("userId") int userId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(userService.deleteUserById(userId)<0) return msg;
        msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/user/chRole")
    ResponseMsg changeRole(@RequestParam("userId") int userId,@RequestParam("role") String role){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(userService.changeUserRoleById(userId,role)<0)
            return msg;
        msg.setStatus(200);
        return msg;
    }

}
