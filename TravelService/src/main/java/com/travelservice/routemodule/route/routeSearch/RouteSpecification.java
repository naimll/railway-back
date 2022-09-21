package com.travelservice.routemodule.route.routeSearch;

import com.travelservice.advSearch.SearchCriteria;
import com.travelservice.advSearch.SearchOperation;
import com.travelservice.routemodule.attraction.Attraction;
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
                if(searchCriteria.getFilterKey().equals("stationName"))
                {
                    return cb.like(cb.lower(stationSPJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }
                if(searchCriteria.getFilterKey().equals("attractionName")){
                    return cb.like(cb.lower(attractionJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");
            case CONTAINS_MP:
                    return cb.like(cb.lower(stationMPJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
            case CONTAINS_EP:
                return cb.like(cb.lower(stationEPJoin(root).
                                <String>get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if(searchCriteria.getFilterKey().equals("stationName")){
                    return cb.notLike(cb.lower(stationSPJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                if(searchCriteria.getFilterKey().equals("attractionName")){
                    return cb.notLike(cb.lower(attractionJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN_MP:
                return cb.notLike(cb.lower(stationMPJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN_EP:
                return cb.notLike(cb.lower(stationEPJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case BEGINS_WITH:
                if(searchCriteria.getFilterKey().equals("stationName")){
                    return cb.like(cb.lower(stationSPJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                if(searchCriteria.getFilterKey().equals("attractionName")){
                    return cb.like(cb.lower(attractionJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case BEGINS_WITH_MP:
                return cb.like(cb.lower(stationMPJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");

            case BEGINS_WITH_EP:
                return cb.like(cb.lower(stationEPJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");

            case DOES_NOT_BEGIN_WITH:
                if(searchCriteria.getFilterKey().equals("stationName")){
                    return cb.notLike(cb.lower(stationSPJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                if(searchCriteria.getFilterKey().equals("attractionName")){
                    return cb.notLike(cb.lower(attractionJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case DOES_NOT_BEGIN_WITH_MP:
                return cb.notLike(cb.lower(stationMPJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");

            case DOES_NOT_BEGIN_WITH_EP:
                return cb.notLike(cb.lower(stationEPJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");

            case ENDS_WITH:
                if(searchCriteria.getFilterKey().equals("stationName")){
                    return cb.like(cb.lower(stationSPJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                if(searchCriteria.getFilterKey().equals("attractionName")){
                    return cb.like(cb.lower(attractionJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case ENDS_WITH_MP:
                return cb.like(cb.lower(stationMPJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case ENDS_WITH_EP:
                return cb.like(cb.lower(stationEPJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case DOES_NOT_END_WITH:
                if(searchCriteria.getFilterKey().equals("stationName")){
                    return cb.notLike(cb.lower(stationSPJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                if(searchCriteria.getFilterKey().equals("attractionName")){
                    return cb.notLike(cb.lower(attractionJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case DOES_NOT_END_WITH_MP:
                return cb.notLike(cb.lower(stationMPJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case DOES_NOT_END_WITH_EP:
                return cb.notLike(cb.lower(stationEPJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case EQUAL:
                if(searchCriteria.getFilterKey().equals("stationName")){
                    return cb.equal(stationSPJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                }
                if(searchCriteria.getFilterKey().equals("attractionName")){
                    return cb.equal(attractionJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                }
                return cb.equal(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case EQUAL_MP:
                return cb.equal(stationMPJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case EQUAL_EP:
                return cb.equal(stationEPJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case NOT_EQUAL:
                if(searchCriteria.getFilterKey().equals("stationName")){
                    return cb.notEqual(stationSPJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                }
                if(searchCriteria.getFilterKey().equals("attractionName")){
                    return cb.notEqual(attractionJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                }
                return cb.notEqual(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case NOT_EQUAL_MP:
                return cb.notEqual(stationMPJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case NOT_EQUAL_EP:
                return cb.notEqual(stationEPJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case NULL:
                return cb.isNull(root.<String> get(searchCriteria.getFilterKey()));

            case NOT_NULL:
                return cb.isNotNull(root.<String> get(searchCriteria.getFilterKey()));

            case GREATER_THAN:
                return cb.greaterThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case GREATER_THAN_EQUAL:
                return cb.greaterThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN:
                return cb.lessThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN_EQUAL:
                return cb.lessThanOrEqualTo(root.<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
        }
            return null;
    }

    private Join<Route, Station> stationSPJoin(Root<Route> root){
        return root.join("startPoint");
    }

    private Join<Route, Station> stationEPJoin(Root<Route> root){
        return root.join("endPoint");
    }

    private Join<Route, Station> stationMPJoin(Root<Route> root){
        return root.join("middlePoints");
    }

    private Join<Route, Attraction> attractionJoin(Root<Route> root){
        return root.join("attractions");
    }
}
