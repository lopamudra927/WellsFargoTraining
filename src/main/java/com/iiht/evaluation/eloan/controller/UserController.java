package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dao.UserDao;
import com.iiht.evaluation.eloan.dto.UserDto;
import com.iiht.evaluation.eloan.model.LoanInfo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ConnectionDao connDao;

	public static void setConnDao(ConnectionDao connDao) {
		UserController.connDao = connDao;
	}

	public static ConnectionDao getConnDao() {
		return connDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		UserController.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName = registernewuser(request, response);
				break;
			case "validate":
				viewName = validate(request, response);
				break;
			case "placeloan":
				viewName = placeloan(request, response);
				break;
			case "application1":
				viewName = application1(request, response);
				break;
			case "editLoanProcess":
				viewName = editLoanProcess(request, response);
				break;
			case "registeruser":
				viewName = registerUser(request, response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;
			case "displaystatus":
				viewName = displaystatus(request, response);
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

	private String validate(HttpServletRequest request, HttpServletResponse response) {
		/* write the code to validate the user */

		return null;
	}

	private String placeloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to place the loan information */
		String loanName = request.getParameter("loanName");
		String applNumber = request.getParameter("applNumber");
		int amtRequest = Integer.parseInt(request.getParameter("amtRequest"));
		String appDate = request.getParameter("appDate");
		String businessStructure = request.getParameter("businessStructure");
		String billingIndicator = request.getParameter("billingIndicator");
		String taxIndicator = request.getParameter("taxIndicator");
		String mob = request.getParameter("mobile");
		String email = request.getParameter("email");

		LoanInfo loanInfo = new LoanInfo();

		loanInfo.setApplno(applNumber);
		loanInfo.setAmtrequest(amtRequest);
		loanInfo.setDoa(appDate);
		loanInfo.setBstructure(businessStructure);
		loanInfo.setBindicator(billingIndicator);
		loanInfo.setMobile(mob);
		loanInfo.setEmail(email);

		UserDao userDao = new UserDao();

		String applyLoan = userDao.applyMortgageLoan(loanInfo);

		if (applyLoan.equals("SUCCESS")) {
			return "userhome.jsp";
		} else {
			request.setAttribute("errMessage", applyLoan);
			return "errorPage.jsp";
		}
	}

	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to display the loan application page */

		return null;
	}

	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */

		return null;
	}

	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		return "newuserui.jsp";
	}

	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * write the code to create the new user account read from user and return to
		 * index page
		 */
		String firstName = request.getParameter("username");
		String middleName = request.getParameter("password");
		String lastName = request.getParameter("password");
		String age = request.getParameter("password");
		String mob = request.getParameter("password");
		String email = request.getParameter("password");
		String occupation = request.getParameter("password");
		String address = request.getParameter("password");

		UserDto userDto = new UserDto();

		userDto.setFirstName(firstName);
		userDto.setMiddleName(middleName);
		userDto.setLastName(lastName);
		userDto.setAge(age);
		userDto.setMob(mob);
		userDto.setEmail(email);
		userDto.setOccupation(occupation);
		userDto.setAddress(address);

		UserDao userDao = new UserDao();

		String userRegistered = userDao.registerUser(userDto);

		if (userRegistered.equals("SUCCESS")) {
			return "userhome.jsp";
		} else {
			request.setAttribute("errMessage", userRegistered);
			return "errorPage.jsp";
		}
	}

	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */

		return null;
	}

	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * write the code the display the loan status based on the given application
		 * number
		 */
		String loanNumber = request.getParameter("loanNumber");
		UserDao userDao = new UserDao();
		String status = userDao.checkLoanStatus(loanNumber);
		request.setAttribute("status", status);

		return "loanDetails.jsp";
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write a code to return to edit loan page */
		String loanNumber = request.getParameter("loanNumber");
		UserDao userDao = new UserDao();
		UserDto userDto = userDao.trackLoanApplication(loanNumber);
		if (userDto.getLoanStatus().equalsIgnoreCase("approved")) {
			request.setAttribute("errMessage", "Cannot edit loan as it is already approved by manager.");
			return "errorPage.jsp";
		} else {
			String firstName = request.getParameter("username");
			String middleName = request.getParameter("password");
			String lastName = request.getParameter("password");
			String age = request.getParameter("password");
			String mob = request.getParameter("password");
			String email = request.getParameter("password");
			String occupation = request.getParameter("password");
			String address = request.getParameter("password");
			// UserDto userDto = new UserDto();
			userDto.setFirstName(firstName);
			userDto.setMiddleName(middleName);
			userDto.setLastName(lastName);
			userDto.setAge(age);
			userDto.setMob(mob);
			userDto.setEmail(email);
			userDto.setOccupation(occupation);
			userDto.setAddress(address);

			String loanEditStatus = userDao.editLoanApplication(userDto);

			if (loanEditStatus.equals("SUCCESS")) {
				return "editloan.jsp";
			} else {
				request.setAttribute("errMessage", loanEditStatus);
				return "errorPage.jsp";
			}
		}
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write a code to return to trackloan page */

		String loanNumber = request.getParameter("loanNumber");
		UserDao userDao = new UserDao();
		UserDto userDto = userDao.trackLoanApplication(loanNumber);
		request.setAttribute("status", userDto);

		return "trackloan.jsp";
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write a code to return to trackloan page */
		return null;
	}
}