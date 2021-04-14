package com.junioroffers.offer;

import com.junioroffers.offer.domain.dao.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {
}
