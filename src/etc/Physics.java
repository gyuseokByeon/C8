package etc;

public class Physics {
	// �ӵ����
	public static double cal_speed(double dist, double time) {
		System.out.print("\n Distance(km) : " + dist);
		System.out.print("\n Time(hr) : " + time);

		return dist / time;
	}

	// ����Ÿ� ���
	public static double cal_dis(double speed, double time) {
		System.out.print("\n Time(hr) : " + time);
		System.out.print("\n Speed(km / hr) : " + speed);

		return speed * time;
	}

	// ���� �ð� ���
	public static double cal_time(double dist, double speed) {
		System.out.print("\n Distance(km) : " + dist);
		System.out.print("\n Speed(km / hr) : " + speed);

		return dist / speed;
	}
}
