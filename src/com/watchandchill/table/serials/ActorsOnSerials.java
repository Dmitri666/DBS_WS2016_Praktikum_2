package com.watchandchill.table.serials;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActorsOnSerials extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT SerieId AS \"ID von Serie\",Benutzername AS \"Schauspieler\" FROM Wirkt_mit_Serie ";
        if (filter != null && !filter.isEmpty()) {
            selectQuery += " SerieId LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT SerieId AS \"ID von Serie\",Benutzername AS \"Schauspieler\" " + "" +
                " WHERE SerieId = '" + data.get("Wirkt_mit_Serie.ID von Serie") + "' AND Benutzername = '"  + data.get("Wirkt_mit_Serie.Schauspieler") + "'" ;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("INSERT INTO Wirkt_mit_Serie(SerieId, Benutzername) VALUES (?, ?)");
        preparedStatement.setObject(1, data.get("Wirkt_mit_Serie.ID von Serie"));
        preparedStatement.setObject(2, data.get("Wirkt_mit_Serie.Schauspieler"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("UPDATE Wirkt_mit_Serie SET SerieId = ?, Benutzername = ? WHERE SerieId = ? AND Benutzername = ?");
        preparedStatement.setObject(1, newData.get("Wirkt_mit_Serie.ID von Serie"));
        preparedStatement.setObject(2, newData.get("Wirkt_mit_Serie.Schauspieler"));
        preparedStatement.setObject(3, oldData.get("Wirkt_mit_Serie.ID von Serie"));
        preparedStatement.setObject(4, oldData.get("Wirkt_mit_Serie.Schauspieler"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("DELETE FROM Wirkt_mit_Serie WHERE SerieId = ? AND Benutzername = ?");
        preparedStatement.setObject(1, data.get("Wirkt_mit_Serie.ID von Serie"));
        preparedStatement.setObject(2, data.get("Wirkt_mit_Serie.Schauspieler"));
        preparedStatement.executeUpdate();
    }
}
