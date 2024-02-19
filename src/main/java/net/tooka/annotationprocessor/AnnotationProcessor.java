package net.tooka.annotationprocessor;

import com.google.auto.service.AutoService;
import net.tooka.config.DbConfig;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;
import java.util.Set;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
//@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {


    private static Properties properties = new Properties();
    private static Connection connection;

    static {
        try {

            InputStream inputStream =
                    DbConfig.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);
//            Class.forName(properties.getProperty("database.driver"));
//            connection =
//                    DriverManager.getConnection(
//                            properties.getProperty("database.url"),
//                            properties.getProperty("database.user"),
//                            properties.getProperty("database.password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        String hbmToDDL = properties.getProperty("hbm.to.ddl");
        if (hbmToDDL.equals("create")) {
            for (TypeElement annotation : annotations) {
                Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(annotation);
                annotatedElements.forEach(obj -> {
                    String className = obj.getSimpleName().toString();
                    Field[] fields = obj.getClass().getDeclaredFields();
                    System.out.println(createQuery(className, fields));
                });
            }
        }
        return true;
    }


    public String createQuery(String className, Field[] fields) {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ").append(className).append(" (");
        for (int j = 0; j < fields.length; j++) {
            if (fields[j].getType().getSimpleName().equalsIgnoreCase("long")) {
                builder.append(fields[j]).append(" BIGINT(10) ,");
            } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("int")) {
                builder.append(fields[j]).append(" INT(5) ,");
            } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("Date")) {
                builder.append(fields[j]).append(" DATE ,");
            } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("String")) {
                builder.append(fields[j]).append(" VARCHAR(100) ,");
            } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("timestamp")) {
                builder.append(fields[j]).append(" TIMESTAMP ,");
            } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("time")) {
                builder.append(fields[j]).append(" TIMESTAMP ,");
            }
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append(")");
        return builder.toString();
    }
}
