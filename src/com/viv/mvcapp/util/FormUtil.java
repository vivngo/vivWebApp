package com.viv.mvcapp.util;

import com.viv.mvcapp.domain.Factoid;
import com.viv.mvcapp.domain.FlamingoFact;
import com.viv.mvcapp.domain.JellyfishFact;

/**
 *
 * @author Vivien Ngo
 */
public final class FormUtil {
	private FormUtil() {
		throw new AssertionError();
	}
	
    public static Factoid randomFact(){    
        Factoid funFact = null;
        
        int chooseFact = (int)(Math.random()*2);
        switch (chooseFact){
            case 0:
                funFact = new FlamingoFact();
                break;
            case 1:
                funFact = new JellyfishFact();
                break;
            default:
                break;
        }
        
        return funFact;
    }
}
