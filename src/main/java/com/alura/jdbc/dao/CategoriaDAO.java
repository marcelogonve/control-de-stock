package com.alura.jdbc.dao;

import com.alura.jdbc.modelo.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection conn;

    public CategoriaDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Categoria> listar() {
        List<Categoria> result = new ArrayList<>();

        try {
            final PreparedStatement st = conn.prepareStatement("SELECT id, nombre FROM categoria");
            try(st) {
                final ResultSet rs = st.executeQuery();
                try(rs) {
                    while(rs.next()) {
                        var categoria = new Categoria(rs.getInt("id"), rs.getString("nombre"));
                        result.add(categoria);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
