package morganStanley_Day5;

public class ThreadEx {

	public static void main(String[] args) {
	
		// Q1. Create and start a Thread using a Runnable. The thread should print numbers 1 to 5 with a 1-second delay.
		System.out.println("Create and start a Thread using a Runnable. The thread should print numbers 1 to 5 with a 1-second delay");
		Runnable runnable = ()->{
			for(int i=1; i<=5; i++) {
				System.out.println("Number:"+i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		System.out.println("----------------------------------------");
		
		// Q2. Start a thread that prints the current thread name 5 times using Lambda Expression.
		System.out.println("Start a thread that prints the current thread name 5 times using Lambda Expression");
		Thread t = new Thread(()->{
			for(int i=1; i<=5; i++) {
				System.out.println("Thread name:" + Thread.currentThread().getName());
			}
		});
		t.start();
		try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		System.out.println("----------------------------------------");
		
		// Q3. Create 3 threads to simulate file download tasks. Use join() to wait for all downloads to complete before printing "All files downloaded".
		System.out.println("Create 3 threads to simulate file download tasks. Use join() to wait for all downloads to complete before printing \"All files downloaded");
		Runnable filedownload = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " started downloading...");
            try {
                Thread.sleep(10000); // simulate 2 sec download time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " finished downloading.");
        };
        
        Thread t1 = new Thread(filedownload, "File-1");
        Thread t2 = new Thread(filedownload, "File-2");
        Thread t3 = new Thread(filedownload, "File-3");
        
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All files downloaded.");
		System.out.println("----------------------------------------");
	}

}
