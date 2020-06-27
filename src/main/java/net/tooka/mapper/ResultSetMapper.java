package net.tooka.mapper;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T>  {

    public T mapResultSetToEntity(ResultSet resultSet , T t) throws IntrospectionException, SQLException, InvocationTargetException, IllegalAccessException;
}
