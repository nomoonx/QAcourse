import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * class RandomGeneratoerForD.java：TODO
 * 
 * @author noMoon Nov 11, 2014 2:12:21 AM
 */
public class RandomGeneratoerForD {
    public static void main(String[] args) throws Exception {
	randomGenerateSmallInputFile();
	if (compare()) {
	    System.out
		    .println("In small input: solution is the same as oracle");
	} else {
	    System.out.println("In small input: solution failed the test");
	}
	randomGenerateLargeInputFile();
	if (compare()) {
	    System.out
		    .println("In large input: solution is the same as oracle");
	} else {
	    System.out.println("In large input: solution failed the test");
	}

    }

    private static void randomGenerateLargeInputFile() throws IOException {
	File outputFile = new File("randomInput");
	BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
	int numberOfTestCases = 100;
	bw.write(String.valueOf(numberOfTestCases));
	bw.newLine();
	int map[][] = new int[100][100];
	// generate the minimun case
	int n = 1, a = 0;
	map[0][0] = -1;
	writeTheCase(bw, n, a, map);
	Date today = new Date();
	Random random = new Random(today.getTime());
	for (int i = 1; i < numberOfTestCases; i++) {
	    n = random.nextInt(99) + 2;
	    if (i == numberOfTestCases)
		n = 100;
	    a = random.nextInt(n);
	    for (int j = 0; j < n; j++) {
		Arrays.fill(map[j], -1);
	    }
	    for (int j = 0; j < n; j++) {
		for (int k = j + 1; k < n; k++) {
		    int flag;
		    if (random.nextInt(100) > 50)
			flag = 1;
		    else
			flag = 0;

		    map[j][k] = flag;
		    map[k][j] = 1 - flag;
		}
	    }
	    writeTheCase(bw, n, a, map);
	}
	bw.close();
    }

    private static void randomGenerateSmallInputFile() throws IOException {
	File outputFile = new File("randomInput");
	BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
	int numberOfTestCases = 10;
	bw.write(String.valueOf(numberOfTestCases));
	bw.newLine();
	int map[][] = new int[10][10];
	// generate the minimun case
	int n = 1, a = 0;
	map[0][0] = -1;
	writeTheCase(bw, n, a, map);
	Date today = new Date();
	Random random = new Random(today.getTime());
	for (int i = 1; i < numberOfTestCases; i++) {
	    n = random.nextInt(9) + 2;
	    if (i == numberOfTestCases)
		n = 10;
	    a = random.nextInt(n);
	    for (int j = 0; j < n; j++) {
		Arrays.fill(map[j], -1);
	    }
	    for (int j = 0; j < n; j++) {
		for (int k = j + 1; k < n; k++) {
		    int flag;
		    if (random.nextInt(100) > 50)
			flag = 1;
		    else
			flag = 0;

		    map[j][k] = flag;
		    map[k][j] = 1 - flag;
		}
	    }
	    writeTheCase(bw, n, a, map);
	}
	bw.close();
    }

    private static void writeTheCase(BufferedWriter bw, int n, int a,
	    int[][] map) throws IOException {
	bw.write(String.valueOf(n));
	bw.write(' ');
	bw.write(String.valueOf(a));
	bw.newLine();
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (map[i][j] == 1) {
		    bw.write('Y');
		} else if (map[i][j] == 0) {
		    bw.write('N');
		} else if (map[i][j] == -1) {
		    bw.write('-');
		}
	    }
	    bw.newLine();
	}
	bw.newLine();
    }

    private static void runProcess(String command) throws Exception {
	Process pro = Runtime.getRuntime().exec(command);
	printLines(command + " stdout:", pro.getInputStream());
	printLines(command + " stderr:", pro.getErrorStream());
	pro.waitFor();
	System.out.println(command + " exitValue() " + pro.exitValue());
    }

    private static void printLines(String name, InputStream ins)
	    throws Exception {
	String line = null;
	BufferedReader in = new BufferedReader(new InputStreamReader(ins));
	while ((line = in.readLine()) != null) {
	    System.out.println(name + " " + line);
	}
    }

    public static boolean compare() throws Exception {
	try {
	    runProcess("javac Main.java");
	    runProcess("java Main");
	    runProcess("javac DforRandom.java");
	    runProcess("java DforRandom");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	File outputFileForD = new File("DOutput");

	BufferedReader brForD = new BufferedReader(new InputStreamReader(
		new FileInputStream(outputFileForD)));

	File outputFileForOracle = new File("OracleOutput");

	BufferedReader brForOracle = new BufferedReader(new InputStreamReader(
		new FileInputStream(outputFileForD)));
	String lineD = brForD.readLine();
	String lineOracle = brForOracle.readLine();
	int i = 1;
	boolean flag = true;
	while (lineD != null && lineOracle != null) {
	    if (lineD.trim().compareTo(lineOracle.trim()) != 0) {
		flag = false;
		System.out.println("line " + String.valueOf(i) + " of D:      "
			+ lineD);
		System.out.println("line " + String.valueOf(i) + " of Oracle: "
			+ lineOracle);
	    }
	    lineD = brForD.readLine();
	    lineOracle = brForOracle.readLine();
	    i++;
	}

	return flag;

    }
}
