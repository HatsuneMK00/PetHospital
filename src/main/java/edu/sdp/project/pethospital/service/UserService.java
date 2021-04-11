package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.mapper.UserMapper;
import jdk.dynalink.linker.LinkerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public User getUserByAccount(String account){
        return userMapper.selectByAccount(account);
    }
    public User getUserById(int userId){
        return userMapper.selectByUserId(userId);
    }
    public List<User> traverseAllUsers(){
        return userMapper.selectAllUsers();
    }
    public  List<User> traverseAllAdmins(){
        return userMapper.selectAllAdmins();
    }
    public List<User> traverseNormalUsers(){
        return userMapper.selectAllNormalUsers();
    }
    public int changeUserRoleById(int userId,String role){
        return userMapper.updateRoleById(userId,role);
    }
    public int deleteUserById(int userId){
        return userMapper.deleteByUserId(userId);
    }
    public int changeUserByModel(User user){
        return userMapper.updateUser(user);
    }
    public int addUser(String account,String name,String password,String role){
        User user = new User();
        user.setAccount(account);
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);
        User exist = userMapper.selectByAccount(account);
        if(exist!=null) return 0;
        int result = userMapper.insert(user);
        if(result>0) return user.getuserId();
        return result;
    }
    public boolean loginCheck(String account,String password){
        User user = userMapper.selectByAccount(account);
        if(user==null) return false;
        return password.equals(user.getPassword());
    }
}
