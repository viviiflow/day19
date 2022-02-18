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
		System.out.println("이름: " + id + "\t비밀번호: " + pw);
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
		System.out.println(list[cnt].id + "님 환영합니다.");
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
				System.out.println("정렬되었습니다.");
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
		mg.name = "학생 정보 관리 시스템";
		System.out.println("================" + mg.name + "================");
		while (run) {
			System.out.println("[1]가입 [2]탈퇴 [3]정렬 [4]출력 [5]저장 [6]노드 [0]종료");
			System.out.print("번호를 선택해주세요>>> ");
			int slt = sc.nextInt();
			System.out.println();
			if (slt == 0) {
				System.out.println("프로그램 종료");
				run = false;
			} else if (slt == 1) {
				if ((mg.cnt == 0) || (mg.cnt == mg.list.length)) {
					System.out.print("[가입]ID를 입력해주세요>>> ");
					String id = sc.next();
					int n = mg.idx(id);
					if (n == -1) {
						System.out.print("[가입]PW를 입력해주세요>>> ");
						String pw = sc.next();
						mg.add(id, pw);
					} else {
						System.out.println("이미 가입된 학생입니다.");
					}
				} else {
					System.out.println("정렬 후 이용해주시길 바랍니다.");
				}
			} else if (slt == 2) {			//삭제도 메소드 만들기
				System.out.print("[탈퇴]ID를 입력해주세요>>> ");
				String id = sc.next();
				int n = mg.idx(id);
				if (n == -1) {
					System.out.println("존재하지 않는 학생입니다.");
				} else {
					mg.list[n] = null;
					mg.cnt--;
					System.out.println(id + "님 탈퇴되었습니다.");
				}
			} else if (slt == 3) {
				if(mg.cnt==0) {
					System.out.println("현재 사용 불가능한 기능입니다.");
				}else {
					mg.mainsort();
				}
			} else if (slt == 4) {
				if ((mg.cnt == 0) || (mg.cnt == mg.list.length)) {
					for (int i = 0; i < mg.cnt; i++) {
						mg.list[i].print();
					}
				} else {
					System.out.println("정렬 후 이용해주시길 바랍니다.");
				}
			} else if (slt == 5) {
				if ((mg.cnt == 0) || (mg.cnt == mg.list.length)) {
					System.out.println(mg.cnt);
					for (int i = 0; i < mg.cnt; i++) {
						System.out.println(mg.list[i].id + "," + mg.list[i].pw);
					}
				} else {
					System.out.println("정렬 후 이용해주시길 바랍니다.");
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
