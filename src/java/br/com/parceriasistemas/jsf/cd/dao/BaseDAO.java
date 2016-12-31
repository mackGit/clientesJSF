package br.com.parceriasistemas.jsf.cd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.parceriasistemas.jsf.cd.servicos.HibernateUtil;

/**
 * 
 * @author Everton Morais
 */
    public final void insert(Object entity) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.save(entity);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public final void update(Object entity) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public final void delete(Object entity) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @SuppressWarnings({ "rawtypes" })
    protected final List getListByCriteriaClass(Class criteriaClass) {
        List<?> result = null;
        Session session = HibernateUtil.getSession();
        try {
            Criteria cri = session.createCriteria(criteriaClass);
            result = cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @SuppressWarnings({ "rawtypes" })
    protected final List getListByHQLQuery(String hql) {
        Session session = HibernateUtil.getSession();
        try {
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
