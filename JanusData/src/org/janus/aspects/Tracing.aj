package org.janus.aspects;

import org.apache.log4j.Logger;
import org.janus.actions.HandleValue;
import org.janus.fluentActions.ColumnSum;


public aspect Tracing {
		static Logger logger = Logger.getLogger("TraceLogger");

	
	    pointcut myClass(): within(HandleValue) || within(ColumnSum);
	    pointcut myConstructor(): myClass() && execution(new(..));
	    pointcut myMethod(): myClass() && execution(* *(..));

	    before (): myConstructor() {
	        logger.debug("" + thisJoinPointStaticPart.getSignature());
	    }
	    after(): myConstructor() {
	    	logger.debug("" + thisJoinPointStaticPart.getSignature());
	    }

	    before (): myMethod() {
	    	System.out.println("" + thisJoinPointStaticPart.getSignature());
	    }
	    after(): myMethod() {
	    	logger.debug("" + thisJoinPointStaticPart.getSignature());
	    }

}
