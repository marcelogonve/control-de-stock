package com.alura.jdbc.modelo;

public class Producto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;

    public Producto() {
        
    }

    public Producto(String nombre, String descripcion, Integer cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return String.format("{id: %s, nombre: %s, descripcion: %s, cantidad: %d}",
                            this.id,
                            this.nombre,
                            this.descripcion,
                            this.cantidad);
    }
}
