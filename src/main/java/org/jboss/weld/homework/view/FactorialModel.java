package org.jboss.weld.homework.view;

import java.math.BigInteger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.weld.homework.SimpleCDIbean;

/**
 * Backing bean for the factorial form (factorial.xhtml)
 */
@Named("factorial")
@RequestScoped
public class FactorialModel {

	@Inject
	private SimpleCDIbean simpleCDIbean;
	
    private Long input;
    private BigInteger result;

    public void compute() {
    	result = simpleCDIbean.compute(input);
    }

    public void reset() {
        this.input = null;
        this.result = null;
    }

    public Long getInput() {
        return input;
    }

    public void setInput(Long input) {
        this.input = input;
    }

    public BigInteger getResult() {
        return result;
    }
}
