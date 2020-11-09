package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iiht.evaluation.eloan.controller.UserController;
import com.iiht.evaluation.eloan.dto.UserDto;
import com.iiht.evaluation.eloan.model.LoanInfo;

public class UserDao {

	public String registerUser(UserDto userDto) {
		try {
			ConnectionDao conn = UserController.getConnDao();
			Connection con = conn.connect();
			PreparedStatement preparedStatement = null;
			String query = "insert into users(fName,mName,lName,mob,age,email,address,occupation) values (?,?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, userDto.getFirstName());
			preparedStatement.setString(2, userDto.getMiddleName());
			preparedStatement.setString(3, userDto.getLastName());
			preparedStatement.setString(4, userDto.getMob());
			preparedStatement.setString(5, userDto.getAge());
			preparedStatement.setString(6, userDto.getEmail());
			preparedStatement.setString(7, userDto.getAddress());
			preparedStatement.setString(8, userDto.getOccupation());

			int i = preparedStatement.executeUpdate();

			if (i != 0)
				return "SUCCESS";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Oops.. Something went wrong there..!";
	}

	public String applyMortgageLoan(LoanInfo loanInfo) {
		try {
			ConnectionDao conn = UserController.getConnDao();
			Connection con = conn.connect();
			PreparedStatement preparedStatement = null;
			String query = "insert into loanDB(applicationNumber,amountRequest,appDate,billingStructure,billingIndicator,mobile,email) values (?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, loanInfo.getApplno());
			preparedStatement.setInt(2, loanInfo.getAmtrequest());
			preparedStatement.setString(3, loanInfo.getDoa());
			preparedStatement.setString(4, loanInfo.getBstructure());
			preparedStatement.setString(5, loanInfo.getBindicator());
			preparedStatement.setString(6, loanInfo.getMobile());
			preparedStatement.setString(7, loanInfo.getEmail());

			int i = preparedStatement.executeUpdate();

			if (i != 0)
				return "SUCCESS";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Oops.. Something went wrong there..!";
	}

	@SuppressWarnings("null")
	public UserDto trackLoanApplication(String loanNumber) {
		UserDto userDto = null;
		try {
			ConnectionDao conn = UserController.getConnDao();
			Connection con = conn.connect();
			Statement stmt = null;
			String query = "select loanStatus from loanDB where loanNumber = " + loanNumber;
			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				userDto.setFirstName(resultSet.getString("fName"));
				userDto.setMiddleName(resultSet.getString("mName"));
				userDto.setLastName(resultSet.getString("lName"));
				userDto.setAge(resultSet.getString("age"));
				userDto.setEmail(resultSet.getString("email"));
				userDto.setMob(resultSet.getString("mob"));
				userDto.setOccupation(resultSet.getString("occupation"));
				userDto.setAddress(resultSet.getString("address"));
				userDto.setLoanStatus(resultSet.getString("loanStatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userDto;
	}

	public String editLoanApplication(UserDto userDto) {
		try {
			ConnectionDao conn = UserController.getConnDao();
			Connection con = conn.connect();
			Statement stmt = null;
			String query = "update users set fName = " + userDto.getFirstName() + ",mName = " + userDto.getMiddleName()
					+ ",lName = " + userDto.getLastName() + ",mob = " + userDto.getMob() + ",age = " + userDto.getAge()
					+ ",email = " + userDto.getEmail() + ",address = " + userDto.getAddress() + ",occupation = "
					+ userDto.getOccupation();
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String checkLoanStatus(String loanNumber) {
		String status = null;
		try {
			ConnectionDao conn = UserController.getConnDao();
			Connection con = conn.connect();
			Statement stmt = null;
			String query = "select loanStatus from loanDB where loanNumber = " + loanNumber;
			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				status = resultSet.getString("loanStatus");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}