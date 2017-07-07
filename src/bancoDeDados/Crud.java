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
    
    public int pegaIdRegra(){
        Connection con = Conector.getConnection();
    PreparedStatement stmt = null;
        ResultSet rs = null;
        int i = 0;
        try {
            stmt = con.prepareStatement("select max(id) as id from regra");
            rs = stmt.executeQuery();
            rs.next();
            i = rs.getInt("id");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PegaIdRegra Erro!" + ex);
        }finally {
            Conector.closeConnection(con, stmt);
        }
        return i;
    
    }
        public int pegaIdRecurso(){
        Connection con = Conector.getConnection();
    PreparedStatement stmt = null;
        ResultSet rs = null;
        int i = 0;
        try {
            stmt = con.prepareStatement("select max(id) as id from recurso");
            rs = stmt.executeQuery();
            rs.next();
            i = rs.getInt("id");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }finally {
            Conector.closeConnection(con, stmt);
        }
        return i;
    
    }
    public void ligaModeloRegras(Modelo m){
        Connection cx = Conector.getConnection();
        PreparedStatement stmt = null;
    try{
    for (Regra r: m.getRegra()){
            create(r);
            r.setId(pegaIdRegra());
                stmt = cx.prepareStatement("INSERT INTO modelo_regra (fk_modelo_id, fk_regra_id)VALUES(?, ?)");
                stmt.setInt(1, m.getId());
                stmt.setInt(2, r.getId());
                stmt.executeUpdate();
                
            }
    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Liga Modelo Regra Erro!"+ex);
        }finally {
            Conector.closeConnection(cx, stmt);
        }
    }
    public void ligaAtividadeRecurso(Atividade a){
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;
    try{
    for (Recurso r: a.getRecursos()){
//            create(r);
//            r.setId(pegaIdRecurso());
                stmt = con.prepareStatement("INSERT INTO atividade_recurso "
                        + "(fk_atividade_id, fk_recurso_id)VALUES(?, ?)");
                stmt.setInt(1, a.getId());
                stmt.setInt(2, r.getId());
                stmt.executeUpdate();
            }
    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Liga Atividade Recurso Erro!"+ex);
        }finally {
            Conector.closeConnection(con, stmt);
        }
    }
    public void ligaModeloAtividades(Modelo m){
        Connection cx = Conector.getConnection();
        PreparedStatement stmt = null;
    try{
    for (Atividade a: m.getDominio().getAtividades()){
            create(a);
            a.setId(pegaIdAtividade());
                stmt = cx.prepareStatement("INSERT INTO dominio (fk_atividade_id, fk_modelo_id)VALUES(?, ?)");
                stmt.setInt(1, a.getId());
                stmt.setInt(2, m.getId());
                stmt.executeUpdate();
                ligaAtividadeRecurso(a);
                
                
            }
    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Liga Modelo Atividade Erro!"+ex);
        }finally {
            Conector.closeConnection(cx, stmt);
        }
        
    }
    /**
     *
     * @param d
     * liga o modelo a suas atividades
     */
    public void CriaDominio(Dominio d){
        Connection con = Conector.getConnection();
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
            JOptionPane.showMessageDialog(null, "Cria Dominio Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }
    }
    public void create(Regra r) {
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO regra (tipo, ladoe, ladod)VALUES( ?, ?, ?)");
            stmt.setString(1, r.getTipo());
            stmt.setString(2, r.getLadoE());
            stmt.setString(3, r.getLadoD());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Create Regra Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }
    public int pegaIdAtividade(){
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int i = 0;
        try {
            stmt = con.prepareStatement("select max(id) as id from atividade");
            rs = stmt.executeQuery();
            rs.next();
            i = rs.getInt("id");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }finally {
            Conector.closeConnection(con, stmt);
        }
        return i;
        
    }
        public int pegaIdModelo() {
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int i = 0;
        try {
            stmt = con.prepareStatement("select max(id) as id from modelo");
            rs = stmt.executeQuery();
            rs.next();
            i = rs.getInt("id");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PegaIdModelo Erro! " + ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }
        return i;
    }
    public void create(Modelo m) {
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO modelo (nome)VALUES(?)");
            stmt.setString(1, m.getNome());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Create Modelo Erro!"+ex);
        }finally {
            Conector.closeConnection(con, stmt);
        }
        m.setId(pegaIdModelo());
        ligaModeloRegras(m);
        ligaModeloAtividades(m);
    }
    /**
     *
     * @param a
     * passar a atividade com o array de seus recursos
     */
    public void alocaRecurso(Atividade a) {
        Connection cx = Conector.getConnection();
        PreparedStatement stmt = null;
        try {
            for (Recurso r: a.getRecursos()){
                stmt = cx.prepareStatement("INSERT INTO atividade_recurso ( fk_atividade_id, fk_recurso_id)VALUES( ?, ?)");
                stmt.setInt(1, a.getId());
                stmt.setInt(2, r.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Aloca Recurso Erro!"+ex);
        } finally {
            Conector.closeConnection(cx, stmt);
        }

    }
    public void create(Atividade a) {
        Connection cx = Conector.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = cx.prepareStatement("INSERT INTO atividade ( nome, tid, tipo)VALUES( ?, ?, ?)");
            stmt.setString(1, a.getNome());
            stmt.setInt(2, 0);
            stmt.setString(3, a.getTipo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "create Recurso Erro!"+ex);
        } finally {
            Conector.closeConnection(cx, stmt);
        }

    }
    public void create(Recurso r) {
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO recurso (nome, tipo, descricao )VALUES( ?,?,?)");
            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getTipo());
            stmt.setString(3, r.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Create Recurso Erro!"+ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }
    public ArrayList<Recurso> listaRecurso() {
        Connection con = Conector.getConnection();
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
        public ArrayList<Recurso> listaRecurso(Atividade a) {
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList <Recurso> recursos = new ArrayList();
        try {
            stmt = con.prepareStatement("select recurso.id as id, recurso.nome as nome,recurso.tipo as tipo, recurso.descricao as descricao from  atividade\n" +
"	inner join atividade_recurso as ar on(ar.fk_atividade_id = atividade.id)\n" +
"	inner join recurso on (ar.fk_recurso_id = recurso.id)\n" +
"	where atividade.id ="+a.getId());
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
    public ArrayList<Recurso> carregaRecursoAtividade(int id){
        Connection con = Conector.getConnection();
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
        Connection con = Conector.getConnection();
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
                a.setRecursos(carregaRecursoAtividade(a.getId()));
                atividades.add(a);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        } finally {
            Conector.closeConnection(con, stmt, rs);
        }

        return atividades;
    }
        public ArrayList<Atividade> listaAtividade(Modelo m) {
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList <Atividade> atividades = new ArrayList();
        ArrayList<Recurso> r;
        try {
            stmt = con.prepareStatement("select * from modelo \n" +
"	inner join dominio on (modelo.id = dominio.fk_modelo_id)\n" +
"	inner join atividade on (atividade.id = dominio.fk_atividade_id)\n" +
"	where modelo.id = "+m.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Atividade a = new Atividade();
                r = carregaRecursoAtividade(a.getId());
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setTipo(rs.getString("tipo"));
                a.setRecursos(r);
                atividades.add(a);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        } finally {
            Conector.closeConnection(con, stmt, rs);
        }

        return atividades;
    }
        
    public ArrayList<Regra> listaRegras(Modelo m) {
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList <Regra> regras = new ArrayList();
        try {
            stmt = con.prepareStatement("select regra.id as id, regra.tipo as tipo, regra.ladoe as ladoe, regra.ladod as ladod from modelo\n" +
"	inner join modelo_regra as mr on (modelo.id = mr.fk_modelo_id)\n" +
"	inner join regra on (regra.id = mr.fk_regra_id)	where modelo.id ="+m.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Regra r = new Regra();
                r.setId(rs.getInt("id"));
                r.setTipo(rs.getString("tipo"));
                r.setLadoE(rs.getString("ladoe"));
                r.setLadoD(rs.getString("ladod"));
                regras.add(r);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        } finally {
            Conector.closeConnection(con, stmt, rs);
        }

        return regras;
    }

    public List<Modelo> listaModelo() {
        Connection con = Conector.getConnection();
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
        Connection con = Conector.getConnection();
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
        Connection con = Conector.getConnection();
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
        Connection con = Conector.getConnection();
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
        Connection con = Conector.getConnection();
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
        Connection con = Conector.getConnection();
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