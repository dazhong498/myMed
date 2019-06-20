package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



//class FilteredRequest extends HttpServletRequestWrapper {
//
//    public FilteredRequest(ServletRequest request) {
//        super((HttpServletRequest)request);
//    }
//
//    @Override
//    public String getParameter(String paramName) {
//        String value = super.getParameter(paramName);
//        if ("dangerousParamName".equals(paramName)) {
//            value = sanitize(value);
//        }
//        return value;
//    }
//}

@WebFilter("/pay")
public class PayFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


//        FilteredRequest filteredRequest = new FilteredRequest(request);
        System.out.println(request.getParameter("dangerousParamName"));
        System.out.println("1232312313123");
//
//        System.out.println(request.getParameterValues("userName"));
//



        HttpServletRequest req= (HttpServletRequest)request;
        System.out.println(req.getParameter("dangerousParamName"));

        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("user") == null){
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect("login");
        }else{
            chain.doFilter(request, response);
        }
    }


    public void destroy() {}

    public void init(FilterConfig filterConfig) {}

}
