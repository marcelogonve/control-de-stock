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
import com.alura.jdbc.modelo.Producto;

public class ProductoController {

	public int modificar(String nombre, String descripcion, Integer id) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		final Connection conn = factory.getConnection();

		try(conn) {
			StringBuilder updateQuery = new StringBuilder();
			updateQuery.append("UPDATE producto SET nombre = ?, descripcion = ? ");
			updateQuery.append("WHERE id = ?");

			final PreparedStatement ps = conn.prepareStatement(updateQuery.toString());

			try(ps) {
				ps.setString(1, nombre);
				ps.setString(2, descripcion);
				ps.setInt(3, id);
				ps.execute();
				return ps.getUpdateCount();
			}
		}
	}

	public int eliminar(Integer id) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		final Connection conn = factory.getConnection();

		try(conn) {
			String deleteQuery = "DELETE FROM producto WHERE id = ?";
			final PreparedStatement ps = conn.prepareStatement(deleteQuery);

			try(ps) {
				ps.setInt(1, id);
				ps.execute();
				return ps.getUpdateCount();
			}
		}		
	}

	public List<Map<String, String>> listar() throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		final Connection conn = factory.getConnection();

		try(conn) {
			String selectQuery = "SELECT id, nombre, descripcion, cantidad FROM producto";
			final PreparedStatement ps = conn.prepareStatement(selectQuery);

			try(ps) {
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
				return resultado;
			}
		}		
	}

    public void guardar(Producto producto) throws SQLException {

		ConnectionFactory factory = new ConnectionFactory();
		final Connection conn = factory.getConnection();

		try(conn) {
			conn.setAutoCommit(false);

			StringBuilder insertQuery = new StringBuilder();
			insertQuery.append("INSERT INTO producto(nombre, descripcion, cantidad) ");
			insertQuery.append("VALUES(?, ?, ?)");
			final PreparedStatement ps = conn.prepareStatement(insertQuery.toString(), Statement.RETURN_GENERATED_KEYS);
			
			try(ps) {
				executeSave(producto, ps);
				conn.commit();			
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

	/**
	 * Ejecuta un insert con los parámetros recibidos
	 * @param producto Recibe el objeto producto, del cual se extraerán los parámetros necesarios para el insert
	 * @param conn Recibe la conexión a la base de datos
	 */
	private void executeSave(Producto producto, PreparedStatement ps) throws SQLException {
		
		ps.setString(1, producto.getNombre());
		ps.setString(2, producto.getDescripcion());
		ps.setInt(3, producto.getCantidad());

		ps.execute();

		final ResultSet rs = ps.getGeneratedKeys();

		try(rs) {
			while(rs.next()) {
				producto.setId(rs.getInt(1));
				System.out.println(String.format("Fue insertado el producto %s", producto));
			}
		}
	}

}
