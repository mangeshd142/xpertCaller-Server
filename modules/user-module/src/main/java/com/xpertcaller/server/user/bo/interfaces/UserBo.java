package com.xpertcaller.server.user.bo.interfaces;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.User;

public interface UserBo {
    User updateUser(User user) throws BusinessException;
}
