package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.mapper.UserMapper;
import edu.sdp.project.pethospital.utils.Result;
import edu.sdp.project.pethospital.utils.ResultCode;
import edu.sdp.project.pethospital.utils.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result selectByID(Integer ID) {
        User user = userMapper.selectByPrimaryKey(ID);
        if (user==null){
            return ResultFactory.buildFailResult("未找到该用户,userID = " + ID);
        }
        else{
            return ResultFactory.buildSuccessResult(user);
        }
    }

    @Override
    public Result selectByAccount(String userAccount) {
        return null;
    }

    @Override
    public Result selectAllUser() {
        List<User> userList = userMapper.selectAllUser();
        if (userList.isEmpty()){
            return ResultFactory.buildFailResult("不存在用户");
        }
        else{
            return ResultFactory.buildSuccessResult(userList);
        }
    }

    @Override
    public Result deleteByID(Integer ID) {
        User user = userMapper.selectByPrimaryKey(ID);
        if (user==null){
            return ResultFactory.buildFailResult("未找到该用户,userID = " + ID);
        }
        else{
            userMapper.deleteByPrimaryKey(ID);
            return ResultFactory.buildSuccessResult(null);
        }
    }

    @Override
    public Result update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        int ID = user.getUserId();
        User newUser = userMapper.selectByPrimaryKey(ID);
        if (newUser==null){
            return ResultFactory.buildFailResult("修改用户失败");
        }
        else{
            return ResultFactory.buildSuccessResult(newUser);
        }
    }

    @Override
    public Result login(String userAccount, String password) {
        User curUser = userMapper.selectByAccount(userAccount);
        String curPassWord;
        if(curUser==null){
            return ResultFactory.buildFailResult("不存在该用户");
        }
        else{
            curPassWord = curUser.getUserPassword();
            if (curPassWord.equals(password)){
                return ResultFactory.buildResult(ResultCode.SUCCESS,"登录成功",curUser);
            }
            else{
                return ResultFactory.buildFailResult("密码错误");
            }
        }
    }

    @Override
    public Result register(User user) {
        User existUser = userMapper.selectByAccount(user.getUserAccount());
        if(existUser==null){
            userMapper.insert(user);
            existUser = userMapper.selectByAccount(user.getUserAccount());
            return ResultFactory.buildSuccessResult(existUser);
        }
        else {
            return ResultFactory.buildFailResult("该用户已存在");
        }
    }
}
