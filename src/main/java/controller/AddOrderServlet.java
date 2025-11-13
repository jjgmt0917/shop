package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;
import dao.*;
import dto.*;

@WebServlet("/customer/addOrders")
public class AddOrderServlet extends HttpServlet {
	GoodsDao goodsDao;
	CartDao cartDao;
	AddressDao addressDao;
	OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// goodsOne action
		String goodsCode = request.getParameter("goodsCode");
		String goodsQuantity = request.getParameter("quantity");
		System.out.println("goodsCode: " + goodsCode);
		System.out.println("goodsQuantity: " + goodsQuantity);
		
		String[] cartCodeList = request.getParameterValues("cartCodeList");
		System.out.println("carCodeList: " + cartCodeList);
		
		List<Map<String, Object>> list = new ArrayList<>();
		// Map : 상품정보 + 이미지정보 + 수량
		if(goodsCode != null) { // goodsOne action
			// list.size() -> 하나의 상품
			// goodsCode를 사용하여 조인
			goodsDao = new GoodsDao();
			Map<String, Object> m = goodsDao.selectGoodsOne(Integer.parseInt(goodsCode));
			m.put("goodsQuantity", goodsQuantity);
			list.add(m);
			
		} else {	// cartList action
			// list.size() -> 하나이상의 상품
			// cartCodeList -> goods 정보 -> 조인
			cartDao = new CartDao();
			for(String cc : cartCodeList) {
				int cartCode = Integer.parseInt(cc);
				Map<String, Object> m = cartDao.selectCartListByKey(cartCode);
				list.add(m);
//				cartDao.delecteCar(cc); // 주문 목록으로 이동후 cart에서는 삭제
			}
		}
		
		int totalPrice = 0;
		for(Map m : list) {
			totalPrice += (Integer)(m.get("goodsPrice"));
		}
		request.setAttribute("list", list);
		request.setAttribute("totalPrice", totalPrice);
		
		HttpSession session = request.getSession();
		Customer loginCust = (Customer)(session.getAttribute("loginCustomer"));
		addressDao = new AddressDao();
		List<Address> addressList = addressDao.selectAddressList(loginCust.getCustomerCode());
		
		request.setAttribute("addressList", addressList);
		
		request.getRequestDispatcher("/WEB-INF/view/customer/addOrders.jsp").forward(request, response);
	}
	// addOrder.jsp action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCust = (Customer)(session.getAttribute("loginCustomer"));
		String addressCode = request.getParameter("addressCode");
		String orderPrice = request.getParameter("orderPrice");		// 결제 모듈 추가 후 구현 가능
		
		String[] goodsCodeList = request.getParameterValues("goodsCode");
		String[] orderQuantityList = request.getParameterValues("orderQuantity");
		String[] goodsPriceList = request.getParameterValues("goodsPrice");
		
		
		System.out.println("loginCustomer: " + loginCust.getCustomerCode());
		System.out.println("addressCode: " + addressCode);
		System.out.println("orderPrice: " + orderPrice);		// 결제 모듈 추가 후 구현 가능
		System.out.println("goodsCodeList: " + goodsCodeList.length);
		System.out.println("orderQuantityList: " + orderQuantityList.length);
		System.out.println("goodsPriceList: " + goodsPriceList.length);
		
		// 1) insert payment 테이블에 결제 행을 추가 - 테이블 추가 필요
		ordersDao = new OrdersDao();
		// 2) insert orders 각 상품별로 주문행 추가
		for(int i=0; i < goodsCodeList.length; i = i + 1) {	
			Orders o = new Orders();
			o.setCustomerCode(loginCust.getCustomerCode());
			o.setAddressCode(Integer.parseInt(addressCode));
			o.setGoodscode(Integer.parseInt(goodsCodeList[i]));
			o.setOrderQuantity(Integer.parseInt(orderQuantityList[i]));
			// goodsPrice는 실제 상품 가격 / orderPrice는 할인가격일수도 있따.
			o.setOrderPrice(Integer.parseInt(goodsPriceList[i]) * Integer.parseInt(orderQuantityList[i]));
			ordersDao.insertOrders(o);
		}
		
		// 1)번 테이블 추가호 구현 가능
		// 3) orders_payment 테이블에 payment pk와 orders pk를 연결(insert)
	}

}
