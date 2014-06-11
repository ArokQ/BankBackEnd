package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.*;
import dk.cphbusiness.bank.contract.eto.*;
import static dk.cphbusiness.bank.control.Assembler.*;
import dk.cphbusiness.bank.model.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class BankManagerBean implements BankManager {

    @PersistenceContext(unitName = "BackendPU")
    private EntityManager em;

    @Override
    public String sayHello(String name) {
    return " Hello " + name + " from bank manager bean ";//+person.getCpr();
    }

    @Override
    public Collection<CustomerSummary> listCustomers() {
        Query query = em.createNamedQuery("Person.findAll");
        Collection<Person> customer = query.getResultList();
        return createCustomerSummaries(customer);
    }
    
    @Override
    public int getCustomerCount() {
        Query query = em.createNamedQuery("Person.findAll");
        Collection<Person> customer = query.getResultList();
        return customer.size();
    }


    @Override
    public Collection<AccountSummary> listAccounts() {
        Query query = em.createNamedQuery("Account.findAll");
        Collection<Account> account = query.getResultList();
        return createAccountSummaries(account);
    }

    @Override
    public Collection<AccountSummary> listCustomerAccounts(CustomerIdentifier identifier) {
        Person customer = em.find(Person.class, identifier.getCpr());
        if (customer == null) {
            return createAccountSummaries(null);
        }
        em.refresh(customer);
        return createAccountSummaries(customer.getOwnedAccounts());
    }

    @Override
    public Collection<String> listAccountTypes() {
        Collection<String> accountTypes = new ArrayList<>();
        accountTypes.add("Checking Account");
        accountTypes.add("Realestate Account");
        accountTypes.add("Money Market Account");
        accountTypes.add("Time Deposit Account");
        return accountTypes;
        //----------------------------------------------------------------------
    }

    @Override
    public AccountDetail transferAmount(BigDecimal amount, AccountIdentifier source, AccountIdentifier target) throws NoSuchAccountException, TransferNotAcceptedException, InsufficientFundsException {
        Account sourceAccount = em.find(Account.class, source.getNumber());
        Account targetAccount = em.find(Account.class, target.getNumber());
        Transfer trans = new Transfer((int) (Math.random()*100000000), amount.doubleValue(), new Date(), sourceAccount, targetAccount);
        em.persist(trans);
        return createAccountDetail(sourceAccount);
        //----------------------------------------------------------------------
    }

    @Override
    public AccountDetail showAccountHistory(AccountIdentifier account) {
        Account ac = em.find(Account.class, account.getNumber());
        return createAccountDetail(ac);
    }

    @Override
    public CustomerDetail saveCustomer(CustomerDetail customer) {
        Person person = new Person(customer.getCpr(), customer.getTitle(), customer.getFirstName(), customer.getLastName(), customer.getStreet(), Integer.parseInt(customer.getPhone()));
        person.setPostal(new Postal(customer.getPostalCode(), customer.getPostalDistrict()));
        person.setEmail(customer.getEmail());
        person.setPassword("bla");

        em.merge(person);
        return createCustomerDetail(person);
    }

    @Override
    public CustomerDetail showCustomer(CustomerIdentifier customer) throws NoSuchCustomerException {
        Person person = em.find(Person.class, customer.getCpr());
        if (person == null) {
            throw new NoSuchCustomerException(customer);
        }
        return createCustomerDetail(person);
    }

    @Override
    public AccountDetail createAccount(CustomerIdentifier customerId, AccountDetail detail) throws NoSuchCustomerException, CustomerBannedException {

        Person customer = em.find(Person.class, customerId.getCpr());

        if (detail instanceof CheckingAccountDetail) {
            CheckingAccountDetail checkingAccountDetail = (CheckingAccountDetail) detail;
            CheckingAccount checkingAccount = new CheckingAccount(
                    checkingAccountDetail.getInterest().doubleValue(),
                    customer
            );

            em.persist(checkingAccount);
            return createAccountDetail(checkingAccount);
        } else if (detail instanceof RealestateAccountDetail){
            RealestateAccountDetail realestateAccountDetail = (RealestateAccountDetail) detail;
            
            RealestateAccount realestateAccount = new RealestateAccount(null, 0, 0, null);
                    

            em.persist(realestateAccount);
            return createAccountDetail(realestateAccount);
        } 
        else {
            throw new RuntimeException("Unknown Account Type");
        }

    }

    public Person createCustomerEntity(CustomerDetail detail) {
        Person customer = em.find(Person.class, detail.getCpr());
        if (customer == null) {
            return new Person(
                    detail.getCpr(),
                    detail.getTitle(),
                    detail.getFirstName(),
                    detail.getLastName(),
                    detail.getStreet(),
                    Integer.parseInt(detail.getPhone())
            );
        } else {

            em.persist(customer);
            return customer;
        }
    }

    @Override
    public boolean checkEmail(String email) {

        Query query = em.createNamedQuery("Person.findEmail").setParameter("email", email);

        try {
            query.getSingleResult();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

}
