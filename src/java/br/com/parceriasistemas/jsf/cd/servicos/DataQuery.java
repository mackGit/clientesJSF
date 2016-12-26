/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.servicos;

import br.com.parceriasistemas.jsf.cd.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mack
 */
public class DataQuery {
    
    EntityManagerFactory emf;
    EntityManager em;

    public DataQuery() {
        emf = Persistence.createEntityManagerFactory("clientesPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public boolean loginControl(String loginUsuario, String senhaUsuario) {
        try {
            Usuario u = em.createNamedQuery("Login.control", Usuario.class).setParameter("loginUsuario", loginUsuario).setParameter("senhaUsuario", senhaUsuario).getSingleResult();
            if( u != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
