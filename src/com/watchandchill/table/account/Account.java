package com.watchandchill.table.account;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Account extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT Benutzername FROM Premium_Nutzer";
        if ( filter != null && ! filter .isEmpty() )
        {
            selectQuery += " WHERE Benutzername LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT Benutzername  FROM Premium_Nutzer  WHERE Benutzername = '" + data.get("Premium_Nutzer.Benutzername") + "'";
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        if ((Integer) Application.getInstance().getData().get("permission") != 2) {
            throw new SQLException("Nicht die notwendigen Rechte.");
        } else if (!Application.getInstance().getData().get("username").equals(data.get("Premium_Nutzer.Benutzername"))) {
            throw new SQLException("Nicht der gleiche Nutzer.");
        }

        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("INSERT INTO Premium_Nutzer(Benutzername) VALUES (?)");
        preparedStatement.setObject(1, data.get(Application.getInstance().getData().get("username")));

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        throw new SQLException(getClass().getName() + ".updateRowWithData(Data, Data) nicht implementiert.");
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        if ((Integer) Application.getInstance().getData().get("permission") != 1) {
            throw new SQLException("Nicht die notwendigen Rechte.");
        } else if (!Application.getInstance().getData().get("username").equals(data.get("Premium_Nutzer.Benutzername"))) {
            throw new SQLException("Nicht der gleiche Nutzer.");
        }
        PreparedStatement preparedStatement = Application.getInstance().getConnection().
                prepareStatement("DELETE FROM Premium_Nutzer WHERE Benutzername = ?");
        preparedStatement.setObject (1 , data.get("Premium_Nutzer.Benutzername"));
        preparedStatement.executeUpdate () ;
    }
}
