import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.util.*;

public class LCalc {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		LoveCalc solver = new LoveCalc();
		solver.solve(in, out);
		out.close();
	}
}

class LoveCalc {
	public void solve(InputReader in, PrintWriter out) {
        int numTests = in.nextInt();
        
		while(numTests-- != 0) {
			String lcString = in.next();
			StringBuffer lcBuffer = new StringBuffer(lcString);
			int len = lcString.length();
			char[] lcCharArray = new char[len];
			lcCharArray = lcString.toCharArray();
			for(int init = 0, init2 = 1; init < lcBuffer.length(); init++, init2++) {
				String sTemp = lcBuffer.substring(init, init2);
				for(int firstIndex = lcBuffer.indexOf(sTemp), lastIndex = lcBuffer.lastIndexOf(sTemp); firstIndex < lastIndex; ) {
					lcBuffer.deleteCharAt(lcBuffer.lastIndexOf(sTemp));
					lastIndex = lcBuffer.lastIndexOf(sTemp);
				}
			}	
			String lcBuffString = new String(lcBuffer);
			int lcBuff = lcBuffString.length();
			char[] lcbCharArray = new char[lcBuff];
			lcbCharArray = lcBuffString.toCharArray();
			int[] lcInt = new int[lcBuff];
			int count = 0;
			for(int start = 0; start < lcBuff; start++) {
				for(int start2 = 0; start2 < len; start2++) {
					if(lcbCharArray[start] == lcCharArray[start2]) {
						count++;
					}
					lcInt[start] = count;
				}
				count = 0;
			}
			ArrayList<Integer> lc = new ArrayList<Integer>();
			for(int ind = 0; ind < lcBuff; ind++) {
				lc.add(lcInt[ind]);
			}
			while(lc.size() != 2) {
				for(int begin = 0, end = lc.size() - 1; begin < end; begin++, end--) {
					lc.set(begin, lc.get(begin) + lc.get(end));
				}
				int counter = lc.size() / 2;
				while(counter-- > 0) {
					lc.remove(lc.size() - 1);
				}
				for(int x = 0; x < lc.size(); x++) {
					if(lc.get(x) >= 10) {
						int put = lc.get(x) - 10;
						lc.set(x, 1);
						lc.add(x, put);
					}
				}
			}
			int one = lc.get(0);
			int two = lc.get(1);
			out.println(one+""+two+"%");
		}
	}	
}

class InputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
		return Integer.parseInt(next());
	}
}