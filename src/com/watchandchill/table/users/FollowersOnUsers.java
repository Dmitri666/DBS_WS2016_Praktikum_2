package com.watchandchill.table.users;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;

import java.sql.SQLException;

public class FollowersOnUsers extends Table {
    @Override
    public String getSelectQueryForTableWithFilter(String filter) throws SQLException {
        String selectQuery = "SELECT Benutzername AS \"Premiumnutzer\",Benutzername1 AS \"Folgt Premiumnutzer\" FROM Folgt";
        if ( filter != null && ! filter .isEmpty() )
        {
            selectQuery += " WHERE Benutzername LIKE '%" + filter + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        return null;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");
    }

    @Override
    public void updateRowWithData(Data oldData, Data newData) throws SQLException {
        throw new SQLException(getClass().getName() + ".updateRowWithData(Data, Data) nicht implementiert.");
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        throw new SQLException(getClass().getName() + ".deleteRowWithData(Data) nicht implementiert.");
    }
}
