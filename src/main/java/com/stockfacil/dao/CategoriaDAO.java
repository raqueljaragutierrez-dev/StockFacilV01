/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stockfacil.dao;
//hacemos los import
import com.stockfacil.config.Conexion;
import com.stockfacil.model.Categoria;

//hacemos import para la conexion con base d datos
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    //metodo q devuelve todas las categorias de la tabla
    public List<Categoria> lista()throws SQLException{
        String sql="SELECT * FROM categoria ORDER BY nombre";
        //guardamos en la lista
        List<Categoria> lista = new ArrayList<>();
        
        try (Connection con= Conexion.obtener();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery())
                {
                    while (rs.next()){//avanza a la siguiente fila
                        lista.add(mapear(rs));//llama metodo, 
                    }
                }
                return lista;
    }
    //NUEVO METODO QUE BUSCA CATEGORIAS POR NOMBRE
    //Busca caategorias por coincidencia parcil en el nombre, incluye tanto las categorias activas como las inactivas
     public List<Categoria> buscarPorNombre(String nombre)
            throws SQLException {

        String sql = "SELECT * FROM categoria "
                   + "WHERE nombre LIKE ? "
                   + "ORDER BY nombre";

        List<Categoria> lista = new ArrayList<>();//guarda todas las listas que tenemos

        try (Connection con = Conexion.obtener();//nos sirve para indicar si sucede algun error con la conexion
             PreparedStatement ps = con.prepareStatement(sql)) {//preparar la consulta que ya habia definido

            ps.setString(1, "%" + nombre + "%");//le damos las posiciones, 1 esta el nombre, y la concatenacion

            try (ResultSet rs = ps.executeQuery()) {//obtener el resultado 
                while (rs.next()) {//todo lo que leyo
                    lista.add(mapear(rs));//agregamos la lista
                }
            }
        }

        return lista;//aqui retorna la lista de todas las categorias que el usuario ingreso a buscar
    }
    //mapear carga todas las consultas
    private Categoria mapear(ResultSet rs )throws SQLException{
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("id_categoria"));
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setActivo(rs.getBoolean("activo"));
        return c;//retorna el objeto
    }
    
    public Categoria buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";

        try (Connection con = Conexion.obtener();

             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next() ? mapear(rs) : null;

            }

        }

    }

    /** RN-01: true si otro registro ya usa ese nombre. */

    public boolean existeNombre(String nombre, int idExcluir) throws SQLException {

        String sql = "SELECT COUNT(*) FROM categoria WHERE nombre = ? AND id_categoria <> ?";

        try (Connection con = Conexion.obtener();

             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);//devuelve nombre

            ps.setInt(2, idExcluir);

            try (ResultSet rs = ps.executeQuery()) {

                rs.next();

                return rs.getInt(1) > 0;

            }

        }

    }

    public void insertar(Categoria c) throws SQLException {

        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";

        try (Connection con = Conexion.obtener();

             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());

            ps.setString(2, c.getDescripcion());

            ps.executeUpdate();

        }

    }

    public void actualizar(Categoria c) throws SQLException {

        String sql = "UPDATE categoria SET nombre = ?, descripcion = ?, activo = ? WHERE id_categoria = ?";

        try (Connection con = Conexion.obtener();

             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());

            ps.setString(2, c.getDescripcion());

            ps.setBoolean(3, c.isActivo());

            ps.setInt(4, c.getIdCategoria());

            ps.executeUpdate();

        }

    }

    /** RN-05: no se borra, se desactiva. */

    public void desactivar(int id) throws SQLException {

        String sql = "UPDATE categoria SET activo = 0 WHERE id_categoria = ?";

        try (Connection con = Conexion.obtener();

             PreparedStatement ps = con.prepareStatement(sql)) {//se prepara la consulta

            ps.setInt(1, id);//se guarda

            ps.executeUpdate();

        }

    }
 
    }
    
    

