package Lessons.Lesson_7.WebinarCodeSamples;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

public class Lesson_7ReflectionCodeSamples {
    public static void main(String[] args) throws NoSuchMethodException {
        String id = "my bank";
        Account account = new BankAccount(id);
        ((BankAccount) account).setCardId(UUID.randomUUID().toString());
        lookDeeper(account);
        try {
            save(account);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //lambdas();
    }

//    public static void lambdas(Object account) {
//        final Thread thread = new Thread(() -> {
//            try {
//                lookDeeper(account);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    private static void lookDeeper(Object account) throws NoSuchMethodException {
        final Class<?> klass = account.getClass();
        final Method[] methods = klass.getMethods();
        final Method[] declaredMethods = klass.getDeclaredMethods();
//        System.out.println(Modifier.toString(klass.getModifiers()));
//        System.out.println(klass.isInterface() ? "interface" : "class");
//        System.out.println(Arrays.toString(methods));
//        System.out.println(Arrays.toString(declaredMethods));
//        System.out.println(Account.class.getModifiers());
//        System.out.println(BankAccount.class.getModifiers());
        final Field[] declasredFields = klass.getDeclaredFields();
        final int modifiers1 = declasredFields[0].getModifiers();
//        System.out.println(modifiers1);
        final Constructor<?>[] declaredConstructors = klass.getDeclaredConstructors();
        final Constructor<?> constructor = klass.getConstructor();
        try {
            final Account newInstance = (Account) constructor.newInstance();
            final Object invoke = methods[0].invoke(account);
            System.out.println(invoke);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(declaredConstructors));
        final Annotation[] declaredAnnotations = declaredConstructors[0].getDeclaredAnnotations();

    }

    private static void save(Account account) throws IllegalAccessException {
        final Table annotation = account.getClass().getAnnotation(Table.class);
        String  tableName;
        tableName = annotation !=null ? annotation.name() : account.getClass().getSimpleName().toLowerCase();
//        if (annotation != null) tableName = annotation.name();
//        else account.getClass().getSimpleName().toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM "+ tableName + " WHERE ");
        for (Field field: account.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            stringBuilder.append((field.getName() +   " = " + field.get(account)));
        }
        System.out.println(stringBuilder.append(";").toString());
    }
}
