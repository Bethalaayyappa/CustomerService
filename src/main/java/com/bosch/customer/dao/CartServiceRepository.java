/**
 * 
 */
package com.bosch.customer.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bosch.customer.entity.Carts;

/**
 * @author BEN6KOR
 *
 */
@Transactional
public interface CartServiceRepository extends MongoRepository<Carts, String> {

	Carts findById(@Param("id") String id);

	Carts findByCustomerId(@Param("customerId") String customerId);

}
