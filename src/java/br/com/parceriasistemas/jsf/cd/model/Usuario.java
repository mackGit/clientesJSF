package br.com.parceriasistemas.jsf.cd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 3320940951749228854L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_USUARIO")
    @Column(name = "idUsuario", length = 20, updatable = false)
    private Integer idUsuario;

    @Column(name = "nomeUsuario")
    @Size(max = 150)
    @NotEmpty
    private String nomeUsuario;

    @Column(name = "loginUsuario", unique = true)
    @Size(max = 20)
    @NotEmpty
    private String loginUsuario;

    @Column(name = "emailUsuario", unique = true)
    @Size(max = 100)
    @NotEmpty
    private String emailUsuario;

    @Column(name = "senhaUsuario")
    @Size(min = 3, max = 100)
    @NotEmpty
    private String senhaUsuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nomeUsuario, String loginUsuario, String emailUsuario, String senhaUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.loginUsuario = loginUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public void criptografarSenhaUsuario() {
        this.senhaUsuario = DigestUtils.md5Hex(this.senhaUsuario);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emailUsuario == null) ? 0 : emailUsuario.hashCode());
        result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
        result = prime * result + ((loginUsuario == null) ? 0 : loginUsuario.hashCode());
        result = prime * result + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
        result = prime * result + ((senhaUsuario == null) ? 0 : senhaUsuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (emailUsuario == null) {
            if (other.emailUsuario != null)
                return false;
        } else if (!emailUsuario.equals(other.emailUsuario))
            return false;
        if (idUsuario == null) {
            if (other.idUsuario != null)
                return false;
        } else if (!idUsuario.equals(other.idUsuario))
            return false;
        if (loginUsuario == null) {
            if (other.loginUsuario != null)
                return false;
        } else if (!loginUsuario.equals(other.loginUsuario))
            return false;
        if (nomeUsuario == null) {
            if (other.nomeUsuario != null)
                return false;
        } else if (!nomeUsuario.equals(other.nomeUsuario))
            return false;
        if (senhaUsuario == null) {
            if (other.senhaUsuario != null)
                return false;
        } else if (!senhaUsuario.equals(other.senhaUsuario))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("Nome: ").append(getNomeUsuario()).append(" | Login: ").append(getLoginUsuario()).toString();
    }
}
