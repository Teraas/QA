package SystemDesigns;

public class RateLimiting {
    public static void main(String[] agrs){
        TokenBucket ratelimit = new TokenBucket(10, 10);
        ratelimit.allowRequest(5);
        ratelimit.allowRequest(1);
        ratelimit.allowRequest(2);
        ratelimit.allowRequest(5);
    }
}

class TokenBucket{
    private final long bucketMaxSize;
    private final int timeRate;

    long currentBucketSize;
    private long lastCHeckTimestamp;

    public TokenBucket(long bucketMaxSize, int timeRate) {
        this.bucketMaxSize = bucketMaxSize;
        this.timeRate = timeRate;
        currentBucketSize = bucketMaxSize;
        lastCHeckTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest(int tokens){
        refill();
        //Allow requests if tokens available
        if(currentBucketSize >tokens){
            currentBucketSize -=tokens;
            System.out.println("Requests are allowed");
            return true;
        }
        System.out.println("Requests are not allowed");
        return false;
    }

    private void refill() {
        long currentTimestamp = System.currentTimeMillis();
        long tokenAccumulated = (currentTimestamp - lastCHeckTimestamp) * timeRate / 1000;
        currentBucketSize = Math.min(bucketMaxSize, currentBucketSize+tokenAccumulated);
        lastCHeckTimestamp = currentTimestamp;
    }
}
