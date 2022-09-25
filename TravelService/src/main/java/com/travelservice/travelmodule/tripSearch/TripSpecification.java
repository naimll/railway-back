package com.travelservice.travelmodule.tripSearch;

import com.travelservice.advSearch.SearchCriteria;
import com.travelservice.advSearch.SearchOperation;
import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.station.Station;
import com.travelservice.travelmodule.Trip;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class TripSpecification implements Specification<Trip>{

        private SearchCriteria searchCriteria;

        public TripSpecification(final SearchCriteria searchCriteria){
            super();
            this.searchCriteria=searchCriteria;
        }

        @Override
        public Predicate toPredicate(Root<Trip> root,
                                     CriteriaQuery<?> query, CriteriaBuilder cb){
            String strToSearch = searchCriteria.getValue().toString().toLowerCase();

            switch(Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
                case CONTAINS:
                    if(searchCriteria.getFilterKey().equals("endPoint"))
                    {
                        return cb.like(cb.lower(routeJoin(root).
                                        <String>get(searchCriteria.getFilterKey())),
                                "%" + strToSearch + "%");
                    }
                    if(searchCriteria.getFilterKey().equals("stationName"))
                    {
                        return cb.like(cb.lower(stationJoin(root).
                                        <String>get(searchCriteria.getFilterKey())),
                                "%" + strToSearch + "%");
                    }
                    return cb.like(cb.lower(root
                                    .get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");

                case DOES_NOT_CONTAIN:
                    if(searchCriteria.getFilterKey().equals("stationName")){
                        return cb.notLike(cb.lower(routeJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                    }
                    return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

                case BEGINS_WITH:
                    if(searchCriteria.getFilterKey().equals("stationName")){
                        return cb.like(cb.lower(routeJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                    }
                    return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

                case DOES_NOT_BEGIN_WITH:
                    if(searchCriteria.getFilterKey().equals("stationName")){
                        return cb.notLike(cb.lower(routeJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                    }
                    return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

                case ENDS_WITH:
                    if(searchCriteria.getFilterKey().equals("stationName")){
                        return cb.like(cb.lower(routeJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                    }
                    return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

                case DOES_NOT_END_WITH:
                    if(searchCriteria.getFilterKey().equals("stationName")){
                        return cb.notLike(cb.lower(routeJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                    }
                    return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

                case EQUAL:
                    if(searchCriteria.getFilterKey().equals("stationName")){
                        return cb.equal(routeJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                    }
                    return cb.equal(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

                case NOT_EQUAL:
                    if(searchCriteria.getFilterKey().equals("stationName")){
                        return cb.notEqual(routeJoin(root).<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                    }
                    return cb.notEqual(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

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

        private Join<Trip, Route> routeJoin(Root<Trip> root){
            return root.join("routeTrip");
        }

        private Join<Trip, Station> stationJoin(Root<Trip> root){
            return root.join("stationName");
        }
    }


