package com.watchandchill.table.serials;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeasonsOnSerials extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT ID,Staffelnummer,SerieID FROM Staffel";
        if ( filter != null && ! filter .isEmpty() )
        {
            selectQuery += " WHERE ID LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT ID,Staffelnummer,SerieID FROM Staffel  WHERE ID = " + data.get("Staffel.ID") ;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        PreparedStatement pstmt = Application.getInstance().getConnection().prepareStatement("INSERT INTO Staffel(Staffelnummer,SerieID) VALUES(?,?)");
        pstmt.setObject(1, data.get("Staffel.Staffelnummer"));
        pstmt.setObject(2, data.get("Staffel.SerieID"));
        pstmt.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("UPDATE Staffel SET Staffelnummer = ?,SerieID = ? WHERE ID = ?");
        preparedStatement.setObject(1, newData.get("Staffel.Staffelnummer"));
        preparedStatement.setObject(2, newData.get("Staffel.SerieID"));
        preparedStatement.setObject(2, oldData.get("Staffel.ID"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().
                prepareStatement("DELETE FROM Staffel WHERE ID = ?");
        preparedStatement.setObject (1 , data.get("Staffel.ID"));
        preparedStatement.executeUpdate () ;
    }
}
