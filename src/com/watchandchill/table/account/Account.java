package com.watchandchill.table.account;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Account extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT Benutzername as \"Premiumnutzer\" FROM Premium_Nutzer WHERE Benutzername NOT IN (SELECT Benutzername FROM Schauspieler )  ";
        if ( filter != null && ! filter .isEmpty() )
        {
            selectQuery += " AND Benutzername LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT Benutzername as \"Premiumnutzer\"  FROM Premium_Nutzer   WHERE Benutzername NOT IN (SELECT Benutzername FROM Schauspieler ) AND Benutzername = '" + data.get("Premium_Nutzer.Premiumnutzer") + "'";
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        String angemeldeteUser = Application.getInstance().getData().get("username").toString();
        if ((Integer) Application.getInstance().getData().get("permission") != 2) {
            throw new SQLException("Nicht die notwendigen Rechte.");
        } else if (!angemeldeteUser.equals(data.get("Premium_Nutzer.Premiumnutzer"))) {
            throw new SQLException("Nicht der gleiche Nutzer.");
        }

        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("INSERT INTO Premium_Nutzer(Benutzername) VALUES (?)");
        preparedStatement.setString(1, angemeldeteUser);

        preparedStatement.executeUpdate();
        Application.getInstance().getData().replace("permission",1);
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        throw new SQLException(getClass().getName() + ".updateRowWithData(Data, Data) nicht implementiert.");
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        if ((Integer) Application.getInstance().getData().get("permission") != 1) {
            throw new SQLException("Nicht die notwendigen Rechte.");
        } else if (!Application.getInstance().getData().get("username").equals(data.get("Premium_Nutzer.Premiumnutzer"))) {
            throw new SQLException("Nicht der gleiche Nutzer.");
        }
        PreparedStatement preparedStatement = Application.getInstance().getConnection().
                prepareStatement("DELETE FROM Premium_Nutzer WHERE Benutzername = ?");
        preparedStatement.setObject (1 , data.get("Premium_Nutzer.Premiumnutzer"));
        preparedStatement.executeUpdate () ;
        Application.getInstance().getData().replace("permission",2);
    }
}
