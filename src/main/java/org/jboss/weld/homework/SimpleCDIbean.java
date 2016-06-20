package org.jboss.weld.homework;

import java.math.BigInteger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@ApplicationScoped
public class SimpleCDIbean implements Factorial {
	@Inject
	MathOperations mathOperations;

	@Inject
	@Any
	Event<FactorialComputationFinished> factorialComputationFinished;
	
	@Override
	public BigInteger compute(long number) {
		BigInteger result;
		if (number == 0) {
			result = BigInteger.ONE;
		} else {
			result = mathOperations.multiplySequence(1, number);
		}
		factorialComputationFinished.fire(new FactorialComputationFinished(number, result));
		return result;
	}
}
