package br.com.parceriasistemas.jsf.cd.dao;

import br.com.parceriasistemas.jsf.cd.model.Livro;
import br.com.parceriasistemas.jsf.cd.servicos.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class LivroDao {
    
    private String mensagem;

    public boolean incluir(Livro obj) {
       Session sessao = HibernateUtil.getSession(); 
       sessao.beginTransaction(); 
       try{
          sessao.save(obj); 
          sessao.getTransaction().commit();
          mensagem = "Incluído com sucesso!";
          return true;
       }catch (Exception  e){
          sessao.getTransaction().rollback();
          mensagem = "Não foi possível incluir!";
          return false;
       } finally {
          sessao.close();
       }
    }

    public boolean alterar(Livro obj) {
       Session sessao = HibernateUtil.getSession(); 
       sessao.beginTransaction(); 
       try{
          sessao.update(obj); 
          sessao.getTransaction().commit();
          mensagem = "Alterado com sucesso!";
          return true;
       }catch (Exception  e){
          sessao.getTransaction().rollback();
          mensagem = "Erro ao Alterar: " + e;
          return false;
       } finally {
          sessao.close();
       }
    }

    public boolean excluir(Livro obj) {
       Session sessao = HibernateUtil.getSession();
       sessao.beginTransaction(); 
       try{
          sessao.delete(obj); 
          sessao.getTransaction().commit();
          mensagem = "Excluído com sucesso!";
          return true;
       }catch (Exception  e){
          sessao.getTransaction().rollback();
          mensagem = "Não foi possível Excluir: "+ e;
          return false;
       } finally {
          sessao.close();
       }
    }

    public Livro localizarID(int id) {
       Session sessao = HibernateUtil.getSession();
       sessao.beginTransaction(); 
       try {
          Livro encontrado = (Livro) sessao.load(Livro.class, id);         
          mensagem = "Encontrou no Banco de Dados";
          return encontrado;
       } catch (Exception e) {
          mensagem = "Não encontrou no Banco de Dados: " + e;
          return null;
       } finally {
          sessao.getTransaction().rollback();
          sessao.close();
       }
    }
    
    public List<Livro> getLivros() {
       Session sessao = HibernateUtil.getSession(); 
       sessao.beginTransaction(); 
       try {
          mensagem = "Lista de Cidades.";
          return sessao.createCriteria(Livro.class).
                  list();
       } catch (Exception e) {
          mensagem = "Não foi possível retornar lista: ";
          return null;
       } finally {
          sessao.getTransaction().rollback();
          sessao.close();
       }
    }

    public String getMensagem() {
       return mensagem;
    }

    public void setMensagem(String mensagem) {
       this.mensagem = mensagem;
    }
    
}
