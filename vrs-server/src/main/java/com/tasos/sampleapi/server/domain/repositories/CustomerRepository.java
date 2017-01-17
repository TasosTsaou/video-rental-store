package com.tasos.sampleapi.server.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tasos.sampleapi.server.domain.entities.Customer;

/**
 * This interface is used to automatically create the implementation of the
 * CustomerRepository (DAO) and includes the CRUD operations inherited by
 * JpaRepository as well as the defined custom queries.
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    /**
     * Finds the <code>Customer</code> corresponding to the supplied identifier.
     *
     * @param customerId
     *            the identifier of the <code>Customer</code> to search for.
     * @return the <code>Customer</code> corresponding to the supplied
     *         identifier.
     */
    Customer findById(Integer customerId);
    

}
