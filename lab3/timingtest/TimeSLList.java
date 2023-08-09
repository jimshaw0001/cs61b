package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        final int START_VAL = 1_000;
        final int MAX_VAL = 64_000; //10_000_000;
        final int OPS = 10000; //1_000;

        int i = START_VAL;
        while (i <= MAX_VAL){
            SLList<Integer> testList = new SLList<>();
            for (int l = 0; l < i; l++) {
                testList.addLast(l);
            }

            Stopwatch sw = new Stopwatch();
            for (int op = 0; op < OPS; op++) {
                testList.getLast();
            }
            double timeInSeconds = sw.elapsedTime();

            Ns.addLast(i);
            opCounts.addLast(OPS);
            times.addLast(timeInSeconds);
            i *= 2;
        }
        printTimingTable(Ns, times, opCounts);
    }

}
