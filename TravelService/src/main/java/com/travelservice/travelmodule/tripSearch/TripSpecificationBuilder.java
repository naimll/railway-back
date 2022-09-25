package com.travelservice.travelmodule.tripSearch;

import com.travelservice.advSearch.SearchCriteria;
import com.travelservice.advSearch.SearchOperation;
import com.travelservice.travelmodule.Trip;
import com.travelservice.travelmodule.tripSearch.TripSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TripSpecificationBuilder {
        private final List<SearchCriteria> params;

        public TripSpecificationBuilder(){
            this.params = new ArrayList<>();
        }

        public final TripSpecificationBuilder with(String key, String operation, Object value){
            params.add(new SearchCriteria(key,value,operation));
            return this;
        }

        public final TripSpecificationBuilder with(SearchCriteria searchCriteria){
            params.add(searchCriteria);
            return this;
        }

        public Specification<Trip> build(){
            if(params.size() == 0){
                return null;
            }

            Specification<Trip> result = new TripSpecification(params.get(0));
            for(int index = 1; index < params.size(); index++){
                SearchCriteria criteria = params.get(index);
                result = SearchOperation.getDataOption(criteria.getDataOption())
                        == SearchOperation.ALL ? Specification.where(result)
                        .and(new TripSpecification(criteria))
                        : Specification.where(result)
                        .or(new TripSpecification((criteria)));
            }
            return result;
        }

}
