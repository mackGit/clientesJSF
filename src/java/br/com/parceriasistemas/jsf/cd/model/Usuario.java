package br.com.parceriasistemas.jsf.cd.model;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name="SEQ_USUARIO", sequenceName = "SEQ_USUARIO")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_USUARIO")
    @Column(name = "idUsuario", length = 20, updatable = false)
    private Integer idUsuario;
    
    @Column(name = "nomeUsuario")
    @Size(max = 150)
    @NotEmpty
    private String nomeUsuario;
    
    @Column(name = "loginUsuario", unique=true)
    @Size(max = 20)
    @NotEmpty
    private String loginUsuario;
    
    @Column(name = "emailUsuario", unique=true)
    @Size(max = 100)
    @NotEmpty
    private String emailUsuario;
    
    
    @Column(name = "senhaUsuario")
    @Size(min = 3, max = 100 )
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
    
    
    
    
}
