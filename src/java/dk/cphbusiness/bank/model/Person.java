package dk.cphbusiness.bank.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Mads
 */
@Entity
@Table(name = "PERSON")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")})
public class Person implements Serializable {
    private static final Map<String, Person> items = new HashMap<>();
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CPR")
    private String cpr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STREET")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PHONE")
    private int phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 80)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 80)
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "POSTAL_CODE", referencedColumnName = "CODE")
    @ManyToOne(optional = false)
    private Postal postalCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personCpr")
    private Collection<Account> accountCollection;

    public Person() {
    }

    public Person(String cpr) {
        this.cpr = cpr;
    }

    public Person(String cpr, String title, String firstname, String lastname, String street, int phone) {
        this.cpr = cpr;
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.phone = phone;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Postal getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Postal postalCode) {
        this.postalCode = postalCode;
    }

    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpr != null ? cpr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.cpr == null && other.cpr != null) || (this.cpr != null && !this.cpr.equals(other.cpr))) {
            return false;
        }
        return true;
    }
    
    public static Person find(String cpr) {
    return items.get(cpr);
    }
    public void set(
      String title,
      String firstName,
      String lastName,
      String street,
      String phone,
      String email
      ) {
    this.firstname = firstName;
    this.lastname = lastName;
    this.street = street;
    this.phone = Integer.parseInt(phone);
    this.email = email;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.bank.control.Person[ cpr=" + cpr + " ]";
    }

}
