/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cliente")
@SequenceGenerator(name="SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE")
public class Cliente implements Serializable{
    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CLIENTE")
    @Column(name = "idCliente", length = 20, updatable = false)
    private Integer idCliente;
    
    @Column(name = "nomeCliente")
    @Size(max = 150)
    @NotEmpty
    private String nomeCliente;
    
    @Column(name = "dtNascimentoCliente")
    @Temporal(TemporalType.DATE)
    private Date dtNascimentoCliente;
    
    @Column(name = "cpfCnpjCliente")
    @Size(min=14,max=14)
    private String  cpfCnpjCliente;
    
    @Column(name = "bairroCliente")
    @Size(max=100)
    private String  bairroCliente;
    
    @Column(name = "enderecoCliente")
    @Size(max=100)
    private String  enderecoCliente;
    
    @Column(name = "numeroCliente")
    private Integer numeroCliente;
    
    @Column(name = "cepCliente")
    @Size(max=8)
    private String  cepCliente;
    
    @Column(name = "complementoCliente")
    @Size(max=80)
    private String  complementoCliente;
    
    @Column(name = "emailCliente")
    @Size(max=80)
    private String  emailCliente;
    
    @Column(name = "telCelularCliente")
    @Size(max=80)
    private String  telCelularCliente;
    
    @Column(name = "telComercialCliente")
    @Size(max=80)
    private String  telComercialCliente;
    
    @Column(name = "stCliente")
    private Boolean stCliente;
    
    @JoinColumn(name = "idCidade")
    @ManyToOne
    private Cidade cidadeCliente;

    /* Contrutores */

    
    public Cliente() {
    }
    
    
    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, String nomeCliente, Date dtNascimentoCliente, String cpfCnpjCliente, String bairroCliente, String enderecoCliente, Integer numeroCliente, String cepCliente, String complementoCliente, String emailCliente, String telCelularCliente, String telComercialCliente, Boolean stCliente, Cidade cidadeCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.dtNascimentoCliente = dtNascimentoCliente;
        this.cpfCnpjCliente = cpfCnpjCliente;
        this.bairroCliente = bairroCliente;
        this.enderecoCliente = enderecoCliente;
        this.numeroCliente = numeroCliente;
        this.cepCliente = cepCliente;
        this.complementoCliente = complementoCliente;
        this.emailCliente = emailCliente;
        this.telCelularCliente = telCelularCliente;
        this.telComercialCliente = telComercialCliente;
        this.stCliente = stCliente;
        this.cidadeCliente = cidadeCliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idCliente);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (idCliente == null) {
            if (other.idCliente != null)
                return false;
        } else if (!idCliente.equals(other.idCliente))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + '}';
    }
    
    
    
    
    
    
    /*getters e setters*/
    
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDtNascimentoCliente() {
        return dtNascimentoCliente;
    }

    public void setDtNascimentoCliente(Date dtNascimentoCliente) {
        this.dtNascimentoCliente = dtNascimentoCliente;
    }

    public String getCpfCnpjCliente() {
        return cpfCnpjCliente;
    }

    public void setCpfCnpjCliente(String cpfCnpjCliente) {
        this.cpfCnpjCliente = cpfCnpjCliente;
    }

    public String getBairroCliente() {
        return bairroCliente;
    }

    public void setBairroCliente(String bairroCliente) {
        this.bairroCliente = bairroCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public Integer getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(Integer numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }

    public String getComplementoCliente() {
        return complementoCliente;
    }

    public void setComplementoCliente(String complementoCliente) {
        this.complementoCliente = complementoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelCelularCliente() {
        return telCelularCliente;
    }

    public void setTelCelularCliente(String telCelularCliente) {
        this.telCelularCliente = telCelularCliente;
    }

    public String getTelComercialCliente() {
        return telComercialCliente;
    }

    public void setTelComercialCliente(String telComercialCliente) {
        this.telComercialCliente = telComercialCliente;
    }

    public Cidade getCidadeCliente() {
        return cidadeCliente;
    }
    
     public String getCidadeClienteString() {
        return cidadeCliente.getNomeCidade();
    }
    

    public void setCidadeCliente(Cidade cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public Boolean getStCliente() {
        return stCliente;
    }
    
    public String getStClienteString() {
        if (stCliente == true) {
            return "Ativo";
        } else {
            return "Desativado";
        }
    }

    public void setStCliente(Boolean stCliente) {
        this.stCliente = stCliente;
    }
    
    
    
    
    
}
