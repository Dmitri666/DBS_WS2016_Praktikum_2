package com.watchandchill.table.serials;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import com.watchandchill.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentsOnSeasons extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT ID,Kommentar,Benutzername as \"Premiumnutzer\",StaffelId FROM Staffel_Kommentar";
        if ( filter != null && ! filter .isEmpty() )
        {
            selectQuery += " WHERE ID LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return "SELECT ID,Kommentar,Benutzername as \"Premiumnutzer\",StaffelId FROM Staffel_Kommentar  WHERE ID = '" + data.get("Staffel_Kommentar.ID") + "'";
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        if ((Integer) Application.getInstance().getData().get("permission") > 1) {
            throw new SQLException("Nicht die notwendigen Rechte.");
        }
        PreparedStatement pstmt = Application.getInstance().getConnection().prepareStatement("INSERT INTO Staffel_Kommentar(Kommentar,Benutzername,StaffelId) VALUES(?,?,?)");
        pstmt.setObject(1, data.get("Staffel_Kommentar.Kommentar"));
        pstmt.setObject(2, Application.getInstance().getData().get("username"));
        pstmt.setObject(3, data.get("Staffel_Kommentar.StaffelId"));
        pstmt.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        if ((Integer) Application.getInstance().getData().get("permission") > 1) {
            throw new SQLException("Nicht die notwendigen Rechte.");
        }
        PreparedStatement preparedStatement = Application.getInstance().getConnection().prepareStatement("UPDATE Staffel_Kommentar SET Kommentar = ? WHERE ID = ?");
        preparedStatement.setObject(1, newData.get("Staffel_Kommentar.Kommentar"));
        preparedStatement.setObject(2, oldData.get("Staffel_Kommentar.ID"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        if ((Integer) Application.getInstance().getData().get("permission") > 1) {
            throw new SQLException("Nicht die notwendigen Rechte.");
        }
        PreparedStatement preparedStatement = Application.getInstance().getConnection().
                prepareStatement("DELETE FROM Staffel_Kommentar WHERE ID = ?");
        preparedStatement.setObject (1 , data.get("Staffel_Kommentar.ID"));
        preparedStatement.executeUpdate () ;
    }
}
