package controller;


import pojo.Medicine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/addCartSession")
public class AddToCookie extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        Cookie[] cookies = req.getCookies();
        List<Medicine> medicines = (List<Medicine>)httpSession.getAttribute("medicine");
        String medName = null;
        for(Medicine medicine: medicines){
            if(medicine.getId() == Integer.parseInt(req.getParameter("medId"))){
                medName = medicine.getName();
            }
        }

        if(httpSession.getAttribute(medName) == null){
            httpSession.setAttribute(medName, 1);
        }else{
            httpSession.setAttribute(medName, (int)httpSession.getAttribute(medName) + 1);
        }

        System.out.println(cookies);
        int cookieVal = 0;
        for(Cookie cookie: cookies){
            if(cookie.getName().equals(medName)){
                cookieVal = Integer.parseInt(cookie.getValue());
            }
        }

        if(cookieVal == 0){
            resp.addCookie(new Cookie(medName, "1"));
        }else{
            resp.addCookie(new Cookie(medName, cookieVal + 1 + ""));
        }

        System.out.println("================");
//        Enumeration<String> stringEnumeration = httpSession.getAttributeNames();
//        while(stringEnumeration.hasMoreElements()){
//            String str = stringEnumeration.nextElement();
//        }
        req.getRequestDispatcher("products").forward(req, resp);
    }


}
