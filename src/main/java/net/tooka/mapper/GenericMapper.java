package net.tooka.mapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class GenericMapper<S, D> {

  public S map(S s, D d)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException {
    Class<?> objectClass = d.getClass();
    for (Field field : objectClass.getDeclaredFields()) {

      Object objField =
          new PropertyDescriptor(field.getName(), s.getClass()).getReadMethod().invoke(s);

      new PropertyDescriptor(field.getName(), d.getClass()).getWriteMethod().invoke(d, objField);
    }

    return s;
  }
}
