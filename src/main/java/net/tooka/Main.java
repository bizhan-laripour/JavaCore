package net.tooka;

import net.tooka.dao.QueryFactory;
import net.tooka.dao.impl.QueryFactoryImpl;
import net.tooka.entity.User;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class Main {

  public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
      QueryFactory<User> queryFactory = new QueryFactoryImpl<>();
      User user = new User();
      user.setName("bizhan");
      user.setId(1);
      user.setLastName("hlkjsdhf");
      user.setPassword("fdhlsahf");
      user.setUsername("ashfhas");
    System.out.println(queryFactory.findById(user));
  }
}
