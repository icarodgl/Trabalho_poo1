/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JOptionPane;
import projetopoo.Modelo;
import projetopoo.Regra;

/**
 *
 * @author icaro
 */
public class CrudRegras {
    
    Connection con;
    
    public CrudRegras() {
        con = Conector.getConnection();
    }
    
    public void create(Regra m) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO regra (tipo, esquerda,direita)VALUES(?, ?, ?)");
            stmt.setString(1, m.getTipo());
            stmt.setInt(2, m.getLadoE());
            stmt.setInt(3, m.getLadoD());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }

    public List<Modelo> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Modelo> modelos = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM modelo");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Modelo modelo = new Modelo();

                modelo.setId(rs.getInt("id"));
                modelo.setNome(rs.getString("nome"));
                modelos.add(modelo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        } finally {
            Conector.closeConnection(con, stmt, rs);
        }

        return modelos;

    }
    

    public void update(Modelo m) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE modelo SET nome = ? WHERE id = ?");
            stmt.setString(1, m.getNome());
            stmt.setInt(2, m.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }
    public void delete(Modelo p) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM modelo WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }

}
