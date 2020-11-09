package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.eloan.controller.AdminController;
import com.iiht.evaluation.eloan.model.LoanInfo;

public class AdminDao {

	public static final String SELECT_ALL_QRY = "SELECT applno,purpose,amtrequest,doa,bstructure,bindicator,address,email,mobile,status FROM LoanInfo";

	public static List<LoanInfo> listAll() {
		List<LoanInfo> loans = new ArrayList<>();
		try {
			ConnectionDao conn = AdminController.getConnDao();
			Connection con = conn.connect();
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(SELECT_ALL_QRY);

			while (rs.next()) {
				LoanInfo loanInfo = new LoanInfo();
				loanInfo.setApplno(rs.getString(1));
				loanInfo.setPurpose(rs.getString(2));
				loanInfo.setAmtrequest(rs.getInt(3));
				loanInfo.setDoa(rs.getString(4));
				loanInfo.setBstructure(rs.getString(5));
				loanInfo.setBindicator(rs.getString(6));
				loanInfo.setAddress(rs.getString(7));
				loanInfo.setEmail(rs.getString(8));
				loanInfo.setMobile(rs.getString(9));
				loanInfo.setStatus(rs.getString(10));

				loans.add(loanInfo);
			}

		} catch (SQLException exp) {

		}
		return loans;
	}

	public static String updateLoanStatus(int loanApplicationNumber, String loanStatus) {
		try {
			ConnectionDao conn = AdminController.getConnDao();
			Connection con = conn.connect();
			String UPD_Status_QRY = "UPDATE LoanInfo SET status= " + loanStatus +" WHERE applno= " + loanApplicationNumber;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(UPD_Status_QRY);
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return "SUCCESS";
	}

	public static void process() {
		// TODO Auto-generated method stub

	}
}
