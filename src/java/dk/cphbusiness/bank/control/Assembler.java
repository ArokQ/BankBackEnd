package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CheckingAccountDetail;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.dto.TransferSummary;
import dk.cphbusiness.bank.model.Account;
import dk.cphbusiness.bank.model.Person;
import dk.cphbusiness.bank.model.Transfer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Assembler {

    public static CustomerSummary createCustomerSummary(Person customer) {
        return new CustomerSummary(customer.getCpr(), customer.getFirstname() + " " + customer.getLastname(), customer.getStreet(), null, customer.getEmail());
    }

    public static Collection<CustomerSummary> createCustomerSummaries(Collection<Person> customers) {
        Collection<CustomerSummary> summaries = new ArrayList<>();
        if (customers == null) {
            return summaries;
        }
        for (Person customer : customers) {
            summaries.add(createCustomerSummary(customer));
        }
        return summaries;
    }

    public static AccountSummary createAccountSummary(Account account) {
        return new AccountSummary(account.getAccountnumber().toString(), "Time Deposit Account", BigDecimal.valueOf(account.getBalance()));
    }

    
    public static Collection<AccountSummary> createAccountSummaries(Collection<Account> accounts) {
        Collection<AccountSummary> summaries = new ArrayList<>();
        if (accounts == null) {
            return summaries;
        }
        for (Account account : accounts) {
            summaries.add(createAccountSummary(account));
        }
        return summaries;
    }

    
    public static TransferSummary createTransferSummary(Account account, Transfer transfer) {
        if (transfer.getAccountAccountnumber() == account) {
            return new TransferSummary(transfer.getTransferdate(), BigDecimal.valueOf(transfer.getAmount()), transfer.getTargetaccount());
        }
        else {
            return new TransferSummary(transfer.getTransferdate(), BigDecimal.valueOf(transfer.getAmount()), transfer.getAccountAccountnumber().toString());
        }
    }
    
    public static AccountDetail createAccountDetail(Account account) {
    List<Transfer> transfers = new ArrayList<>();
    transfers.addAll(account.getTransferCollection());   
    //Collections.sort(transfers);
    System.err.println("Transfers for #"+account.getAccountnumber()+" "+transfers.size());
    Collection<TransferSummary> transferSummaries = new ArrayList<>();
    for (Transfer transfer : transfers) transferSummaries.add(createTransferSummary(account, transfer));
    return new CheckingAccountDetail(account.getAccountnumber().toString(), BigDecimal.valueOf(account.getInterest()), transferSummaries);
    }
    
    public static CustomerDetail createCustomerDetail(Person customer) {
    return new CustomerDetail(
        customer.getCpr(),
        customer.getTitle(),
        customer.getFirstname(),
        customer.getLastname(),
        customer.getStreet(),
        customer.getPostalCode().getCode(),
        customer.getPostalCode().getDistrict(),
        ""+customer.getPhone(),
        customer.getEmail()
        );
    }
    
    public static Person createCustomerEntity(CustomerDetail detail) {
    Person person = Person.find(detail.getCpr());
    if (person == null) { 
      return new Person(
          detail.getCpr(),
          detail.getTitle(),
          detail.getFirstName(),
          detail.getLastName(),
          detail.getStreet(),
          Integer.parseInt(detail.getPhone())
          );
      }
    else {
      person.set(
          detail.getTitle(),
          detail.getFirstName(),
          detail.getLastName(),
          detail.getStreet(),
          detail.getPhone(),
          detail.getEmail()
          );
      return person;
      }
    }

}