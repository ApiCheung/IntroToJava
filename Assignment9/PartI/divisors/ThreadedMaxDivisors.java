package Assignment9.PartI.divisors;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ThreadedMaxDivisors implements Runnable {
	
	private long min;
	private long max;
	private Entry<Long, Long> res;
	
	public ThreadedMaxDivisors(long min, long max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public void run() {
		CountDivisors count = new CountDivisors();
		res = count.maxDivisors(min, max);
		try{
			Thread.sleep((long)(Math.random()*10));
		}catch(InterruptedException e){
			e.printStackTrace();
		}

	}
	

	public static void main(String[] args) {
		
		long min = 100_000;
		long max = 200_000;
		long GPU = Runtime.getRuntime().availableProcessors();;
		long maxDivisor = 0;
		long result = 0;
		long temp = (max-min+1)/GPU;
		Set<Thread> threadSet = new HashSet<Thread>();
		Set<ThreadedMaxDivisors> divisorsSet = new HashSet<ThreadedMaxDivisors>();
		long startTime = System.currentTimeMillis();

		for(int i = 0; i < GPU; i++){
			ThreadedMaxDivisors t1 = new ThreadedMaxDivisors(
					min + temp * i, min + temp * (i + 1));
			Thread thread = new Thread(t1);
			threadSet.add(thread);

		}
		

		ThreadedMaxDivisors t2 = new ThreadedMaxDivisors(min + temp * GPU, max);
		Thread thread = new Thread(t2);
		threadSet.add(thread);
		divisorsSet.add(t2);

		for(Thread t : threadSet){
			t.start();
		}

		/* join() tells a thread to wait until it's complete before the rest of the code and proceed.
		 * if we do that for all the threads, then then we can get the results of the threads once
		 * all of them are done
		 */
		for (Thread t: threadSet) {
			try {
				t.join();
				System.out.print("Done");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// at this point, all threads have been completed, since we
		// called the "join()" method on all the threads we created,
		// which forces the code to wait until the thread is finished
		// before we continue
		
		for (ThreadedMaxDivisors tmd : divisorsSet) {
			// presumably you've recorded the results of
			// each ThreadedMaxDivisors run. Pick
			// the largest number with the largest number of
			// divisors

			if(tmd.res.getValue() >= result){
				if(tmd.res.getKey() >= maxDivisor){
					maxDivisor = tmd.res.getKey();
					result = tmd.res.getValue();
				}
			}
			
		}
		System.out.println(result + " " + maxDivisor);
		
		long endTime = System.currentTimeMillis();
		System.out.println("Threaded elapsed time: " + (endTime - startTime));
		startTime = System.currentTimeMillis();
		Entry<Long,Long> e = CountDivisors.maxDivisors(min, max);
		
		long number = e.getKey();
		long numDivisors = e.getValue();
		
		System.out.println("\n" + number + ": " + numDivisors);
		endTime = System.currentTimeMillis();
		
		System.out.println("Non-threaded elapsed time: " + (endTime - startTime));
		
		
		
		
	}
}
