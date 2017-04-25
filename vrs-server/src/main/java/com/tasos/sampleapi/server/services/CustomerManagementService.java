package com.tasos.sampleapi.server.services;

import com.tasos.sampleapi.common.dataobjects.CustomerDTO;

public interface CustomerManagementService {

    public CustomerDTO createCustomer(CustomerDTO customerDTO);

    public CustomerDTO updateCustomer(CustomerDTO customerDTO);

    public CustomerDTO getCustomerById(int integer);
}
