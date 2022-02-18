package day19;

class Student {
	String name;
	int score;

	void print() {
		System.out.println(name + " : " + score);
	}
}

public class scoreData {
	public static void main(String[] args) {
		String data = "3\n";
		data += "±è¿µÈñ/30\n";
		data += "ÀÌ¸¸¼ö/40\n";
		data += "ÀÌÃ¶¹Î/60\n";
		System.out.println(data);

		String[] ar = data.split("\n");
		int size = Integer.parseInt(ar[0]);
		Student[] st = new Student[size];
		for (int i = 0; i < size; i++) {
			st[i] = new Student();
			String[] dt = ar[i + 1].split("/");
			st[i].name = dt[0];
			st[i].score = Integer.parseInt(dt[1]);
			st[i].print();
		}
		System.out.println();
		int min = 1000, idx = -1;
		for (int i = 0; i < size; i++) {
			if (st[i].score < min) {
				min = st[i].score;
				idx = i;
			}
		}
		for (int i = idx; i < st.length - 1; i++) {
			st[i].name = st[i + 1].name;
			st[i].score = st[i + 1].score;
		}
		size--;
		Student[] temp = new Student[size];
		for (int i = idx; i < size; i++) {
			temp[i] = new Student();
			temp[i].name = st[i].name;
			temp[i].score = st[i].score;
			st[i] = new Student();
			st[i].name = temp[i].name;
			st[i].score = temp[i].score;
			st[i].print();
		}
		st[size] = null;
	}
}
