package com.watchandchill.gui;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alexanderthelen.applicationkit.database.Data;
import com.watchandchill.Application;

public class AuthenticationViewController extends com.alexanderthelen.applicationkit.gui.AuthenticationViewController {
	public static AuthenticationViewController createWithName(String name) throws IOException {
		AuthenticationViewController viewController = new AuthenticationViewController(name);
		viewController.loadView();
		return viewController;
	}

	protected AuthenticationViewController(String name) {
		super(name);
	}

	@Override
	public void loginUser(Data data) throws SQLException {
		//throw new SQLException("AuthenticationViewController.loginUser(Data) nicht implementiert.");
	}

	@Override
	public void registerUser(Data data) throws SQLException {
		throw new SQLException("AuthenticationViewController.registerUser(Data) nicht implementiert.");
	}
}
