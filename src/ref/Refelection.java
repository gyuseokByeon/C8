package ref;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Refelection {
	/*
	  A a1 = new A(1, 2, new B("string 1", 10));
	  A a2 = new A(3, 4, new B("string 2", 20));
	  copyFields(a1, a2);
	*/	
	public static <T> void copyFields(T from, T to) {
		for (Field f : from.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (isPrimitivish(f.getType())) {
					f.set(to, f.get(from));
				} else {
					copyFields(f.get(from), f.get(to));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean isPrimitivish(Class c) {
		return c.isPrimitive() || c == String.class || c == Boolean.class
				|| c == Byte.class || c == Short.class || c == Character.class
				|| c == Integer.class || c == Float.class || c == Double.class
				|| c == Long.class;
	}

	public static String generateJsonAndToString(Object obj) {

		char[] methodNameArr = null;

		String methodName = null;

		Method method = null;

		for (Field field : obj.getClass().getDeclaredFields()) {
			// reflection�� �̿��ؼ� ��������� �̸��� ���Ѵ�.
			// field.setAccessible(true) �ϸ� �ʵ�� �ʵ�� �����ü� ����.
			// field.get(obj); //obj�ȿ� �ʵ���� ���� ������
			// field.getName() //�ʵ��
			// field.set(newObj,field.get(obj)) �� object���� new object�� �� ����
			try {
				methodNameArr = field.getName().toCharArray();
				// �޼ҵ� �̸��� ������� �̸��� ù ���ĺ��� �빮�ڷ� �ٲ� ���̶�� �����ϰ� �ʵ��̸��� ���ؼ� �־��ش�.
				methodNameArr[0] = (char) ((methodNameArr[0] - 'a') + 'A');
				// ù ���ĺ��� �빮�ڷ� �ٲ��ش�.
				methodName = "get" + new String(methodNameArr);
				// �տ� get�� �߰��ؼ� getMethod�� �ǵ��� �Ѵ�.
				try {
					method = obj.getClass().getMethod(methodName);
					// �޼ҵ� ������Ʈ�� reflection�� �̿��ؼ� �޼ҵ尴ü�� ��´�.
					// json.put(field.getName(), method.invoke(obj));
					// �޼ҵ� ����
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}

		return "aaa";
	}

}
