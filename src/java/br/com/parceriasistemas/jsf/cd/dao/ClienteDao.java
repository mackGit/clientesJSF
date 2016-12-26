/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.dao;

import br.com.parceriasistemas.jsf.cd.model.Cliente;
import br.com.parceriasistemas.jsf.cd.servicos.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mack
 */
public class ClienteDao {
    private Session sessao;
    private Transaction tr;
    private List<Cliente> list;

    
    public List<Cliente> getList() {
        sessao =HibernateUtil.getSessionFactory().openSession();
        tr=sessao.beginTransaction();
        try {
            Criteria cri = sessao.createCriteria(Cliente.class);
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
    
    
    public void adicionarClienteBanco (Cliente c) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            tr = sessao.beginTransaction();

            Cliente cliente = new Cliente();
            cliente.setNomeCliente(c.getNomeCliente());
            cliente.setDtNascimentoCliente(c.getDtNascimentoCliente());
            cliente.setCpfCnpjCliente(c.getCpfCnpjCliente());
            cliente.setBairroCliente(c.getBairroCliente());
            cliente.setEnderecoCliente(c.getEnderecoCliente());
            cliente.setNumeroCliente(c.getNumeroCliente());
            cliente.setCepCliente(c.getCepCliente());
            cliente.setComplementoCliente(c.getComplementoCliente());
            cliente.setEmailCliente(c.getEmailCliente());
            cliente.setTelCelularCliente(c.getTelCelularCliente());
            cliente.setTelComercialCliente(c.getTelComercialCliente());
            if (c.getStCliente() == true) {
                cliente.setStCliente(true);
            } else {
                cliente.setStCliente(false);
            }
            cliente.setCidadeCliente(c.getCidadeCliente());

            sessao.save(cliente);
            tr.commit();
            
        } catch (Exception e) {
            System.out.println(""+e);
        } finally {
            sessao.close();
        }
    }
    
    public void removerClienteBanco (Cliente c) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            tr = sessao.beginTransaction();
            
            sessao.delete(c);
            tr.commit();
            
        } catch (Exception e) {
            System.out.println(""+e);
        } finally {
            sessao.close();
        }
    }
    
     public void atualizarClienteBanco (Cliente c) {
         sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(c);
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
