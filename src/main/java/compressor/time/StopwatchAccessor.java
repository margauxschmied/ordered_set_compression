package compressor.time;

public class StopwatchAccessor {
    protected long start = -1;
    protected long cumul=0;
    protected long numCumuls=0;
    protected long last=0;
    public final long readElapsedNanoSeconds(){ return System.nanoTime()-start; }
    public final long lastElapsedNanoSeconds() { return last; }
    public final long lastElapsedMilliSeconds() { return (last)/1000000; }
    public final long elapsedNanoSeconds() { return cumul; }
    public final long elapsedMilliSeconds() { return (cumul)/1000000; }
    public final double millionOperationPerSecond(long numOp) { return (numOp / (double)(elapsedNanoSeconds())) * 1000.; }
    public final long numberOfAccumulations(){return numCumuls;}
    static public long computeGranularityNanoSeconds(){
        long cur;
        final long last=System.nanoTime();
        do {
            cur = System.nanoTime();
        } while (cur == last);
        return cur-last;
    }
}