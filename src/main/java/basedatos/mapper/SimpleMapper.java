package basedatos.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleMapper {
    private final Class targetClass;

    public SimpleMapper(Class target) {
        this.targetClass = target;
    }

    public Object map(Object source, Class sourceClass)
            throws
            InstantiationException,
            IllegalAccessException,
            InvocationTargetException {
        Object target = null;
        target = targetClass.newInstance();
        Method[] sourceMethods = sourceClass.getMethods();
        Method[] targetMethods = targetClass.getMethods();
        for (Method sourceMethod : sourceMethods) {
            if (sourceMethod.getName().startsWith("get")) {
                String targetMethodName = "set" + sourceMethod.getName().substring(3);
                for (Method targetMethod : targetMethods) {
                    if (targetMethod.getName().equals(targetMethodName)) {
                        targetMethod.invoke(target, sourceMethod.invoke(source));
                    }
                }
            }
        }

        return target;
    }

    public Object mapFromResultSet(ResultSet rs)
            throws InstantiationException,
            IllegalAccessException,
            SQLException,
            InvocationTargetException {
        Object target = null;
        target = targetClass.newInstance();
        Method[] targetMethods = targetClass.getMethods();
        for (Method targetMethod : targetMethods) {
            if (targetMethod.getName().startsWith("set")) {
                String columnName = targetMethod.getName().substring(3).toLowerCase();
                if (targetMethod.getParameterTypes()[0] == int.class) {
                    targetMethod.invoke(target, rs.getInt(columnName));
                } else if (targetMethod.getParameterTypes()[0] == String.class) {
                    targetMethod.invoke(target, rs.getString(columnName));
                } else if (targetMethod.getParameterTypes()[0] == float.class) {
                    targetMethod.invoke(target, rs.getFloat(columnName));
                }
            }
        }

        return target;
    }
}
