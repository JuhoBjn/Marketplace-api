package com.example.Marketplace;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long>{
    @Override
    ArrayList<Listing> findAll();
    ArrayList<Listing> findAllByUserID(long UserID);
    Listing findByListingID(long listingID);
    Long deleteByListingID(long ListingID);
    Long deleteAllByUserID(long UserID);
}
