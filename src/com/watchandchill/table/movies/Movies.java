package com.watchandchill.table.movies;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

public class Movies extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT Bezeichnung as \"Title\",Produktionsbudget FROM Film";
        if ( filter != null && ! filter .isEmpty() )
        {
            selectQuery += " WHERE Bezeichnung LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT Bezeichnung AS  \"Title\", Produktionsbudget as \"Produktionsbudget\" FROM Film  WHERE Bezeichnung = '" + data.get("Film.Title") + "'";
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        PreparedStatement pstmt = Application.getInstance().getConnection().prepareStatement("INSERT INTO Film(Bezeichnung,Produktionsbudget) VALUES(?,?)");
        pstmt.setObject(1, data.get("Film.Title"));
        pstmt.setObject(2, data.get("Film.Produktionsbudget"));
        pstmt.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("UPDATE Film SET Bezeichnung = ?,Produktionsbudget = ? WHERE Bezeichnung = ?");
        preparedStatement.setObject(1, newData.get("Film.Title"));
        preparedStatement.setObject(2, newData.get("Film.Produktionsbudget"));
        preparedStatement.setObject(3, oldData.get("Film.Title"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().
                prepareStatement("DELETE FROM Film WHERE Bezeichnung = ?");
        preparedStatement.setObject (1 , data.get("Film.Title"));
        preparedStatement.executeUpdate () ;
    }
}
