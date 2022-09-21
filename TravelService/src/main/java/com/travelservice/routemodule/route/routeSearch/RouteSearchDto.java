package com.travelservice.routemodule.route.routeSearch;

import com.travelservice.advSearch.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteSearchDto {
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

}
