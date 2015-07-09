package com.viv.mvcapp.tests;

import static org.junit.Assert.*;
import junit.runner.Version;

import org.junit.Test;

import com.viv.mvcapp.domain.Factoid;
import com.viv.mvcapp.domain.FlamingoFact;
import com.viv.mvcapp.domain.JellyfishFact;

public class FactoidTest {

	@Test
	public void testSetAndGetPic() {
		Factoid fact = new Factoid();
		fact.setPic("test.png");
		assertEquals("unexpected pic name attribute", "resources/test.png", fact.getPic());
	}

	@Test
	public void testSetAndGetTxt() {
		Factoid fact = new Factoid();
		fact.setTxt("test");
		assertEquals("unexpected text attribute", "test", fact.getTxt());
	}
	
	@Test
	public void testFlamingoFact() {
		Factoid fact = new FlamingoFact();
		assertEquals("unexpected pic attribute - flamingo", "resources/flamingo.png", fact.getPic());
		assertEquals("unexpected text attribute - flamingo", "Flamingos turn pink from eating shrimp!", fact.getTxt());
	}
	
	@Test
	public void testJellyfishFact() {
		Factoid fact = new JellyfishFact();
		assertEquals("unexpected pic attribute - jellyfish", "resources/jellyfish.png", fact.getPic());
		assertEquals("unexpected text attribute - jellyfish", "Jellyfish have no brains!", fact.getTxt());
	}

}
