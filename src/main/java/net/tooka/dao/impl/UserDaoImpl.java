package net.tooka.dao.impl;

import net.tooka.dao.QueryFactory;
import net.tooka.entity.User;
import net.tooka.mapper.ResultSetMapper;

public class UserDaoImpl extends GenericDaoImpl<User> {


    public UserDaoImpl(QueryFactory<User> queryFactory, ResultSetMapper<User> resultSetMapper) {
        super(queryFactory, resultSetMapper);
    }
}
