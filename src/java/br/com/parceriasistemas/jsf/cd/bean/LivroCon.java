package br.com.parceriasistemas.jsf.cd.bean;

import br.com.parceriasistemas.jsf.cd.dao.LivroDao;
import br.com.parceriasistemas.jsf.cd.model.Livro;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="livroBean")
@SessionScoped
public class LivroCon {
    
    public Livro obj;
    private LivroDao dao = new LivroDao();
    private String mensagem;
    private char acao = ' ';
    
    public LivroCon() {
    }

    public boolean incluir(){
        obj = new Livro();
        obj.setId(0);
        obj.setAutor("");
        obj.setTitulo("");
        
        acao = 'I';
        return true;
    }

    public boolean alterar(){
        acao = 'A';
        return true;
    }

    public boolean gravar(){
        switch(acao){
            case 'I':
                acao = ' ';
                if (dao.incluir(obj)){
                   mensagem = dao.getMensagem();
                    return true;
                }
                else{
                    mensagem = dao.getMensagem();
                    return false;
                }
            case 'A':
                acao = ' ';
                if (dao.alterar(obj)){
                    mensagem = dao.getMensagem();
                    return true;
                }
                else{
                    mensagem = dao.getMensagem();
                    return false;
                }
            default:
                mensagem = "Erro: Nao tem acao especificada!";
                return false;
        }
    }

    public boolean excluir(){
        if (dao.excluir(obj)){
            mensagem = dao.getMensagem();
            return true;
        }else{
            mensagem = dao.getMensagem();
            return false;
        }
    }
    
    public void excluir(Livro livro){
        if(localizar(livro.getId())){
            if (dao.excluir(obj)){
                mensagem = dao.getMensagem();            
            }else{
                mensagem = dao.getMensagem();            
            }
        }
    }
    
    public boolean localizar(int id){
        Livro objSearch = dao.localizarID(id);
        if (objSearch != null){
            obj = objSearch;
            setMensagem(dao.getMensagem());
            return true;
        } else{
            setMensagem("Erro: "+dao.getMensagem());
            return false;
        }
    }
    
    public void localizar(Livro livro){
        Livro objSearch = dao.localizarID(livro.getId());
        if (objSearch != null){
            obj = objSearch;
            setMensagem(dao.getMensagem());
        } else{
            setMensagem("Erro: "+dao.getMensagem());
        }
    }
    
    public List<Livro> getLivros(){
        mensagem = dao.getMensagem();
        return dao.getLivros();
    }
   
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Livro getLivro() {
        return obj;
    }
    
}
