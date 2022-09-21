package com.travelservice.routemodule.route.routeSearch;

import com.travelservice.advSearch.SearchCriteria;
import com.travelservice.advSearch.SearchOperation;
import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.station.Station;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class RouteSpecification implements Specification<Route> {

    private  SearchCriteria searchCriteria;

    public RouteSpecification(final SearchCriteria searchCriteria){
        super();
        this.searchCriteria=searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Route> root,
                                 CriteriaQuery<?> query, CriteriaBuilder cb){
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch(Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
            case CONTAINS:
                if(searchCriteria.getFilterKey().equals("stationName")){
                    return cb.notLike(cb.lower(stationJoin(root).
                            <String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }
        }
            return null;
    }

    private Join<Route, Station> stationJoin(Root<Route> root){
        return root.join("stationName");
    }
}
