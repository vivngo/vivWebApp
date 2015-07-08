package com.viv.mvcapp.tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.viv.mvcapp.util.AnnotationCheckUtil;
import com.viv.mvcapp.domain.Factoid;

public class AnnotationCheckUtilTest {

	@Test
	public void testValidFileName() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			Factoid fact = new Factoid();
			fact.setPic("pic.pdf");
			boolean valid = AnnotationCheckUtil.validFileName(fact);
			assertFalse("did not catch invalid file name", valid);
			
			fact = new Factoid();
			fact.setPic("pic.png");
			valid = AnnotationCheckUtil.validFileName(fact);
			assertTrue("incorrectly caught valid file name", valid);
		
	}

}
