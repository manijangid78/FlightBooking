package com.example.FlightBooking.repository;

import com.example.FlightBooking.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;

public class FlightCustomRepositoryImpl implements FlightCustomRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Object> findFlights(String source, String destination, Date date) {
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery(Flight.class);

        Root<Flight> flightRoot=cq.from(Flight.class);

//        List<Predicate> predicates = new ArrayList<>();

        Predicate predicate1 = cb.equal(flightRoot.get("fromLocation"),source);
        Predicate predicate2 = cb.equal(flightRoot.get("toLocation"),destination);
        Predicate predicate3 = cb.like(flightRoot.get("connectingLocations"),"%"+source+"%");
        Predicate predicate4 = cb.like(flightRoot.get("connectingLocations"),"%"+destination+"%");
        Predicate predicate5 = cb.or(predicate1,predicate3);
        Predicate predicate6 = cb.or(predicate2,predicate4);
        Predicate predicate8 = cb.equal(flightRoot.get("date"),date);
//        Predicate predicate7 = cb.and(predicate5,predicate6,predicate8);

        cq.select(flightRoot).where(cb.and(predicate5,predicate6,predicate8));
        List<Object> resultList = entityManager.createQuery(cq).getResultList();
        return resultList;
    }

}