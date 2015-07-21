package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import com.viv.mvcapp.domain.GameState;

public class GameStateTest {
	
	@Test
	public void testGetStartTime_EmptyConstr() {
		GameState gs = new GameState();
		assertNotNull("startTime field empty - empty constr", gs.getStartTime());
	}
	
	@Test
	public void testGetStartTime_IntConstr() {
		GameState gs = new GameState(3);
		assertNotNull("startTime field empty - int constr", gs.getStartTime());
	}
	
	@Test
	public void testGetStartTime_StringConstr() {
		GameState gs = new GameState("3");
		assertNotNull("startTime field empty - string constr", gs.getStartTime());
	}

	@Test
	public void testGetEndTime_EmptyConstr() {
		GameState gs = new GameState();
		assertNull("endTime field not empty - empty constr", gs.getEndTime());
		gs.setEndTime();
		assertNotNull("endTime field empty - empty constr", gs.getEndTime());
	}
	
	@Test
	public void testGetEndTime_IntConstr() {
		GameState gs = new GameState(3);
		assertNull("endTime field not empty - int constr", gs.getEndTime());
		gs.setEndTime();
		assertNotNull("endTime field empty - int constr", gs.getEndTime());
	}
	
	@Test
	public void testGetEndTime_StringConstr() {
		GameState gs = new GameState("3");
		assertNull("endTime field not empty - str constr", gs.getEndTime());
		gs.setEndTime();
		assertNotNull("endTime field empty - str constr", gs.getEndTime());
	}

	@Test
	public void testIsWon_EmptyConstr() {
		GameState gs = new GameState();
		assertFalse("won field initialized incorrectly - empty constr", gs.isWon());
		gs.setWon(true);
		assertTrue("won field expected to be true - empty constr", gs.isWon());
	}
	
	@Test
	public void testIsWon_IntConstr() {
		GameState gs = new GameState(3);
		assertFalse("won field initialized incorrectly - int constr", gs.isWon());
		gs.setWon(true);
		assertTrue("won field expected to be true - int constr", gs.isWon());
	}
	
	@Test
	public void testIsWon_StringConstr() {
		GameState gs = new GameState("3");
		assertFalse("won field initialized incorrectly - string constr", gs.isWon());
		gs.setWon(true);
		assertTrue("won field expected to be true - string constr", gs.isWon());
	}

	@Test
	public void testGetTreasureLocation_EmptyConstr() {
		GameState gs = new GameState();
		boolean valid = false;
		int location = gs.getTreasureLocation();
		
		if (location < 9 && location > -1) {
			valid = true;
		}
		
		assertTrue("invalid value for treasureLocation - empty constr", valid);
	}
	
	@Test
	public void testGetTreasureLocation_IntConstr() {
		GameState gs = new GameState(3);
		boolean valid = false;
		int location = gs.getTreasureLocation();
		
		if (location < 9 && location > -1) {
			valid = true;
		}
		
		assertTrue("invalid value for treasureLocation - int constr", valid);
	}
	
	@Test
	public void testGetTreasureLocation_StringConstr() {
		GameState gs = new GameState("3");
		boolean valid = false;
		int location = gs.getTreasureLocation();
		
		if (location < 9 && location > -1) {
			valid = true;
		}
		
		assertTrue("invalid value for treasureLocation - string constr", valid);
	}

	@Test
	public void testGetNumButtons_EmptyConstr() {
		GameState gs = new GameState();
		assertSame("incorrect value for numButtons - empty constr", 9, gs.getNumButtons());
	}
	
	@Test
	public void testGetNumButtons_IntConstr() {
		GameState gs = new GameState(3);
		assertSame("incorrect value for numButtons - int constr", 9, gs.getNumButtons());
	}
	
	@Test
	public void testGetNumButtons_StringConstr() {
		GameState gs = new GameState("3");
		assertSame("incorrect value for numButtons - string constr", 9, gs.getNumButtons());
	}

	@Test
	public void testGetButtonText_EmptyConstr() {
		GameState gs = new GameState();
		String[] expectedText = {"dig", "dig", "dig", "dig", "dig", "dig", "dig", "dig", "dig"};
		assertArrayEquals("incorrect value for buttonText - empty constr", expectedText, gs.getButtonText());
	}
	
	@Test
	public void testGetButtonText_IntConstr() {
		GameState gs = new GameState(3);
		String[] expectedText = {"dig", "dig", "dig", "dig", "dig", "dig", "dig", "dig", "dig"};
		assertArrayEquals("incorrect value for buttonText - empty constr", expectedText, gs.getButtonText());
	}
	
	@Test
	public void testGetButtonText_StringConstr() {
		GameState gs = new GameState("3");
		String[] expectedText = {"dig", "dig", "dig", "dig", "dig", "dig", "dig", "dig", "dig"};
		assertArrayEquals("incorrect value for buttonText - empty constr", expectedText, gs.getButtonText());
	}

	@Test
	public void testGetNumRows_EmptyConstr() {
		GameState gs = new GameState();
		assertSame("incorrect value for numRows - empty constr", 3, gs.getNumRows());
	}
	
	@Test
	public void testGetNumRows_IntConstr() {
		GameState gs = new GameState(3);
		assertSame("incorrect value for numRows - int constr", 3, gs.getNumRows());
	}
	
	@Test
	public void testGetNumRows_StringConstr() {
		GameState gs = new GameState("3");
		assertSame("incorrect value for numRows - string constr", 3, gs.getNumRows());
	}

}
