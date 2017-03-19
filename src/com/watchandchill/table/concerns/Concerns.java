package com.watchandchill.table.concerns;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Concerns extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT Bezeichnung as Bezeichnung FROM Medienkonzern";
        if ( filter != null && ! filter .isEmpty() )
        {
            selectQuery += " WHERE Bezeichnung LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT Bezeichnung AS  \"Bezeichnung von Medienkonzern\" FROM Medienkonzern  WHERE Bezeichnung = '" + data.get("Medienkonzern.Bezeichnung") + "'";
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        PreparedStatement pstmt = Application.getInstance().getConnection().prepareStatement("INSERT INTO Medienkonzern(Bezeichnung) VALUES(?)");
        pstmt.setObject(1, data.get("Medienkonzern.Bezeichnung von Medienkonzern"));
        pstmt.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("UPDATE Medienkonzern SET Bezeichnung = ? WHERE Bezeichnung = ?");
        preparedStatement.setObject(1, newData.get("Medienkonzern.Bezeichnung von Medienkonzern"));
        preparedStatement.setObject(2, oldData.get("Medienkonzern.Bezeichnung"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().
                prepareStatement("DELETE FROM Medienkonzern WHERE Bezeichnung = ?");
        preparedStatement.setObject (1 , data.get("Medienkonzern.Bezeichnung"));
        preparedStatement.executeUpdate () ;
    }
}
