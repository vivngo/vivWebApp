package com.viv.mvcapp.services;
import java.util.Calendar;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;

public class ContextAndSessionListener
    implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("((Listener)) contextInitialized: " + context.getServletContextName());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("((Listener)) contextDestroyed: " + context.getServletContextName());
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        event.getServletContext().log("((Listener)) servlet - attributeAdded: " + event);
//        showAttributeElements(event);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        event.getServletContext().log("((Listener)) servlet - attributeAdded: " + event);
//        showAttributeElements(event);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent arg0) {
        arg0.getServletContext().log("((Listener)) servlet - attributeReplaced: " + arg0);
//        showAttributeElements(arg0);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Calendar today = Calendar.getInstance();
        session.getServletContext().log("((Listener)) sessionCreated: ID = " + session.getId());
        session.setAttribute("dayOfWeek", convertToDayOfWeekString(today.get(Calendar.DAY_OF_WEEK)));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        long duration = session.getLastAccessedTime() - session.getCreationTime();
        session.getServletContext().log("((Listener)) sessionDestroyed: ID = " + session.getId() + ", duration: " + duration);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        event.getSession().getServletContext().log("((Listener)) session - attributeAdded: " + event);
        showAttributeElements(event);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        event.getSession().getServletContext().log("((Listener)) session - attributeRemoved: " + event);
        showAttributeElements(event);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        event.getSession().getServletContext().log("((Listener)) session - attributeReplaced: " + event);
        showAttributeElements(event);
        
    }
    
    private String convertToDayOfWeekString(int numDay) {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (numDay < 8) {
            return daysOfWeek[numDay-1];
        } else {
            return "day";
        }
    }
    
    private void showAttributeElements(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        Enumeration<?> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String current = (String) attributeNames.nextElement();
            session.getServletContext().log("((Listener)) ATTRIBUTE " + current + " = " + session.getAttribute(current));
        }
    }
    
//    private void showAttributeElements(ServletContextAttributeEvent event) {
//        ServletContext context = event.getServletContext();
//        Enumeration<?> attributeNames = context.getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            String current = (String) attributeNames.nextElement();
//            context.log("((Listener)) ATTRIBUTE " + current + " = " + context.getAttribute(current));
//        }
//    }
}
