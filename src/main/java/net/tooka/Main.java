package net.tooka;

import net.tooka.dao.QueryFactory;
import net.tooka.dto.UserDto;
import net.tooka.entity.User;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class Main {

  public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
      QueryFactory<User> queryFactory = new QueryFactory<>();
    System.out.println(queryFactory.save(new User()));
  }
}
