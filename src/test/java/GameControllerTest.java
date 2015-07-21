package test.java;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.viv.mvcapp.domain.GameState;
import com.viv.mvcapp.web.GameController;

public class GameControllerTest {

	@Test
	public void testControlTreasureGameStart() {
		GameController controller = new GameController();
		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		ModelAndView mv = new ModelAndView();
		
		GameState gs;
		String action = "start";
		request.addParameter("numRows", "3");
		
		gs = controller.controlTreasureGame(mv, request, session, action);
		
		assertNotNull("Game not made", gs);
		assertSame("improper number of rows", 3, gs.getNumRows());
		assertSame("improper number of buttons", 9, gs.getNumButtons());
		
	}
	
	@Test
	public void testControlTreasureGameWin() {
		GameController controller = new GameController();
		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		ModelAndView mv = new ModelAndView();
		
		GameState gs;
		String action = "start";
		request.addParameter("numRows", "3");
		
		gs = controller.controlTreasureGame(mv, request, session, action);
		assertFalse("won game without starting", gs.isWon());
		
		action = "move";
		int index = 0;
		do {
			request.removeParameter("index");
			request.addParameter("index", Integer.toString(index));
			gs = controller.controlTreasureGame(mv, request, session, action);
			index++;
		} while( !gs.isWon() );
		
		while (index < 9) {
			request.removeParameter("index");
			request.addParameter("index", Integer.toString(index));
			gs = controller.controlTreasureGame(mv, request, session, action);
			index++;
			assertFalse("multiple buttons win game", gs.isWon());
		}
	}
	
}
