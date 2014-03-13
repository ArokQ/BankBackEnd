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
import javax.validation.constraints.Size;

/**
 *
 * @author Mads
 */
@Entity
@Table(name = "TRANSFER")
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t")})
public class Transfer implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private Double amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSFERDATE")
    @Temporal(TemporalType.DATE)
    private Date transferdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TARGETACCOUNT")
    private String targetaccount;
    @JoinColumn(name = "ACCOUNT_ACCOUNTNUMBER", referencedColumnName = "ACCOUNTNUMBER")
    @ManyToOne(optional = false)
    private Account accountAccountnumber;

    public Transfer() {
    }

    public Transfer(Double amount) {
        this.amount = amount;
    }

    public Transfer(Double amount, Date transferdate, String targetaccount) {
        this.amount = amount;
        this.transferdate = transferdate;
        this.targetaccount = targetaccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransferdate() {
        return transferdate;
    }

    public void setTransferdate(Date transferdate) {
        this.transferdate = transferdate;
    }

    public String getTargetaccount() {
        return targetaccount;
    }

    public void setTargetaccount(String targetaccount) {
        this.targetaccount = targetaccount;
    }

    public Account getAccountAccountnumber() {
        return accountAccountnumber;
    }

    public void setAccountAccountnumber(Account accountAccountnumber) {
        this.accountAccountnumber = accountAccountnumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amount != null ? amount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.amount == null && other.amount != null) || (this.amount != null && !this.amount.equals(other.amount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.bank.control.Transfer[ amount=" + amount + " ]";
    }

}
