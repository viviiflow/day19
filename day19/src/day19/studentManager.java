package day19;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

class Schoolst {
	String id = "";
	String pw = "";

	void set_data(String i, String p) {
		id = i;
		pw = p;
	}

	void print() {
		System.out.println("�̸�: " + id + "\t��й�ȣ: " + pw);
	}
}

class Manager {	
	Schoolst[] list = null;
	String name = "";
	int cnt = 0;

	int idx(String id) {
		int n = -1;
		for (int i = 0; i < cnt; i++) {
			try {
				if (id.equals(list[i].id)) {
					n = i;
					break;
				}
			} catch (Exception e) {
			}
		}
		return n;
	}

	void sort(int n, Schoolst[] a, Schoolst[] b) {
		for (int j = 0; j < n; j++) {
			a[j] = new Schoolst();
			a[j] = b[j];
		}
	}

	void add(String i, String p) {
		Schoolst[] temp = new Schoolst[cnt + 1];
		if (cnt >= 1) {
			sort(cnt, temp, list);
		}
		temp[cnt] = new Schoolst();
		temp[cnt].set_data(i, p);
		list = new Schoolst[cnt + 1];
		sort(cnt + 1, list, temp);
		temp = null;
		System.out.println(list[cnt].id + "�� ȯ���մϴ�.");
		cnt++;
	}

	void mainsort() {
		while (true) {
			int n = -1, scnt = 0;
			for (int i = 0; i < list.length; i++) {
				if (list[i] == null) {
					n = i;
					scnt++;
				}
			}
			if (n != -1) {
				for (int i = n; i < list.length - 1; i++) {
					list[i] = list[i + 1];
				}
				scnt = list.length - scnt;
				Schoolst[] temp = new Schoolst[scnt];
				sort(scnt, temp, list);
				list = new Schoolst[scnt];
				sort(scnt, list, temp);
			} else {
				System.out.println("���ĵǾ����ϴ�.");
				break;
			}
		}
	}

	void load(Schoolst[] a, int n) {
		cnt = n;
		list = a;
	}
}

public class studentManager {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Manager mg = new Manager();
		boolean run = true;
		mg.name = "�л� ���� ���� �ý���";
		System.out.println("================" + mg.name + "================");
		while (run) {
			System.out.println("[1]���� [2]Ż�� [3]���� [4]��� [5]���� [6]��� [0]����");
			System.out.print("��ȣ�� �������ּ���>>> ");
			int slt = sc.nextInt();
			System.out.println();
			if (slt == 0) {
				System.out.println("���α׷� ����");
				run = false;
			} else if (slt == 1) {
				if ((mg.cnt == 0) || (mg.cnt == mg.list.length)) {
					System.out.print("[����]ID�� �Է����ּ���>>> ");
					String id = sc.next();
					int n = mg.idx(id);
					if (n == -1) {
						System.out.print("[����]PW�� �Է����ּ���>>> ");
						String pw = sc.next();
						mg.add(id, pw);
					} else {
						System.out.println("�̹� ���Ե� �л��Դϴ�.");
					}
				} else {
					System.out.println("���� �� �̿����ֽñ� �ٶ��ϴ�.");
				}
			} else if (slt == 2) {			//������ �޼ҵ� �����
				System.out.print("[Ż��]ID�� �Է����ּ���>>> ");
				String id = sc.next();
				int n = mg.idx(id);
				if (n == -1) {
					System.out.println("�������� �ʴ� �л��Դϴ�.");
				} else {
					mg.list[n] = null;
					mg.cnt--;
					System.out.println(id + "�� Ż��Ǿ����ϴ�.");
				}
			} else if (slt == 3) {
				if(mg.cnt==0) {
					System.out.println("���� ��� �Ұ����� ����Դϴ�.");
				}else {
					mg.mainsort();
				}
			} else if (slt == 4) {
				if ((mg.cnt == 0) || (mg.cnt == mg.list.length)) {
					for (int i = 0; i < mg.cnt; i++) {
						mg.list[i].print();
					}
				} else {
					System.out.println("���� �� �̿����ֽñ� �ٶ��ϴ�.");
				}
			} else if (slt == 5) {
				if ((mg.cnt == 0) || (mg.cnt == mg.list.length)) {
					System.out.println(mg.cnt);
					for (int i = 0; i < mg.cnt; i++) {
						System.out.println(mg.list[i].id + "," + mg.list[i].pw);
					}
				} else {
					System.out.println("���� �� �̿����ֽñ� �ٶ��ϴ�.");
				}
			} else if (slt == 6) {
				try {
					File file = new File("Student_Manager.txt");
					if (file != null) {
						FileReader fr = new FileReader(file);
						BufferedReader br = new BufferedReader(fr);
						String line = br.readLine();
						int cnt = Integer.parseInt(line);
						Schoolst[] temp = new Schoolst[cnt];
						for (int i = 0; i < cnt; i++) {
							temp[i] = new Schoolst();
							line = br.readLine();
							String[] value = line.split(",");
							temp[i].id = value[0];
							temp[i].pw = value[1];
						}
						fr.close();
						br.close();
						mg.load(temp, cnt);
					}
					for (int i = 0; i < mg.cnt; i++) {
						mg.list[i].print();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
