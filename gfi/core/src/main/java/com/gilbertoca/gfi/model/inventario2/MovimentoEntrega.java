package com.gilbertoca.gfi.inventario2.model;

import com.gilbertoca.gfi.component.Endereco;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @author Gilberto
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @hibernate.class table = "construtor.estoque_movimento_entrega"
 */
public class MovimentoEntrega implements Serializable{
    private Integer cdMovimentoEntrega;
    private Date dtHrSaida;
    private Date dtHrChegada;
    private Integer cdFuncionario;
    //private Funcionario funcionario = new Funcionario();
    private Integer cdVenda;
    //private Venda venda = new Venda();
    private Endereco enderecoEntrega = new Endereco();
    /**
     * Usado para determinar se todos os itens foram entregues. 
     */
    private Boolean flEntregaRealizada = new Boolean(false);    
    private Set movimentoEntregaItems = Collections.EMPTY_SET;
    private Double valorFrete = new Double(0);
    
 
    /**
     * 
     */
    public MovimentoEntrega() {
       
    }

    /** 
     * @hibernate.set lazy="true" inverse="true" cascade="none"
     * @hibernate.collection-key column="cd_movimento_entrega"
     * @hibernate.collection-one-to-many class="org.appfuse.model.estoque.MovimentoEntregaItem"
     *         
     */
    public Set getMovimentoEntregaItems() {
        return movimentoEntregaItems;
    }

    /**
     * @param movimentoEntregaItems The movimentoEntregaItems to set.
     */
    public void setMovimentoEntregaItems(Set movimentoEntregaItems) {
        this.movimentoEntregaItems = movimentoEntregaItems;
    }
    
    /**
     * @hibernate.many-to-one insert="false" update="false"
     * cascade="none" column="cd_venda" outer-join="true"
     * @return a populated venda object (based on the cdVenda)
     */
//    public Venda getVenda() {
//        return this.venda;
//    }
//
//    public void setVenda(Venda venda) {
//        this.venda = venda;
//    }
//    
//    /**
//     * @hibernate.many-to-one insert="false" update="false"
//     * cascade="none" column="cd_funcionario" outer-join="true"
//     * @return a populated funcionario object (based on the cdFuncionario)
//     */
//    public Funcionario getFuncionario() {
//        return this.funcionario;
//    }
//
//    public void setFuncionario(Funcionario funcionario) {
//        this.funcionario = funcionario;
//    }
    
    /**
     * @hibernate.property  column="fl_entrega_realizada" length="1"
     */
     public Boolean getFlEntregaRealizada() {
         return flEntregaRealizada;
     }

     /**
      * @param flEntregaRealizada The flEntregaRealizada to set.
      */
     public void setFlEntregaRealizada(Boolean flEntregaRealizada) {
         this.flEntregaRealizada = flEntregaRealizada;
     }
 
    /**
     * @return Returns the cdFuncionario.
     * @hibernate.property column = "cd_funcionario"
     */
    public Integer getCdFuncionario() {
        return cdFuncionario;
    }
    /**
     * @param cdFuncionario The cdFuncionario to set.
     */
    public void setCdFuncionario(Integer cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }
    /**
     * @hibernate.id generator-class="sequence" column="cd_movimento_entrega" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.estoque_movimento_entrega_sequence"
     * @return Integer
     */
    public Integer getCdMovimentoEntrega() {
        return cdMovimentoEntrega;
    }
    /**
     * @param cdMovimentoEntrega The cdMovimentoEntrega to set.
     */
    public void setCdMovimentoEntrega(Integer cdMovimentoEntrega) {
        this.cdMovimentoEntrega = cdMovimentoEntrega;
    }
    /**
     * @return Returns the cdVenda.
     * @hibernate.property column = "cd_venda"
     */
    public Integer getCdVenda() {
        return cdVenda;
    }
    /**
     * @param cdVenda The cdVenda to set.
     */
    public void setCdVenda(Integer cdVenda) {
        this.cdVenda = cdVenda;
    }
    /**
     * @return Returns the dtHrSaida.
     * @hibernate.property column = "dt_hr_saida"
     */
    public Date getDtHrSaida() {
        return dtHrSaida;
    }
    /**
     * @param dtHrSaida The dtHrSaida to set.
     */
    public void setDtHrSaida(Date dtHrSaida) {
        this.dtHrSaida = dtHrSaida;
    }

    /**
     * @return Returns the dtHrChegada.
     * @hibernate.property column = "dt_hr_chegada"
     */
    public Date getDtHrChegada() {
        return dtHrChegada;
    }
    /**
     * @param dtHrChegada The dtHrChegada to set.
     */
    public void setDtHrChegada(Date dtHrChegada) {
        this.dtHrChegada = dtHrChegada;
    }
    
    /**
     * @return Returns the endereco.
     * @hibernate.component
     */
    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }
    /**
     * @param endereco The endereco to set.
     */
    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }
    /**
     * @return Returns the valorFrete.
     * @hibernate.property column = "valor_frete"
     */
    public Double getValorFrete() {
        return valorFrete;
    }
    /**
     * @param valorFrete The valorFrete to set.
     */
    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }
    
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof MovimentoEntrega)) {
            return false;
        }
        MovimentoEntrega rhs = (MovimentoEntrega) object;
        return new EqualsBuilder().append(this.cdVenda, rhs.cdVenda).append(
                this.flEntregaRealizada, rhs.flEntregaRealizada).append(
                this.enderecoEntrega, rhs.enderecoEntrega).append(
                this.dtHrSaida, rhs.dtHrSaida).append(this.valorFrete,
                rhs.valorFrete).append(this.cdMovimentoEntrega,
                rhs.cdMovimentoEntrega).append(this.cdFuncionario,
                rhs.cdFuncionario).append(this.dtHrChegada, rhs.dtHrChegada)
                .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(716537779, 640599771).append(this.cdVenda)
                .append(this.flEntregaRealizada).append(this.enderecoEntrega)
                .append(this.dtHrSaida).append(this.valorFrete).append(
                        this.cdMovimentoEntrega).append(this.cdFuncionario)
                .append(this.dtHrChegada).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("cdVenda", this.cdVenda).append("flEntregaRealizada",
                        this.flEntregaRealizada).append("enderecoEntrega",
                        this.enderecoEntrega).append("dtHrSaida",
                        this.dtHrSaida).append("valorFrete", this.valorFrete)
                .append("cdMovimentoEntrega", this.cdMovimentoEntrega).append(
                        "cdFuncionario", this.cdFuncionario).append(
                        "dtHrChegada", this.dtHrChegada).toString();
    }
}
