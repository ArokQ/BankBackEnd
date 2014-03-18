package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CheckingAccountDetail;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.eto.CustomerBannedException;
import dk.cphbusiness.bank.contract.eto.InsufficientFundsException;
import dk.cphbusiness.bank.contract.eto.NoSuchAccountException;
import dk.cphbusiness.bank.contract.eto.NoSuchCustomerException;
import dk.cphbusiness.bank.contract.eto.TransferNotAcceptedException;
import static dk.cphbusiness.bank.control.Assembler.*;
import dk.cphbusiness.bank.model.Account;
import dk.cphbusiness.bank.model.CheckingAccount;
import dk.cphbusiness.bank.model.Person;
import java.math.BigDecimal;
import java.util.Collection;
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
        //Person person = new Person("010101-1001", "Awesome", "Niels", "Boer", "Vej 4", 24242525);
        return " Hello " + name + " from bank manager bean ";//+person.getCpr();
    }

    @Override
    public Collection<CustomerSummary> listCustomers() {
        Query query = em.createNamedQuery("Person.findAll");
        Collection<Person> customer = query.getResultList();
        return createCustomerSummaries(customer);
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
        return createAccountSummaries(customer.getOwnedAccounts());
    }

    @Override
    public Collection<String> listAccountTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail transferAmount(BigDecimal amount, AccountIdentifier source, AccountIdentifier target) throws NoSuchAccountException, TransferNotAcceptedException, InsufficientFundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail showAccountHistory(AccountIdentifier account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDetail saveCustomer(CustomerDetail customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDetail showCustomer(CustomerIdentifier customer) throws NoSuchCustomerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail createAccount(CustomerIdentifier customerId, AccountDetail detail) throws NoSuchCustomerException, CustomerBannedException {
        Person customer = em.find(Person.class, customerId.getCpr());
        if (detail instanceof CheckingAccountDetail) {
            CheckingAccountDetail checkingAccountDetail = (CheckingAccountDetail) detail;
            CheckingAccount checkingAccount = new CheckingAccount(
                    checkingAccountDetail.getNumber(),
                    checkingAccountDetail.getInterest().doubleValue(),
                    customer
            );
            em.persist(checkingAccount);
            return createAccountDetail(checkingAccount);
        } else {
            throw new RuntimeException("Unknown Account Type");
        }

    }

    public Person createCustomerEntity(CustomerDetail detail) {
        Person customer = em.find(Person.class, Integer.parseInt(detail.getCpr()));
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

            return customer;
        }
    }

}
