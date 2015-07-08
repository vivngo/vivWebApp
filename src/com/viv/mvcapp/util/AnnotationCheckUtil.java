package com.viv.mvcapp.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import com.viv.mvcapp.domain.Factoid;
import com.viv.mvcapp.domain.ValidFileName;

public final class AnnotationCheckUtil {
	private AnnotationCheckUtil() {
		throw new AssertionError();
	}
	
	public static boolean validFileName(Factoid fact) {
		boolean valid = false;
		ArrayList<Field> fields = getAllDeclaredFields(fact);
		
		for (Field field : fields) {
			if (field.isAnnotationPresent(ValidFileName.class)) {
				if (fact.getPic().endsWith(ValidFileName.fileExtension)) {
					valid = true;
				} else {
					valid = false;
				}
			}
		}
		
		return valid;
	}
	
	private static ArrayList<Field> getAllDeclaredFields(Object obj) {
		ArrayList<Field> fields = new ArrayList<Field>();
		fields.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
		
		Class<?> parent = obj.getClass().getSuperclass();
		while (parent != null) {
			fields.addAll(Arrays.asList(parent.getDeclaredFields()));
			parent = parent.getSuperclass();
		}
		
		return fields;
	}
}
