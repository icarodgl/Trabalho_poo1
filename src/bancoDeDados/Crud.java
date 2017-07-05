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
import projetopoo.*;

/**
 *
 * @author icaro
 */
public class Crud {
    
    Connection con;
    
    public Crud() {
        con = Conector.getConnection();
    }
    
    public void create(Modelo m) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO modelo (nome, id, fk_dominio_id)VALUES(?, ?, ?)");
            stmt.setString(1, m.getNome());
            stmt.setInt(2, m.getId());
            stmt.setInt(3, m.getDominio().getId());
            stmt.executeUpdate();
            for (Regra r: m.getRegra()){
                stmt = con.prepareStatement("INSERT INTO modelo_regra (fk_modelo_id, fk_regra_id)VALUES(?, ?)");
                stmt.setInt(1, m.getId());
                stmt.setInt(2, r.getId());
                stmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }

    public List<Modelo> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        PreparedStatement stmt2 = null;
        ArrayList <Modelo> modelos = new ArrayList();
        try {
            stmt = con.prepareStatement("select	modelo.id as modelo_id,\n" +
                                    "	dominio.id as dominio_id,\n" +
                                    "	regra.id as regra_id,\n" +
                                    "	regra.tipo as regra_tipo,\n" +
                                    "	regra.ladoe as regra_ladoe,\n" +
                                    "	regra.ladod as regra_ladod,\n" +
                                    "	atividade.id as atividade_id,\n" +
                                    "	atividade.nome as atividade_nome,\n" +
                                    "	atividade.tid as atividade_tid,\n" +
                                    "	atividade.tipo as atividade_tipo,\n" +
                                    "	recurso.id as recurso_id,\n" +
                                    "	recurso.nome as recurso_nome,\n" +
                                    "	recurso.tipo as recurso_tipo,\n" +
                                    "	recurso.descricao as recurso_rescricao\n" +
                                    "	\n" +
                                    "\n" +
                                    "	from modelo \n" +
                                    "	inner join dominio on (modelo.fk_dominio_id = dominio.id)\n" +
                                    "	inner join modelo_regra as mr on(mr.fk_modelo_id = modelo.id)\n" +
                                    "	inner join regra on (mr.fk_regra_id = regra.id)\n" +
                                    "	inner join dominio_atividade as doa on (doa.fk_dominio_id = dominio.id)\n" +
                                    "	inner join atividade on (doa.fk_atividade_id = atividade.id)\n" +
                                    "	inner join atividade_recurso as ar on (ar.fk_atividade_id = atividade.id)\n" +
                                    "	inner join recurso on (ar.fk_recurso_id = recurso.id);");
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