package etc;

public class Formatter {

	//ex String number = String.format("%03d", i);
	//ex 10���ڸ��� 0 ä���: %03d, �Ҽ��� �ڸ�:"%.2f"
	public static String getFormatString(String format,int i)
	{
		String number = String.format(format, i);
		return number;
	}
}
