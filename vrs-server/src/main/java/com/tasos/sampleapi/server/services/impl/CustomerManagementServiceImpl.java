/**
 * 
 */
package com.tasos.sampleapi.server.services.impl;

import com.tasos.sampleapi.server.services.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.tasos.sampleapi.common.dataobjects.CustomerDTO;
import com.tasos.sampleapi.server.domain.entities.Customer;
import com.tasos.sampleapi.server.domain.repositories.CustomerRepository;


/**
 * @author tasos
 */
@Service
public class CustomerManagementServiceImpl implements CustomerManagementService {

    @Autowired
    CustomerRepository customerRepo;

    /**
     * 
     */
    public CustomerManagementServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * @see com.tasos.sampleapi.server.sampleapi.CustomerManagementService#
     * createCustomer(dataobjects.CustomerDTO)
     */
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        final Customer customer = new Customer();
        applyCustomerDTOPropertiesToCustomer(customerDTO, customer);
        Customer savedCustomer = customerRepo.save(customer);
        applyCustomerPropertiesToCustomerDTO(customerDTO, savedCustomer);
        return customerDTO;
    }

    /*
     * (non-Javadoc)
     * @see com.tasos.sampleapi.server.sampleapi.CustomerManagementService#
     * updateCustomer(dataobjects.CustomerDTO)
     */
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer foundCustomer = customerRepo.findOne(customerDTO.getId());
        Assert.notNull(foundCustomer, "Customer with id:" + customerDTO.getId() + " does not exist");
        applyCustomerDTOPropertiesToCustomer(customerDTO, foundCustomer);
        Customer updatedCustomer = customerRepo.save(foundCustomer);
        applyCustomerPropertiesToCustomerDTO(customerDTO, updatedCustomer);
        return customerDTO;
    }

    /*
     * (non-Javadoc)
     * @see com.tasos.sampleapi.server.sampleapi.CustomerManagementService#
     * getCustomerById(dataobjects.CustomerDTO)
     */
    @Override
    public CustomerDTO getCustomerById(int customerId) {
        Customer foundCustomer = customerRepo.findOne(customerId);
        Assert.notNull(foundCustomer, "Customer with id:" + customerId + " does not exist");
        CustomerDTO foundCustomerDTO = new CustomerDTO();
        applyCustomerPropertiesToCustomerDTO(foundCustomerDTO, foundCustomer);
        return foundCustomerDTO;
    }

    /* private helper methods */

    private void applyCustomerDTOPropertiesToCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setBonusPoints(customerDTO.getBonusPoints());
    }

    private void applyCustomerPropertiesToCustomerDTO(CustomerDTO customerDTO, Customer customer) {
        customerDTO.setBonusPoints(customer.getBonusPoints());
        customerDTO.setId(customer.getId());
    }

}
