import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import jOl.*;



public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		int x = Integer.parseInt(line[0]);
		int y = Integer.parseInt(line[1]);
		String[][] secondMap = new String[y][x];


		for (int i = 0; i < y ; i++) {
			String[] inputLine = in.readLine().split(" ");
			for (int j = 0; j < x ; j++) {
				secondMap[i][j]=inputLine[j];
			}
		}
		JackOLantern jOl = new JackOLanternClass(secondMap);
		System.out.println(jOl.getResult());
	}
}
