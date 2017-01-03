package br.com.parceriasistemas.jsf.cd.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.parceriasistemas.jsf.cd.model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioAutenticadoBean implements Serializable {

    private static final long serialVersionUID = -4461503646727477756L;

    public static final String NOME_ATRIBUTO_USUARIO = "usuario";

    private static Usuario usuarioAutenticado;

    @PostConstruct
    public void init() {
        UsuarioAutenticadoBean.usuarioAutenticado = getUsuarioArmazenadoNoFacesContext();
    }

    private static Usuario getUsuarioArmazenadoNoFacesContext() {
        return (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(NOME_ATRIBUTO_USUARIO);
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public String getNomeUsuarioAutenticado() {
        return usuarioAutenticado.getNomeUsuario();
    }

    public String encerrarSessao() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(NOME_ATRIBUTO_USUARIO);
        UsuarioAutenticadoBean.usuarioAutenticado = null;

        return "/login.xhtml";
    }

    public boolean isVerificarSessao() {
        if (usuarioAutenticado == null) {
            redirecionarParaAPaginaDeLogin();
        }
        return usuarioAutenticado != null;
    }

    private void redirecionarParaAPaginaDeLogin() {
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml?faces-redirect=true");
    }

    private static void alterarAtributoDoUsuarioAutenticadoNoFacesContext(Usuario usuarioAutenticado) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(NOME_ATRIBUTO_USUARIO, usuarioAutenticado);
        UsuarioAutenticadoBean.usuarioAutenticado = getUsuarioArmazenadoNoFacesContext();
    }

    public static void adicionarUsuarioNaSessao(Usuario usuarioAutenticado) {
        alterarAtributoDoUsuarioAutenticadoNoFacesContext(usuarioAutenticado);
    }

    public static void atualizarUsuarioNaSessao(Usuario usuarioAutenticado) {
        alterarAtributoDoUsuarioAutenticadoNoFacesContext(usuarioAutenticado);
    }

    public static Usuario getUsuarioAutenticadoNaSessao() {
        return getUsuarioArmazenadoNoFacesContext();
    }
}
