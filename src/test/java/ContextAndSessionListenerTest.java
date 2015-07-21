package test.java;

import static org.junit.Assert.assertNotNull;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import com.viv.mvcapp.services.ContextAndSessionListener;

public class ContextAndSessionListenerTest {

	@Test
	public void testSessionCreated() {
		
		ContextAndSessionListener casl = new ContextAndSessionListener();
		HttpSession session = new MockHttpSession();
		HttpSessionEvent se = new HttpSessionEvent(session);
		casl.sessionCreated(se);
		assertNotNull("session made, but didn't set dayOfWeek", session.getAttribute("dayOfWeek"));
		
	}

}
