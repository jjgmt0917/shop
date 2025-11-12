package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import dao.*;
import dto.*;

@WebServlet("/customer/addCart")
public class AddCartController extends HttpServlet {
	CartDao cartDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCust = (Customer)session.getAttribute("loginCustomer");
		
		String goodsCode = request.getParameter("goodsCode");
		String goodsQuantity = request.getParameter("goodsQuantity");
		int customerCode = loginCust.getCustomerCode();
		System.out.println(goodsCode);
		System.out.println(goodsQuantity);
		
		Cart c = new Cart();
		c.setGoodsCode(Integer.parseInt(goodsCode));
		c.setCustomerCode(customerCode);
		c.setGoodsQuantity(Integer.parseInt(goodsQuantity));
		
		cartDao.insertCart(c);
		
		response.sendRedirect(request.getContextPath() + "/customer/cartList");
		
	}

}
