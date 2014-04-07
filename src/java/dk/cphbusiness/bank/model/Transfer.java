/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.bank.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kenneth
 */
@Entity
@Table(name = "TRANSFER")
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t")})
public class Transfer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private double amount;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSFERDATE")
    @Temporal(TemporalType.DATE)
    private Date transferdate;
    
    @JoinColumn(name = "SOURCE_NUMBER", referencedColumnName = "NUMBER")
    @ManyToOne(optional = false)
    private Account sourceAccount;
    
    @JoinColumn(name = "TARGET_NUMBER", referencedColumnName = "NUMBER")
    @ManyToOne(optional = false)
    private Account targetAccount;

    public Transfer() {
    }

    public Transfer(Integer id) {
        this.id = id;
    }

    public Transfer(Integer id, double amount, Date transferdate, Account source, Account target) {
        this.id = id;
        this.amount = amount;
        this.transferdate = transferdate;
        this.sourceAccount = source;
        source.getOutgoingTransfers().add(this);
        this.targetAccount = target;
        target.getIncomingTransfers().add(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransferdate() {
        return transferdate;
    }

    public void setTransferdate(Date transferdate) {
        this.transferdate = transferdate;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.bank.model.Transfer[ id=" + id + " ]";
    }
    
}
