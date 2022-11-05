package com.example.laboratorio8.model.daos;

import com.example.laboratorio8.model.beans.Heroes;

import java.sql.*;
import java.util.ArrayList;

public class DaoHeroes {

    public ArrayList<Heroes> obtenerlistaHeroes(){
        ArrayList<Heroes> listaHeroes = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM mydb.heroes";

        try(Connection connection = DriverManager.getConnection(url,"root","123456");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Heroes heroes = new Heroes();
                heroes.setIdHeroes(rs.getInt(1));
                heroes.setNombre(rs.getString(2));
                heroes.setEdad(rs.getString(3));
                heroes.setGenero(rs.getString(4));
                heroes.setClase(rs.getString(5));
                heroes.setNivel_inicial(rs.getString(6));
                heroes.setAtaque(rs.getString(7));
                heroes.setPareja(rs.getString(8));
                listaHeroes.add(heroes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaHeroes;
    }

    public Heroes buscarPorId(String idHeroe){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM mydb WHERE idHeroes = ?";
        Heroes heroes = null;

        try(Connection conn = DriverManager.getConnection(url, "root", "123456");
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idHeroe);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    heroes = new Heroes();

                    heroes.setIdHeroes(rs.getInt(1));
                    heroes.setNombre(rs.getString(2));
                    heroes.setEdad(rs.getString(3));
                    heroes.setGenero(rs.getString(4));
                    heroes.setClase(rs.getString(5));
                    heroes.setNivel_inicial(rs.getString(6));
                    heroes.setAtaque(rs.getString(7));
                    heroes.setPareja(rs.getString(8));
                }
            }

        }
        catch (SQLException e){
            throw new RuntimeException();
        }

        return heroes;
    }

    public void guardarHeroes(Heroes heroes){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "INSERT INTO usuarios (nombre,edad,genero,clase,nivel_inicial,ataque,pareja) VALUES (?,?,?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,"root","123456");
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,heroes.getNombre());
            pstmt.setString(2,heroes.getEdad());
            pstmt.setString(3,heroes.getGenero());
            pstmt.setString(4,heroes.getClase());
            pstmt.setString(5,heroes.getNivel_inicial());
            pstmt.setString(6,heroes.getAtaque());
            pstmt.setString(7,heroes.getPareja());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarHeroes(String IdHeroes, String nombre1,String edad1,String genero1,String nivel1,String clase1,String ataque1,String pareja1) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String sql = "UPDATE mydb.heroes SET nombre = ?, edad = ?, genero = ?, nivel = ?, clase = ?, ataque = ?, pareja = ? WHERE idHeroes = ?";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(8, IdHeroes);
            pstmt.setString(1, nombre1);
            pstmt.setString(2, edad1);
            pstmt.setString(3, genero1);
            pstmt.setString(4, nivel1);
            pstmt.setString(5, clase1);
            pstmt.setString(6, ataque1);
            pstmt.setString(7, pareja1);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void borrar(String idHeroe){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "DELETE from mydb.heroes WHERE idHeroes = ?";

        try(Connection connection = DriverManager.getConnection(url,"root","123456");
            PreparedStatement pstmt=connection.prepareStatement(sql))
        {

            pstmt.setString(1,idHeroe);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
