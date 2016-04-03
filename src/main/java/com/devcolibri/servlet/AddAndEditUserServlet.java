package com.devcolibri.servlet;

import com.devcolibri.bean.UserBean;
import com.devcolibri.entity.Usev;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/add")
public class AddAndEditUserServlet extends HttpServlet {

    @EJB
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        if(req.getParameter("edit") != null){
            long id = Long.valueOf(req.getParameter("edit"));
            Usev usev = userBean.get(id);

            req.setAttribute("usev", usev);
        }

        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        int age = Integer.valueOf(req.getParameter("age"));

        if(req.getParameter("id") != ""){
            long id = Long.valueOf(req.getParameter("id"));
            Usev usev = userBean.get(id);
            usev.setAge(age);
            usev.setLastName(lastName);
            usev.setName(name);
            userBean.update(usev);
        } else{
            userBean.add(new Usev(name, lastName, age));
        }
        resp.sendRedirect("list");
    }
}
