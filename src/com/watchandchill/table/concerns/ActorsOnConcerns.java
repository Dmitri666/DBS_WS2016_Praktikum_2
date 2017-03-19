package com.watchandchill.table.concerns;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;

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
        return "SELECT Genre_Bezeichnung AS  \"Bezeichnung von Genre\" ,Video_Bezeichnung AS  \"Bezeichnung von Video\" FROM Steht_unter_Vertrag " + "" +
                " WHERE Genre_Bezeichnung = '" + data.get("Steht_unter_Vertrag.Bezeichnung von Genre") + "' AND Video_Bezeichnung = '"  + data.get("Steht_unter_Vertrag.Bezeichnung von Video") + "'" ;

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
