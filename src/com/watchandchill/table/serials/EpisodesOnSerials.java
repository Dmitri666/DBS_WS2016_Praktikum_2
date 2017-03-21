package com.watchandchill.table.serials;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EpisodesOnSerials extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT Bezeichnung,StaffelId FROM Folge";
        if ( filter != null && ! filter .isEmpty() )
        {
            selectQuery += " WHERE Bezeichnung LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT Bezeichnung,StaffelId FROM Folge  WHERE Bezeichnung = " + data.get("Folge.Bezeichnung") ;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        PreparedStatement pstmt = Application.getInstance().getConnection().prepareStatement("INSERT INTO Folge(Bezeichnung,StaffelId) VALUES(?,?)");
        pstmt.setObject(1, data.get("Folge.Bezeichnung"));
        pstmt.setObject(2, data.get("Folge.StaffelId"));
        pstmt.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("UPDATE Folge SET Bezeichnung = ?,StaffelId = ? WHERE Bezeichnung = ?");
        preparedStatement.setObject(1, newData.get("Folge.Bezeichnung"));
        preparedStatement.setObject(2, newData.get("Folge.StaffelId"));
        preparedStatement.setObject(2, oldData.get("Folge.Bezeichnung"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        PreparedStatement preparedStatement = Application.getInstance().getConnection().
                prepareStatement("DELETE FROM Folge WHERE Bezeichnung = ?");
        preparedStatement.setObject (1 , data.get("Folge.Bezeichnung"));
        preparedStatement.executeUpdate () ;
    }
}
