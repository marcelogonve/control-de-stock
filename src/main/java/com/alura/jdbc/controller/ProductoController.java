package com.alura.jdbc.controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;

public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<Map<String, String>> listar() throws SQLException {
		// Se crea la conexión a la base de datos MyQSL
		// El primer parámetro es el enlace de conexión
		// El segundo corresponde al usuario
		// Y el tercero a la autenticación. En este caso es null porque no hay contraseña
		Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
			"root", null);

		Statement st = con.createStatement();
		st.execute("SELECT id, nombre, descripcion, cantidad FROM producto");
		ResultSet rs = st.getResultSet();
		List<Map<String, String>> resultado = new ArrayList<>();
		while(rs.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("id", String.valueOf(rs.getInt("id")));
			fila.put("nombre", rs.getString("nombre"));
			fila.put("descripcion", rs.getString("descripcion"));
			fila.put("cantidad", String.valueOf(rs.getInt("cantidad")));
			resultado.add(fila);
		}
		
		con.close(); // Se cierra la conexión

		return resultado;
	}

    public void guardar(Object producto) {
		// TODO
	}

}
