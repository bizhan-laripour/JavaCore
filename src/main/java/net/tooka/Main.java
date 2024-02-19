package net.tooka;

import net.tooka.annotationprocessor.AnnotationProcessor;
import net.tooka.dao.impl.QueryFactoryImpl;
import net.tooka.dao.impl.UserDaoImpl;
import net.tooka.entity.User;
import net.tooka.mapper.ResultSetMapperImpl;
import net.tooka.service.impl.UserService;

import javax.annotation.processing.AbstractProcessor;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class Main {

  public static void main(String[] args)
      throws IllegalAccessException, IntrospectionException, InvocationTargetException,
          SQLException {

//    AbstractProcessor processor = new AnnotationProcessor();
//    UserService userService =
//        new UserService(new UserDaoImpl(new QueryFactoryImpl<>(), new ResultSetMapperImpl<>()));
//    //
//    List<User> users = userService.findAll(new User());

    //      User user = new User();
    //      user.setName("babak");
    //      user.setLastName("laripour");
    //      user.setUsername("babak.lp");
    //      user.setPassword("password");
    //      userService.save(user);

  }
}
