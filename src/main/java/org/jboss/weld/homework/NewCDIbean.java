package org.jboss.weld.homework;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Parallel
@ApplicationScoped
public class NewCDIbean implements Factorial {

	@Inject
	MathOperations mathOperations;
	
	@Override
	public BigInteger compute(long number) {
		BigInteger result = BigInteger.ONE;
		if (number == 0) {
			result = BigInteger.ONE;
		} else {
			try {
				long half = number / 2;
				Future<BigInteger> first = mathOperations.multiplySequenceAsync(1, half);
				Future<BigInteger> second = mathOperations.multiplySequenceAsync(half + 1, number);
				// Wait until they are all done
		        while (!(first.isDone() && second.isDone())) {
		            Thread.sleep(10); //10-millisecond pause between each check
		        }
				result = first.get().multiply(second.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

}
