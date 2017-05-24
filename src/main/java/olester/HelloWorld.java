package olester;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class HelloWorld {
	public static void main(String[] args) {
//		Flowable.just("Hello world").subscribe(System.out::println);
//
//		Flowable.fromCallable(() -> {
//			Thread.sleep(1000); //  imitate expensive computation
//			return new HashMap<>();
//		})
//				.subscribeOn(Schedulers.computation())
////				.observeOn(Schedulers.single())
//				.subscribe((x) -> System.out.println(x +  " " + Thread.currentThread().getName()), Throwable::printStackTrace);
//


		Observable<Integer> source = Observable.range(1, 10);

		source.map(i -> i * 100)


				.doOnNext(i -> System.out.println("Emitting " + i
						+ " on thread " + Thread.currentThread().getName()))
				.observeOn(Schedulers.computation())
				.map(i -> i * 10)
				.subscribeOn(Schedulers.computation())
				.subscribe(i -> System.out.println("Received " + i + " on thread "
						+ Thread.currentThread().getName()));


		try {
			Thread.sleep(5); // <--- wait for the flow to finish
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
