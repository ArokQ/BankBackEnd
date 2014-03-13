

package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.eto.CustomerBannedException;
import dk.cphbusiness.bank.contract.eto.InsufficientFundsException;
import dk.cphbusiness.bank.contract.eto.NoSuchAccountException;
import dk.cphbusiness.bank.contract.eto.NoSuchCustomerException;
import dk.cphbusiness.bank.contract.eto.TransferNotAcceptedException;
import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.Stateless;

@Stateless
public class BankManagerBean implements BankManager {

    @Override
    public String sayHello(String name) {
        return "Hello "+name+ " from bank manager bean";
    }

    
    @Override
    public Collection<CustomerSummary> listCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<AccountSummary> listAccounts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<AccountSummary> listCustomerAccounts(CustomerIdentifier customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public AccountDetail createAccount(CustomerIdentifier customer, AccountDetail account) throws NoSuchCustomerException, CustomerBannedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
