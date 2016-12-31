package br.com.parceriasistemas.jsf.cd.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.parceriasistemas.jsf.cd.dao.LivroDAO;
import br.com.parceriasistemas.jsf.cd.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

    private static final long serialVersionUID = -501369252529005169L;

    private Livro livroSelecionado;
    private LivroDAO livroDAO;
    private List<Livro> livros;

    @PostConstruct
    public void init() {
        this.livroDAO = new LivroDAO();
        atualizarListaLivros();
    }

    private void atualizarListaLivros() {
        this.livros = livroDAO.getLivros();
    }

    public void limparCamposLivroBean() {
        this.livroSelecionado = new Livro();
    }

    public void adicionarLivroBean() {
        try {
            livroDAO.incluir(livroSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Livro " + livroSelecionado.getTitulo() + " Adicionado."));
            atualizarListaLivros();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar adicionar livro."));
        }
    }

    public void atualizarLivroBean() {
        try {
            livroDAO.alterar(livroSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Livro " + livroSelecionado.getTitulo() + " Atualizado!."));
            atualizarListaLivros();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar atualizar livro."));
            e.printStackTrace();
        }
    }


    public void cancelAtualizarLivroBean() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum livro Foi Alterado!."));
    }

    public void removerLivroBean() {
        try {
            livroDAO.excluir(livroSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Excluído com sucesso!"));
            atualizarListaLivros();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "não foi possível Excluir:"));
            e.printStackTrace();
        }
    }

    public void cancelRemoverLivroBean() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum livro excluído!."));
    }

    public boolean localizar(int id) {
        try {
            this.livroSelecionado = livroDAO.localizarID(id);
            if (livroSelecionado != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Encontrou no Banco de Dados"));
                return true;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fail!", "Não encontrou no Banco de Dados: "));
                return false;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "não foi possível encontrar:"));
            e.printStackTrace();
            return false;
        }
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
    }
}
