import java.util.Random;
import java.util.concurrent.*;

public class Homework1 {
    public static void main(String[] args) {
        double inTheCircle = 0;
        int total = 1000;
        if(args.length != 0) {total = Integer.parseInt(args[0]);}

        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<Integer> result = pool.submit(new Task(total));
        try {
            inTheCircle = result.get();
        } catch(InterruptedException | ExecutionException ie){}
        pool.shutdown();

        System.out.println(4*inTheCircle/total);
        
    }
}

class Task implements Callable<Integer> {
    private int iterations;
    public Task(int iterations){
        this.iterations = iterations;
    }

    public Integer call() {
        int inCircle = 0;
        double x ,y;
        Random rand = new Random();
        for(int i=0;i<iterations;i++) {
            x = rand.nextDouble(2) - 1;
            y = rand.nextDouble(2) - 1;
            
            if (x*x + y*y <= 1) {
                inCircle++;
            }
        }
        return inCircle;
    }
}



