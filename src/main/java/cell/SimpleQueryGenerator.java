/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cell;


/**
 *
 * @author murilo
 */
public class SimpleQueryGenerator implements QueryGenerator<Entity>{
    
    @Override
    public String insertGenerator(Entity e) {
        //INSERT INTO() VALUES();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(e.getTableName()).append('(');
        /**
         * @WhatDo: add the columns that will be inserted.
         */
        for (int i = e.haveAutoIncrementID() ? 1 : 0; i < e.getNumOfColumns(); i++) {
            sql.append(e.getColumnName(i));
            if (i < e.getNumOfColumns() - 1) {
                sql.append(",");
            }
        }
        /**
         * @WhatItIsDoing: adding how many values will be altered.
         */
        sql.append(") VALUES(");
        for (int i = e.haveAutoIncrementID() ? 1 : 0; i < e.getNumOfColumns(); i++) {
            sql.append("?");
            if (i < e.getNumOfColumns() - 1) {
                sql.append(",");
            }
        }
        sql.append(");");
        return sql.toString();
    }

    @Override
    public String deleteGenerator(Entity e) {
        //DELETE FROM [TABLE] WHERE ID = ?;
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").
                append(e.getTableName()).
                append(" WHERE ").
                append(e.getColumnName(0)).
                append(" = ").
                append((String) e.getCell(0).getValue()).
                append(";");
        return sql.toString();
    }

    @Override
    public String updateGenerator(Entity e) {
        //UPDATE [TABLE] SET COL1 = ?, COL2 = ? ...;
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").
                append(e.getTableName()).
                append(" SET ");
        for (int i = e.haveAutoIncrementID() ? 1 : 0; i < e.getNumOfColumns(); i++) {
            sql.append(e.getColumnName(i)).append(" = ?");
            if (i < e.getNumOfColumns() - 1) 
                sql.append(", ");
            
        }
        sql.append(" WHERE = ").
        append(e.getCell(0)).
        append(";");
        System.out.println();
        return sql.toString();
    }

    @Override
    public String selectGenerator(Entity e) {
        StringBuilder sql = new StringBuilder();
        return sql.toString();
    }
}
