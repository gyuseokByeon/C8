package ref;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Refelection extends ClassLoader {
	/*
	 * A a1 = new A(1, 2, new B("string 1", 10)); A a2 = new A(3, 4, new
	 * B("string 2", 20)); copyFields(a1, a2);
	 */
	public <T> void copyFields(T from, T to) {
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

	private boolean isPrimitivish(Class c) {
		return c.isPrimitive() || c == String.class || c == Boolean.class || c == Byte.class || c == Short.class
				|| c == Character.class || c == Integer.class || c == Float.class || c == Double.class
				|| c == Long.class;
	}

	public String generateJsonAndToString(Object obj) {

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

	public Refelection() {
		super(Refelection.class.getClassLoader());
	}

	public Refelection(ClassLoader parent) {
		super(parent);
	}

	protected Class<?> findClass(String binaryFileName, String classNameWithPackage) throws ClassNotFoundException {
		try {
			// String path =
			// "/Users/moonti/Documents/coding_study/java/BJP/out/artifacts/test/test.jar";
			// FileInputStream file = new FileInputStream(path);
			FileInputStream file = new FileInputStream(binaryFileName);
			byte[] classByte = new byte[file.available()];
			file.read(classByte);
			file.close();
			return defineClass(classNameWithPackage, classByte, 0, classByte.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new ClassNotFoundException();
		}
		return null;
	}

	public Object invokeClassMethod(String binaryFileName, String classNameWithPackage, String methodName,
			Class<?> args, Object arg) {
		Object res = null;
		try {
			Class clazz = findClass(binaryFileName, classNameWithPackage);

			System.out.println("Loaded class name: " + clazz.getName());

			// Create a new instance from the loaded class
			Constructor constructor = clazz.getConstructor();
			Object obj = constructor.newInstance();

			// Getting the target method from the loaded class and invoke it using its name
			Method method = clazz.getMethod(methodName, args);
			System.out.println("Invoked method name: " + method.getName());
			res = method.invoke(obj, arg);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void invokeClassMethod(String classNameWithPackage, String methodName) {
		try {

			// Create a new JavaClassLoader
			ClassLoader classLoader = this.getClass().getClassLoader();

			// Load the target class using its binary name
			Class clazz = classLoader.loadClass(classNameWithPackage);

			System.out.println("Loaded class name: " + clazz.getName());

			// Create a new instance from the loaded class
			Constructor constructor = clazz.getConstructor();
			Object obj = constructor.newInstance();

			// Getting the target method from the loaded class and invoke it using its name
			Method method = clazz.getMethod(methodName);
			System.out.println("Invoked method name: " + method.getName());
			method.invoke(obj);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
