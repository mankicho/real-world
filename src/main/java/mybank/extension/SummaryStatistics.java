package mybank.extension;

public class SummaryStatistics {
    private final double sum;
    private final double max;
    private final double min;
    private final double avg;

    public SummaryStatistics(double sum, double max, double min, double avg) {
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public double getSum() {
        return sum;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getAvg() {
        return avg;
    }
}
