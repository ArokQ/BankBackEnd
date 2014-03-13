package dk.cphbusiness.bank.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mads
 */
@Entity
@Table(name = "ACCOUNT")
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNTNUMBER")
    private Integer accountnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALANCE")
    private double balance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTEREST")
    private double interest;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountAccountnumber")
    private Collection<Transfer> transferCollection;
    @JoinColumn(name = "PERSON_CPR", referencedColumnName = "CPR")
    @ManyToOne(optional = false)
    private Person personCpr;

    public Account() {
    }

    public Account(Integer accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Account(Integer accountnumber, double balance, double interest) {
        this.accountnumber = accountnumber;
        this.balance = balance;
        this.interest = interest;
    }

    public Integer getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(Integer accountnumber) {
        this.accountnumber = accountnumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Collection<Transfer> getTransferCollection() {
        return transferCollection;
    }

    public void setTransferCollection(Collection<Transfer> transferCollection) {
        this.transferCollection = transferCollection;
    }

    public Person getPersonCpr() {
        return personCpr;
    }

    public void setPersonCpr(Person personCpr) {
        this.personCpr = personCpr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountnumber != null ? accountnumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountnumber == null && other.accountnumber != null) || (this.accountnumber != null && !this.accountnumber.equals(other.accountnumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.bank.control.Account[ accountnumber=" + accountnumber + " ]";
    }

}
