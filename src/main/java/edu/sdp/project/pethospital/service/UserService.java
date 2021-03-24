package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.utils.Result;

public interface UserService {

    Result selectByID(Integer ID);

    Result selectByAccount(String userAccount);

    Result selectAllUser();

    Result deleteByID(Integer ID);

    Result update(User user);

    Result login(String userAccount, String password);

    Result register(User user);
}
