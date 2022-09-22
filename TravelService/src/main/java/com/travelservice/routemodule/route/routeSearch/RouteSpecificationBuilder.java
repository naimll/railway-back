package com.travelservice.routemodule.route.routeSearch;

import com.travelservice.advSearch.SearchCriteria;
import com.travelservice.advSearch.SearchOperation;
import com.travelservice.routemodule.route.Route;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RouteSpecificationBuilder {
    private final List<SearchCriteria> params;

    public RouteSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final RouteSpecificationBuilder with(String key, String operation, Object value){
        params.add(new SearchCriteria(key,value,operation));
        return this;
    }

    public final RouteSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<Route> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<Route> result = new RouteSpecification(params.get(0));
        for(int index = 1; index < params.size(); index++){
            SearchCriteria criteria = params.get(index);
            result = SearchOperation.getDataOption(criteria.getDataOption())
                    == SearchOperation.ALL ? Specification.where(result)
                    .and(new RouteSpecification(criteria))
                    : Specification.where(result)
                    .or(new RouteSpecification((criteria)));
        }
        return result;
    }
}
