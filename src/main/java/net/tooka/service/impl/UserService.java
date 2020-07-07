package net.tooka.service.impl;

import net.tooka.dao.GenericDao;
import net.tooka.entity.User;

public class UserService extends GenericServiceImpl<User> {
    
    public UserService(GenericDao<User> genericDao) {
        super(genericDao);
    }


}
