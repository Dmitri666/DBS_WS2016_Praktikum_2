package com.watchandchill.table.concerns;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActorsOnConcerns extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT Benutzername AS  \"Schauspieler\" ,Bezeichnung AS  \"Medienkonzern\" FROM Steht_unter_Vertrag ";
        if ( filter != null && ! filter .isEmpty() )
        {
            selectQuery += " WHERE Benutzername LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT Benutzername AS  \"Schauspieler\" ,Bezeichnung AS  \"Medienkonzern\" FROM Steht_unter_Vertrag " + "" +
                " WHERE Bezeichnung = '" + data.get("Steht_unter_Vertrag.Medienkonzern") + "' AND Benutzername = '"  + data.get("Steht_unter_Vertrag.Schauspieler") + "'" ;

    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("INSERT INTO Steht_unter_Vertrag(Benutzername, Bezeichnung) VALUES (?, ?)");
        preparedStatement.setObject(1, data.get("Steht_unter_Vertrag.Schauspieler"));
        preparedStatement.setObject(2, data.get("Steht_unter_Vertrag.Medienkonzern"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("UPDATE Steht_unter_Vertrag SET Benutzername = ?, Bezeichnung = ? WHERE Benutzername = ? AND Bezeichnung = ?");
        preparedStatement.setObject(1, newData.get("Steht_unter_Vertrag.Schauspieler"));
        preparedStatement.setObject(2, newData.get("Steht_unter_Vertrag.Medienkonzern"));
        preparedStatement.setObject(3, oldData.get("Steht_unter_Vertrag.Schauspieler"));
        preparedStatement.setObject(4, oldData.get("Steht_unter_Vertrag.Medienkonzern"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("DELETE FROM Steht_unter_Vertrag WHERE Benutzername = ? AND Bezeichnung = ?");
        preparedStatement.setObject(1, data.get("Steht_unter_Vertrag.Schauspieler"));
        preparedStatement.setObject(2, data.get("Steht_unter_Vertrag.Medienkonzern"));
        preparedStatement.executeUpdate();
    }
}
