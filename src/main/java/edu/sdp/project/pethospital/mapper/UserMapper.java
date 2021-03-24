package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Delete("delete from user where userId = #{userId}")
    int deleteByUserId(Integer userId);

    @Options(useGeneratedKeys = true,keyProperty = "userId")
    @Insert("insert into user (account, name password, role) values (#{account}, #{name}, #{password}, #{role})")
    int insert(User user);

    @Update("update user set userId=#{userId}"+
            ", password=#{password}" +
            ", name=#{name}" +
            ", account=#{account}" +
            ", role=#{role}" +
            "where userId=#{userId}")
    int updateUser(User user);

    @Select("select * from user where account")
    User selectByAccount(String account);

    @Select("select * from user where userId=#{userId}")
    User selectByUserId(Integer userId);
    @Select("select * from user")
    List<User> selectAllUsers();
    @Select("select * from user where role='admin'")
    List<User> selectAllAdmins();
    @Select("select * from user where role='user'")
    List<User> selectAllNormalUsers();
    @Update("update user set role=#{role} where userId=#{userId}")
    int updateRoleById(int userId,String role);
}