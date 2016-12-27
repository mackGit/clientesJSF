/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.dao;

import br.com.parceriasistemas.jsf.cd.model.Usuario;
import br.com.parceriasistemas.jsf.cd.servicos.HibernateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author matheus
 */
public class UsuarioDao {
    private Session sessao;
    private Transaction tr;
    private List<Usuario> list;
    
    public Usuario verificarDadosLoginBanco(Usuario usuario)throws Exception {
        Usuario us = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "SELECT u FROM Usuario u WHERE loginUsuario = '" + usuario.getLoginUsuario()
                            + "' and senhaUsuario = '" + usuario.getSenhaUsuario()+ "'";
            
            Query query = sessao.createQuery(hql);
            
            if (!query.list().isEmpty()) {
                us = (Usuario) query.list().get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        System.out.println("query: "+us);
        return us;
    }
   
        
    public List<Usuario> getList() {
        sessao =HibernateUtil.getSessionFactory().openSession();
        tr=sessao.beginTransaction();
        try {
            Criteria cri = sessao.createCriteria(Usuario.class);
            this.list=cri.list();
            return list;
        } catch (Exception e) {
            System.out.println("erro: " + e);
            return null;
       } finally {
            sessao.getTransaction().rollback();
            sessao.close();
       }
    }
    
    /*
    String encript = DigestUtils.md5Hex(this.obj.getSenhaUsuario());
            this.obj.setSenhaUsuario(encript);
    
    */
    
    
    public void adicionarUsuarioBanco (Usuario u) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            tr = sessao.beginTransaction();

            Usuario usuario = new Usuario();
            usuario.setNomeUsuario(u.getNomeUsuario());
            usuario.setLoginUsuario(u.getLoginUsuario());
            usuario.setEmailUsuario(u.getEmailUsuario());
            /* add senha criptografada */
            String encript = DigestUtils.md5Hex(u.getSenhaUsuario());
            usuario.setSenhaUsuario(encript);

            sessao.save(usuario);
            tr.commit();
            
        } catch (Exception e) {
            System.out.println(""+e);
        } finally {
            sessao.close();
        }
    }
    
    public void removerUsuarioBanco (Usuario u) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            tr = sessao.beginTransaction();
            
            sessao.delete(u);
            tr.commit();
            
        } catch (Exception e) {
            System.out.println(""+e);
        } finally {
            sessao.close();
        }
    }
    
     public void atualizarUsuarioBanco (Usuario u) {
         sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(u);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(""+e);
            sessao.getTransaction().rollback();  
        } finally {
            sessao.close();
        }
    }
}
