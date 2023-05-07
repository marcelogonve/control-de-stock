package com.alura.jdbc.dao;

import com.alura.jdbc.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    final private Connection conn;

    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
		try {
			StringBuilder updateQuery = new StringBuilder();
			updateQuery.append("UPDATE producto SET nombre = ?, descripcion = ?, ");
			updateQuery.append("cantidad = ? WHERE id = ?");

			final PreparedStatement ps = conn.prepareStatement(updateQuery.toString());

			try(ps) {
				ps.setString(1, nombre);
				ps.setString(2, descripcion);
				ps.setInt(3, cantidad);
				ps.setInt(4, id);
				ps.execute();
				return ps.getUpdateCount();
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			String deleteQuery = "DELETE FROM producto WHERE id = ?";
			final PreparedStatement ps = conn.prepareStatement(deleteQuery);

			try(ps) {
				ps.setInt(1, id);
				ps.execute();
				return ps.getUpdateCount();
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Producto> listar() {
		List<Producto> resultado = new ArrayList<>();
		try {
			String selectQuery = "SELECT id, nombre, descripcion, cantidad FROM producto";
			final PreparedStatement ps = conn.prepareStatement(selectQuery);

			try(ps) {
				ps.execute();

				final ResultSet rs = ps.getResultSet();
				try(rs) {
					while(rs.next()) {
						Producto fila = new Producto(rs.getInt("id"),
										rs.getString("nombre"),
										rs.getString("descripcion"),
										rs.getInt("cantidad"));
	
						resultado.add(fila);
					}
				}
			}
			return resultado;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

    public void guardar(Producto producto) {
		try {
			StringBuilder insertQuery = new StringBuilder();
			insertQuery.append("INSERT INTO producto(nombre, descripcion, cantidad) ");
			insertQuery.append("VALUES(?, ?, ?)");
			final PreparedStatement ps = conn.prepareStatement(insertQuery.toString(), Statement.RETURN_GENERATED_KEYS);
			
			try(ps) {
				executeSave(producto, ps);
				conn.commit();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
