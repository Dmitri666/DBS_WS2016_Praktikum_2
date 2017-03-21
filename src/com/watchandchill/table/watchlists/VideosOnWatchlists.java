package com.watchandchill.table.watchlists;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VideosOnWatchlists extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT E.WatchlistId AS \"ID von Watchlist\" ,W.Bezeichnung ,E.Bezeichnung AS \"Video\" FROM Enthalt E " +
                ", Watchlist W WHERE W.ID = E.WatchlistId ";
        if (filter != null && !filter.isEmpty()) {
            selectQuery += " AND W.Bezeichnung LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT WatchlistId AS \"ID von Watchlist\", Bezeichnung AS \"Video\" FROM Enthalt WHERE WatchlistId = " + data.get("Enthalt.ID von Watchlist") +
                " AND Bezeichnung = '"+ data.get("Enthalt.Video") + "'";

    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("INSERT INTO Enthalt(Bezeichnung, WatchlistId) VALUES (?, ?)");
        preparedStatement.setObject(1, data.get("Enthalt.Video"));
        preparedStatement.setObject(2, data.get("Enthalt.ID von Watchlist"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("UPDATE Enthalt SET Bezeichnung = ?, WatchlistId = ? WHERE Bezeichnung = ? AND WatchlistId = ?");
        preparedStatement.setObject(1, newData.get("Enthalt.Video"));
        preparedStatement.setObject(2, newData.get("Enthalt.ID von Watchlist"));
        preparedStatement.setObject(3, oldData.get("Enthalt.Video"));
        preparedStatement.setObject(4, oldData.get("Enthalt.ID von Watchlist"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("DELETE FROM Enthalt WHERE Bezeichnung = ? AND WatchlistId = ?");
        preparedStatement.setObject(1, data.get("Enthalt.Video"));
        preparedStatement.setObject(2, data.get("Enthalt.ID von Watchlist"));
        preparedStatement.executeUpdate();
    }
}
