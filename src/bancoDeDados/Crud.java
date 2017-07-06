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
    public void ligaModeloRegras(Modelo m){
        PreparedStatement stmt = null;
    try{
    for (Regra r: m.getRegra()){
                stmt = con.prepareStatement("INSERT INTO modelo_regra (fk_modelo_id, fk_regra_id)VALUES(?, ?)");
                stmt.setInt(1, m.getId());
                stmt.setInt(2, r.getId());
                stmt.executeUpdate();
                
            }
    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }
    }
    /**
     *
     * @param d
     * liga o modelo a suas atividades
     */
    public void CriaDominio(Dominio d){
        PreparedStatement stmt = null;
        ArrayList<Atividade> at;
        at = d.getAtividades();
        
    try{
            for (Atividade a : at) {
                stmt = con.prepareStatement("INSERT INTO dominio (fk_atividade_id, fk_modelo_id)VALUES(?, ?)");
                stmt.setInt(1, a.getId());
                stmt.setInt(2, d.getId());
                stmt.executeUpdate();
            }
    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }
    }
    public void create(Regra r) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO regra (tipo, ladoe, ladod)VALUES( ?, ?, ?)");
            stmt.setString(1, r.getTipo());
            stmt.setString(2, r.getLadoE());
            stmt.setString(3, r.getLadoD());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }
    public void create(Modelo m) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO modelo (nome)VALUES(?)");
            stmt.setString(1, m.getNome());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }
    }
    /**
     *
     * @param a
     * passar a atividade com o array de seus recursos
     */
    public void alocaRecurso(Atividade a) {
        PreparedStatement stmt = null;

        try {
            for (Recurso r: a.getRecursos()){
                stmt = con.prepareStatement("INSERT INTO atividade_recurso ( fk_atividade_id, fk_recurso_id)VALUES( ?, ?)");
                stmt.setInt(1, a.getId());
                stmt.setInt(2, r.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }
    public void create(Atividade a) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO atividade ( nome, tid, tipo)VALUES( ?, ?, ?)");
            stmt.setString(1, a.getNome());
            stmt.setInt(2, 0);
            stmt.setString(3, a.getTipo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }
    public void create(Recurso r) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO recurso (nome, tipo, descricao )VALUES( ?,?,?)");
            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTipo());
            stmt.setString(3, r.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }
    public List<Recurso> listaRecurso() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList <Recurso> recursos = new ArrayList();
        try {
            stmt = con.prepareStatement("select	* from recurso");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setId(rs.getInt("id"));
                recurso.setNome(rs.getString("nome"));
                recurso.setTipo(rs.getString("tipo"));
                recurso.setDescricao(rs.getString("descricao"));
                recursos.add(recurso);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        } finally {
            Conector.closeConnection(con, stmt, rs);
        }

        return recursos;

    }
    /**
     *
     * @param id da atividade
     * @return lista de recursos para inserir na atividade
     */
    public List<Recurso> carregaRecursoAtividade(int id){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList <Recurso> recursos = new ArrayList();
        try {
            stmt = con.prepareStatement("select	* from atividade_recurso \n" +
                                        "natural join recurso\n" +
                                        "where (fk_atividade_id = ?);");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Recurso r = new Recurso();
                r.setId(rs.getInt("id"));
                r.setNome(rs.getString("nome"));
                r.setTipo(rs.getString("tipo"));
                r.setDescricao(rs.getString("descricao"));
                recursos.add(r);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        } finally {
            Conector.closeConnection(con, stmt, rs);
        }

        return recursos;
        
    
    }
    public List<Atividade> listaAtividade() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList <Atividade> atividades = new ArrayList();
        try {
            stmt = con.prepareStatement("select	* from atividade");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Atividade a = new Atividade();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setTipo(rs.getString("tipo"));
                a.setRecursos((Recurso) carregaRecursoAtividade(a.getId()));
                atividades.add(a);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        } finally {
            Conector.closeConnection(con, stmt, rs);
        }

        return atividades;

    }
    public List<Modelo> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
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

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }
    }
    public void delete(Recurso r) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM recurso WHERE id = ?");
            stmt.setInt(1, r.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }
    }
    public void delete(Atividade a) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM atividade WHERE id = ?");
            stmt.setInt(1, a.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }
    }
    public void delete(Regra r) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM regra WHERE id = ?");
            stmt.setInt(1, r.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }
    }
}