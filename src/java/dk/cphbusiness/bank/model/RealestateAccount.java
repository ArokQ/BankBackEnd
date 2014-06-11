package dk.cphbusiness.bank.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mads
 */
@Entity
@Table(name = "REALESTATE_ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RealestateAccount.findAll", query = "SELECT r FROM RealestateAccount r"),
    @NamedQuery(name = "RealestateAccount.findByNumber", query = "SELECT r FROM RealestateAccount r WHERE r.number = :number"),
    @NamedQuery(name = "RealestateAccount.findByCys", query = "SELECT r FROM RealestateAccount r WHERE r.cys = :cys"),
    @NamedQuery(name = "RealestateAccount.findByVp", query = "SELECT r FROM RealestateAccount r WHERE r.vp = :vp"),
    @NamedQuery(name = "RealestateAccount.findByExdate", query = "SELECT r FROM RealestateAccount r WHERE r.exdate = :exdate")})
public class RealestateAccount extends Account {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NUMBER")
    private String number;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CYS")
    private int cys;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VP")
    private int vp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXDATE")
    @Temporal(TemporalType.DATE)
    private Date exdate;
    @JoinColumn(name = "NUMBER", referencedColumnName = "NUMBER", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Account account;

    public RealestateAccount() {
    }

    public RealestateAccount(String number) {
        this.number = number;
    }

    public RealestateAccount(String number, int cys, int vp, Date exdate) {
        this.number = number;
        this.cys = cys;
        this.vp = vp;
        this.exdate = exdate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCys() {
        return cys;
    }

    public void setCys(int cys) {
        this.cys = cys;
    }

    public int getVp() {
        return vp;
    }

    public void setVp(int vp) {
        this.vp = vp;
    }

    public Date getExdate() {
        return exdate;
    }

    public void setExdate(Date exdate) {
        this.exdate = exdate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (number != null ? number.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RealestateAccount)) {
            return false;
        }
        RealestateAccount other = (RealestateAccount) object;
        if ((this.number == null && other.number != null) || (this.number != null && !this.number.equals(other.number))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.bank.model.RealestateAccount[ number=" + number + " ]";
    }

}
