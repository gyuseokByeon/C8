package pool;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GThreadPool {

	private ExecutorService executorService;

	public GThreadPool(int count) {
		// executorService =
		// Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		executorService = Executors.newFixedThreadPool(count);
	}

	private CompletionHandler<Integer, Void> callback = new CompletionHandler<Integer, Void>() {
		@Override
		public void completed(Integer result, Void attachment) {
			System.out.println("completed() ����: " + result);
		}

		@Override
		public void failed(Throwable exc, Void attachment) {
			System.out.println("failed() ����: " + exc.toString());
		}
	};

	public void simpleWork(Runnable task) {

		// Runnable runnable = new Runnable() {
		// @Override
		// public void run() {
		// // �����忡�� ��ų �۾� ����
		// ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
		// int poolSize = threadPoolExecutor.getPoolSize();// ������ Ǯ ������ ���
		// String threadName = Thread.currentThread().getName();// ������ Ǯ�� �ִ� �ش� ������ �̸�
		// ���
		// System.out.println("[�� ������ ����:" + poolSize + "] �۾� ������ �̸�: " + threadName);
		// // �Ϻη� ���� �߻� ��Ŵ
		// int value = Integer.parseInt("����");
		// }
		// };

		// ������Ǯ���� �۾� ó�� ��û
		// executorService.execute(task);
		executorService.submit(task);

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void doWork(final String x, final String y) {
		//return �� �ʿ�� callable
//		Callable<T> task = new Callable<T>() {
//		    @Override
//		    public T call() throws Exception {
//		        // Code
//		        return T;
//		    }
//		};

//		Callable<Integer> task = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                int sum = 0;
//                
//                for (int i = 1; i <= 10; i++) {
//                    sum += i;
//                }
//                
//                return sum;
//            }
//        };


		//���ϰ� �ʿ� ������
		Runnable task = new Runnable() {
			public void run() {
				try {
					int intX = Integer.parseInt(x);
					int intY = Integer.parseInt(y);

					int result = intX + intY;
					callback.completed(result, null);

				} catch (NumberFormatException e) {
					callback.failed(e, null);
				}
			}
		};
		doWork(task, x, y);
		//doWork(()->{}, x, y);
	}

	public void doWork(Runnable task, final String x, final String y) {
		//�۾��Ұ�
		executorService.submit(task);
		//Future<T> future=executorService.submit(task);
		//Future<Integer> future = executorService.submit(task);
		//T result = future.get();
	}

	public void finish() {
		executorService.shutdown();
	}

}
