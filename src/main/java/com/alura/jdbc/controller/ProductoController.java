package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;

public class ProductoController {

	public int modificar(String nombre, String descripcion, Integer id) throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();

		StringBuilder updateQuery = new StringBuilder();
		updateQuery.append("UPDATE producto SET nombre = ?, descripcion = ? ");
		updateQuery.append("WHERE id = ?");

		PreparedStatement st = conn.prepareStatement(updateQuery.toString());
		st.setString(1, nombre);
		st.setString(2, descripcion);
		st.setInt(3, id);
		st.execute();

		conn.close();

		return st.getUpdateCount();
	}

	public int eliminar(Integer id) throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();

		String deleteQuery = "DELETE FROM producto WHER id = ?";
		PreparedStatement st = conn.prepareStatement(deleteQuery);
		st.setInt(1, id);
		st.execute();

		conn.close();

		return st.getUpdateCount();
	}

	public List<Map<String, String>> listar() throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();

		String selectQuery = "SELECT id, nombre, descripcion, cantidad FROM producto";
		PreparedStatement ps = conn.prepareStatement(selectQuery);
		ps.execute();

		ResultSet rs = ps.getResultSet();
		List<Map<String, String>> resultado = new ArrayList<>();
		while(rs.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("id", String.valueOf(rs.getInt("id")));
			fila.put("nombre", rs.getString("nombre"));
			fila.put("descripcion", rs.getString("descripcion"));
			fila.put("cantidad", String.valueOf(rs.getInt("cantidad")));
			resultado.add(fila);
		}
		
		conn.close(); // Se cierra la conexión

		return resultado;
	}

    public void guardar(Map<String, String> producto) throws SQLException {
		String nombre = producto.get("NOMBRE");
		String descripcion = producto.get("DESCRIPCION");
		Integer cantidad = Integer.valueOf(producto.get("CANTIDAD"));
		Integer cantidadMaxima = 50;

		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.getConnection();
		conn.setAutoCommit(false);

		StringBuilder insertQuery = new StringBuilder();
		insertQuery.append("INSERT INTO producto(nombre, descripcion, cantidad) ");
		insertQuery.append("VALUES(?, ?, ?)");
		PreparedStatement ps = conn.prepareStatement(insertQuery.toString(), Statement.RETURN_GENERATED_KEYS);
		
		try {
			while(cantidad > 0) {
				int cantidadAGuardar = Math.min(cantidad, cantidadMaxima);
				executeSave(nombre, descripcion, cantidadAGuardar, ps);
				cantidad -= cantidadMaxima;
			}
			conn.commit();			
		} catch (Exception e) {
			conn.rollback();
		}
		
		ps.close();
		conn.close();
	}

	/**
	 * Ejecuta un insert con los parámetros recibidos
	 * @param nombre Recibe el nombre del producto
	 * @param descripcion Recibe la descripcion del producto
	 * @param cantidad Recibe la cantidad disponible del producto
	 * @param conn Recibe la conexión a la base de datos
	 * @param insertQuery Recibe el query a ser ejecutado
	 */
	private void executeSave(String nombre, String descripcion, Integer cantidad,
							PreparedStatement ps) throws SQLException {
		
		ps.setString(1, nombre);
		ps.setString(2, descripcion);		
		ps.setInt(3, cantidad);

		ps.execute();

		ResultSet rs = ps.getGeneratedKeys();
		while(rs.next()) {
			System.out.println(String.format("Fue insertado el producto con ID: %d", rs.getInt(1)));
		}
	}

}
