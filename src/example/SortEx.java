package example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SortEx {
	class Student {
		int rollno;
		String name, address;

		// Constructor
		public Student(int rollno, String name, String address) {
			this.rollno = rollno;
			this.name = name;
			this.address = address;
		}

		// Used to print student details in main()
		public String toString() {
			return this.rollno + " " + this.name + " " + this.address;
		}
	}

	class Sortbyroll implements Comparator<Student> {
		// Used for sorting in ascending order of
		// roll number
		public int compare(Student a, Student b) {
			return a.rollno - b.rollno;
		}
	}

	
	// Map 선언
//			Map<Integer, String> testMap = new HashMap<Integer, String>();
//
//			// Map에 데이터 추가
//			testMap.put( 1, "apple");
//			testMap.put( 4, "pineapple");
//			testMap.put( 2, "orange");
//			testMap.put( 5, "strawberry");
//			testMap.put( 3, "melon");
//
//			// 키로 정렬
//			Object[] mapkey = testMap.keySet().toArray();
//			Arrays.sort(mapkey);
//
//			// 결과 출력
//			for (Integer nKey : testMap.keySet())
//			{
//				System.out.println(testMap.get(nKey));
//			}
//			
//			
	// 별도의 스태틱 함수로 구현
//	public static List sortByValue(final Map map) {
//		List<String> list = new ArrayList();
//		list.addAll(map.keySet());
//		Collections.sort(list, new Comparator() {
//			public int compare(Object o1, Object o2) {
//				Object v1 = map.get(o1);
//				Object v2 = map.get(o2);
//				return ((Comparable) v2).compareTo(v1);
//			}
//		});
//		Collections.reverse(list); // 주석시 오름차순
//		return list;
//	}
	//  Map<String, Bus> b = busInfos.get(timeInfo);
	//	Map<String, Bus> sortedMap  = Sort.<String,Bus>sortMap(b);

}