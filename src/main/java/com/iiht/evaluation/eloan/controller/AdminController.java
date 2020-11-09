package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.dao.AdminDao;
import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;

@WebServlet({ "/admin", "/listall", "/process", "/callemi", "/updatestatus", "/logout" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ConnectionDao connDao;

	public static void setConnDao(ConnectionDao connDao) {
		AdminController.connDao = connDao;
	}

	public static ConnectionDao getConnDao() {
		return connDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "listall":
				viewName = listall(request, response);
				break;
			case "process":
				viewName = process(request, response);
				break;
			case "callemi":
				viewName = calemi(request, response);
				break;
			case "updatestatus":
				viewName = updatestatus(request, response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);

	}

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code for updatestatus of loan and return to admin home page */
		int loanApplicationNumber = Integer.parseInt(request.getParameter("loanApplicationNumber"));
		String loanStatus = request.getParameter("loanStatus");
		AdminDao.updateLoanStatus(loanApplicationNumber, loanStatus);
		request.setAttribute("msg", "Loan Status updated Successfully");
		return "admin.jsp";
	}

	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to calculate emi for given applno and display the details */
		int amotsanctioned = Integer.parseInt(request.getParameter("amotsanctioned"));
		int interestrate = Integer.parseInt(request.getParameter("interestrate"));
		int loanterm = Integer.parseInt(request.getParameter("loanterm"));
		Integer termpaymentamount = (amotsanctioned) * (1 + interestrate / 100) ^ (loanterm);
		Integer EMI = termpaymentamount / loanterm;
		ApprovedLoan loan = new ApprovedLoan();
		loan.setAmotsanctioned(amotsanctioned);
		loan.setInterestrate(interestrate);
		loan.setLoanterm(loanterm);
		loan.setEmi(EMI);
		request.setAttribute("Calculated EMI", loan);
		return "callemi.jsp";
	}

	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* return to process page */
		AdminDao.process();
		return "process";
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write code to return index page */
		request.setAttribute("msg", "Admin Logout Successfully");
		return "index.jsp";
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to display all the loans */
		List<LoanInfo> loans = AdminDao.listAll();
		request.setAttribute("loanList", loans);
		return "listall.jsp";

	}

}