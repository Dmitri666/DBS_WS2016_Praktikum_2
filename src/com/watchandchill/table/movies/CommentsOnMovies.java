package com.watchandchill.table.movies;

import java.sql.SQLException;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;

public class CommentsOnMovies extends Table {
	@Override
	public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
		return null;
	}

	@Override
	public String getSelectQueryForRowWithData(Data data) throws SQLException {
		return null;
	}

	@Override
	public void insertRowWithData(Data data) throws SQLException {
		throw new SQLException("CommentsOnMovies.insertRowWithData(Data) nicht implementiert.");
	}

	@Override
	public void updateRowWithData(Data oldData, Data newData) throws SQLException {
		throw new SQLException("CommentsOnMovies.updateRowWithData(Data, Data) nicht implementiert.");
	}

	@Override
	public void deleteRowWithData(Data data) throws SQLException {
		throw new SQLException("CommentsOnMovies.deleteRowWithData(Data) nicht implementiert.");
	}
}
