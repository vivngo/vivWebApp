package com.viv.mvcapp.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viv.mvcapp.util.StringManipUtil;

//conflict 1
@WebFilter(urlPatterns = {"/*"}, description = "Name Check Filter")
public class NameCheckFilter implements Filter {
    
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;
    
    public NameCheckFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("MyFirstFilter:DoBeforeProcessing");
        }
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("MyFirstFilter:DoAfterProcessing");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("MyFirstFilter:doFilter()");
            
            log("Request parameters:" + request.getParameterNames().toString());
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            //Code added by Vivien
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse resp = (HttpServletResponse)response;
            
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            firstName = StringManipUtil.capitalizeFirstOnly(firstName);
            lastName = StringManipUtil.capitalizeFirstOnly(lastName);
            firstName = StringManipUtil.changeNullToEmpty(firstName);
            lastName = StringManipUtil.changeNullToEmpty(lastName);
            
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            
            if ("John".equals(firstName) && "Doe".equals(lastName)) {
                request.setAttribute("message", "You should use something more creative than ");
            } else {
                request.setAttribute("message", "What's up, ");
            }
            
            String admin = req.getSession().getServletContext().getInitParameter("adminUser");
            String formSubmit = request.getParameter("formSubmit");
            if ("true".equals(formSubmit)) {
                String userName = StringManipUtil.combineNames(firstName, lastName);
                
                if(admin.equals(userName)){
                    userName = admin + " (Admin)";
                }
                
                Cookie userCookie = new Cookie("userCookie", userName);
                userCookie.setMaxAge(30*60);
                resp.addCookie(userCookie);
                
                req.getSession().setAttribute("userSession", userName);
            }
            
            Cookie[] cookies = req.getCookies();
            boolean foundUserCookie = false;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userCookie")) {
                        request.setAttribute("userName", cookie.getValue());
                        foundUserCookie = true;
                    }
                }
            }
            if (!foundUserCookie) {
                request.setAttribute("userName", req.getSession().getAttribute("userSession"));
            }
            
            Enumeration<?> paramNames = request.getParameterNames();
            boolean inputContainsScript = false;
            while(paramNames.hasMoreElements()){
            	String paramValue = request.getParameter((String)paramNames.nextElement());
                if(paramValue.contains("<script>")){
                    log("Something contains the string \"<script>\"");
                    inputContainsScript = true;
                }
            }
            request.setAttribute("inputContainsScript", inputContainsScript);
            //end of code added by Vivien
            
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("MyFirstFilter:Initializing filter");
            }
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("MyFirstFilter()");
        }
        StringBuffer sb = new StringBuffer("MyFirstFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            	ex.printStackTrace();
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            	ex.printStackTrace();
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
