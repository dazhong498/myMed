package controller;

import pojo.Medicine;
import repository.MedicineRepository;
import repository.MedicinesRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.ws.WebEndpoint;
import java.io.IOException;
import java.util.List;

@WebServlet("/getProducts")
public class MedicinesController extends HttpServlet {

    MedicineRepository medicineRepository = new MedicinesRepositoryImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
//        if(cookies != null){
//            for(Cookie cookie: cookies){
////                System.out.println(cookie.getName() + ": " + cookie.getValue() + "in log");
//            }
//        }

        List<Medicine> medicineList = medicineRepository.getAllMedicines();
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("medicine", medicineList);

        req.setAttribute("products", medicineList);
        req.getRequestDispatcher("products").forward(req, resp);

    }
}
