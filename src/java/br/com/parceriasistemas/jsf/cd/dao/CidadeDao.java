/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.dao;

import br.com.parceriasistemas.jsf.cd.model.Cidade;
import br.com.parceriasistemas.jsf.cd.servicos.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author matheus
 */
public class CidadeDao {
    
    private Session sessao;
    private Transaction tr;
    private List<Cidade> list;
    
    public List<Cidade> getList() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        tr=sessao.beginTransaction();
        
        Criteria cri = sessao.createCriteria(Cidade.class);
        this.list=cri.list();
        return list;
    }
    
    
    
    public void adicionarCidadeBanco (Cidade cid) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            tr = sessao.beginTransaction();
            Cidade cidade = new Cidade();
            cidade.setNomeCidade(cid.getNomeCidade());
            cidade.setEstadoCidade(cid.getEstadoCidade());
            sessao.save(cidade);
            tr.commit();
            
        } catch (Exception e) {
            System.out.println(""+e);
        } finally {
            sessao.close();
        }
    }
    
    
    
    public void removerCidadeBanco (Cidade cid) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            tr = sessao.beginTransaction();
            
            sessao.delete(cid);
            tr.commit();
            
        } catch (Exception e) {
            System.out.println(""+e);
        } finally {
            sessao.close();
        }
    }
    
    public void atualizarCidadeBanco (Cidade cid) {
         sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(cid);
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
