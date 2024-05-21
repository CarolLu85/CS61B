package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> N = new AList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int tick = 0;
        int nextTarget = 1000;  // 目标大小，初始值为1000
        for (int i = 0; i < 1024000; i += 1) {
            if (N.size() == nextTarget) {
                Stopwatch sw = new Stopwatch();
                Ns.addLast(N.size());
                times.addLast(sw.elapsedTime());
                opCounts.addLast(nextTarget);
                nextTarget *= 2;
                tick += 1;
            }
            N.addLast(i);
        }

        printTimingTable(Ns, times, opCounts);
    }

}
