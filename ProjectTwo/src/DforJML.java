import static java.lang.Integer.parseInt;
import static java.lang.System.exit;
import static java.util.Arrays.fill;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 
 * @author noMoon Nov 10, 2014 3:18:25 PM
 */
public class DforJML {
    static BufferedReader in = new BufferedReader(new InputStreamReader(
	    System.in));
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static int test;

    static void solve() throws Exception {
	String line = in.readLine();
	String params[] = line.split(" ");
	int n = parseInt(params[0]);
	int a = parseInt(params[1]);
	boolean e[][] = new boolean[n][n];
	for (int i = 0; i < n; i++) {
	    String l = in.readLine();
	    for (int j = 0; j < n; j++) {
		e[i][j] = l.charAt(j) == 'Y';
	    }
	}
	boolean used[] = new boolean[n];
	boolean seen[] = new boolean[n];
	if (dfs(n, e, used, seen, a) != n) {
	    printCase();
	    out.println("IMPOSSIBLE");
	    return;
	}
	printCase();
	int current;
	current: for (current = 0; current < n; current++) {
	    used[current] = true;
	    fill(seen, false);
	    dfs(n, e, used, seen, a);
	    for (int j = 0; j < n; j++) {
		if (!seen[j] && j != current && !e[current][j]) {
		    used[current] = false;
		    continue current;
		}
	    }
	    out.print(current);
	    break;
	}
	if (current >= n) {
	    throw new AssertionError();
	}
	for (int i = 1; i < n; i++) {
	    int next;
	    next: for (next = 0; next < n; next++) {
		if (used[next]) {
		    continue;
		}
		if (e[current][next]) {
		    if (next == a) {
			continue;
		    }
		    used[next] = true;
		    fill(seen, false);
		    dfs(n, e, used, seen, a);
		    for (int j = 0; j < n; j++) {
			if (!seen[j] && !used[j] && !e[current][j]) {
			    used[next] = false;
			    continue next;
			}
		    }
		} else {
		    used[next] = true;
		    fill(seen, false);
		    dfs(n, e, used, seen, a);
		    for (int j = 0; j < n; j++) {
			if (!seen[j] && !used[j] && !e[next][j]) {
			    used[next] = false;
			    continue next;
			}
		    }
		    current = next;
		}
		out.print(" " + next);
		break;
	    }
	    if (next >= n) {
		throw new AssertionError();
	    }
	}
	out.println();

    }

    // @ requires used.length>=n && seen.length>=0;
    // @ ensures \result<=n;
    static int dfs(int n, boolean[][] e, boolean[] used, boolean[] seen, int cur) {
	if (seen[cur] || used[cur]) {
	    return 0;
	}
	seen[cur] = true;
	int ans = 1;
	for (int next = 0; next < n; next++) {
	    if (!used[next] && e[cur][next]) {
		ans += dfs(n, e, used, seen, next);
	    }
	}
	return ans;
    }

    static void printCase() {
	out.print("Case #" + test + ": ");
    }

    static void printlnCase() {
	out.println("Case #" + test + ":");
    }

    public static void main(String[] args) {
	try {
	    int tests = parseInt(in.readLine());
	    // @ assert tests>=1 && tests<=100;
	    for (test = 1; test <= tests; test++) {
		solve();
	    }
	    in.close();
	    out.close();
	} catch (Throwable e) {
	    e.printStackTrace();
	    exit(1);
	}
    }
}
