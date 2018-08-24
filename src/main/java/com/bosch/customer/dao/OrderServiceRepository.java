/**
 * 
 */
package com.bosch.customer.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bosch.customer.entity.Orders;

/**
 * @author BEN6KOR
 *
 */
@Transactional
public interface OrderServiceRepository extends MongoRepository<Orders, String> {

	Orders findById(@Param("id") String id);

}
