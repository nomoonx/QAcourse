import java.util.ArrayList;
import java.util.List;

/**
 * Created by noMoon on 2014-10-09.
 */
public class BinarySearchAssert {

    public static int binarySearch(List<Integer> x, int t) {
        // MustBe(1,N)
        assert (x.contains(t)) : "T does not exist in x";
        int l = 0, u = x.size() - 1;
        // MustBe(L,U)
        assert (x.subList(l, u + 1).contains(t)) : "the first MustBe(L,U) error";
        int p;
        while (true) {
            // MustBe(L,U)
            assert (x.subList(l, u + 1).contains(t)) : "the second MustBe(L,U) error";
            if (l > u) {
                //L>U and MustBe(L,U)
                assert (l > u && x.subList(l, u + 1).contains(t)) : "'L>U and MustBe(L,U)' assert error";
                //T is nowhere in the array
                assert (!x.contains(t)) : "'T is nowhere in the array' assert error";
                p = -1;
                break;
            }
            // MustBe(L,U) and L<=U
            assert (x.subList(l, u + 1).contains(t) && l <= u) : "'MustBe(L,U) and L<=U' assert error";
            int m = (l + u) / 2;
            // MustBe(L,U) and L<=M<=U
            assert (x.subList(l, u + 1).contains(t) && l <= m && m <= u) : "'MustBe(L,U) and L<=M<=U' assert error";
            if (t > x.get(m)) {
                // MustBe(L,U) and CantBe(1,M)
                // MustBe(M+1,U)
                assert (x.subList(l, u + 1).contains(t)) : "'MustBe(L,U)' assert error";
                assert (!x.subList(0, m + 1).contains(t)) : "'CantBe(1,M)' assert error";//I think this should be CantBe(L,M)
                assert (x.subList(m+1, u + 1).contains(t)) : "'MustBe(M+1,U)' assert error";
                l = m + 1;
                // MustBe(L,U)
                assert (x.subList(l, u + 1).contains(t)) : "'MustBe(L,U)' assert error";
            } else if (t == x.get(m)) {
                //X[M] = T
                assert(t==x.get(m)):"'X[M] = T' assert error";
                p = m;
                break;
            } else if (t < x.get(m)) {
                // MustBe(L,U) and CantBe(M,N)
                // MustBe(L,M-1)
                assert (x.subList(l, u + 1).contains(t)) : "'MustBe(L,U)' assert error";
                assert (!x.subList(m, x.size()).contains(t)) : "'CantBe(M,N)' assert error";//I think this should be CantBe(M,U)
                assert (x.subList(l, m).contains(t)) : "'MustBe(L,M-1)' assert error";
                u = m - 1;
                // MustBe(L,U)
                assert (x.subList(l, u + 1).contains(t)) : "'MustBe(L,U)' assert error";
            }
            // MustBe(L,U)
            assert (x.subList(l, u + 1).contains(t)) : "'MustBe(L,U)' assert error";
        }
        return p;


    }

    public static void main(String[] args) {
        System.out.println("let's test binary search");
        List<Integer> testList=new ArrayList<Integer>();
        //test case one
        testList.add(0);//testList should be {0}
        int result=binarySearch(testList,0);
        assert(0==result):"test case one fails";

        //test case two
        testList.add(1);//testList should be {0,1}
        result=binarySearch(testList,0);
        assert(0==result):"test case two fails";
        result=binarySearch(testList,1);
        assert(1==result):"test case two fails";

        for (int i=2;i<10;i++){
            testList.add(i);
            int t=i/4;
            result=binarySearch(testList,t);
            assert(result==t):"test case "+String.valueOf(i+1)+" fails";
            t=i*3/4;
            result=binarySearch(testList,t);
            assert(result==t):"test case "+String.valueOf(i+1)+" fails";
        }
        System.out.println("If you see this, it seems everything goes ok :D");


    }
}
