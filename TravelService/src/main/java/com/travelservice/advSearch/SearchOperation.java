package com.travelservice.advSearch;

public enum SearchOperation {
    CONTAINS, CONTAINS_MP, CONTAINS_EP, DOES_NOT_CONTAIN, DOES_NOT_CONTAIN_MP, DOES_NOT_CONTAIN_EP,
    EQUAL, EQUAL_MP, EQUAL_EP, NOT_EQUAL, NOT_EQUAL_MP, NOT_EQUAL_EP, BEGINS_WITH, BEGINS_WITH_MP, BEGINS_WITH_EP,
    DOES_NOT_BEGIN_WITH, DOES_NOT_BEGIN_WITH_MP, DOES_NOT_BEGIN_WITH_EP, ENDS_WITH, ENDS_WITH_MP, ENDS_WITH_EP,
    DOES_NOT_END_WITH, DOES_NOT_END_WITH_MP, DOES_NOT_END_WITH_EP, NULL, NOT_NULL, GREATER_THAN, GREATER_THAN_EQUAL,
    LESS_THAN, LESS_THAN_EQUAL, ANY, ALL;


    public static final String[] SIMPLE_OPERATION_SET = {
            "cn", "cnmp", "cnep","nc", "ncmp", "ncep", "eq", "eqmp", "eqep",
            "ne", "nemp", "neep","bw", "bwmp", "bwep", "bn", "bnmp", "bnep",
            "ew", "ewmp", "ewep","en", "enmp", "enep","nu",
            "nn", "gt", "ge", "lt", "le"
    };

    public static SearchOperation getDataOption(final String dataOption)
    {
        switch(dataOption){
            case "all":
                return ALL;
            case "any":
                return ANY;
            default:
                return null;
        }
    }

    public static SearchOperation getSimpleOperation(final String input){
        switch(input){
            case "cn":
                return CONTAINS;
            case "cnmp":
                return CONTAINS_MP;
            case "cnep":
                return CONTAINS_EP;
            case "nc":
                return DOES_NOT_CONTAIN;
            case "ncmp":
                return DOES_NOT_CONTAIN_MP;
            case "ncep":
                return DOES_NOT_CONTAIN_EP;
            case "eq":
                return EQUAL;
            case "eqmp":
                return EQUAL_MP;
            case "eqep":
                return EQUAL_EP;
            case "ne":
                return NOT_EQUAL;
            case "nemp":
                return NOT_EQUAL_MP;
            case "neep":
                return NOT_EQUAL_EP;
            case "bw":
                return BEGINS_WITH;
            case "bwmp":
                return BEGINS_WITH_MP;
            case "bwep":
                return BEGINS_WITH_EP;
            case "bn":
                return DOES_NOT_BEGIN_WITH;
            case "bnmp":
                return DOES_NOT_BEGIN_WITH_MP;
            case "bnep":
                return DOES_NOT_BEGIN_WITH_EP;
            case "ew":
                return ENDS_WITH;
            case "ewmp":
                return ENDS_WITH_MP;
            case "ewep":
                return ENDS_WITH_EP;
            case "en":
                return DOES_NOT_END_WITH;
            case "enmp":
                return DOES_NOT_END_WITH_MP;
            case "enep":
                return DOES_NOT_END_WITH_EP;
            case "nu":
                return NULL;
            case "nn":
                return NOT_NULL;
            case "gt":
                return GREATER_THAN;
            case "ge":
                return GREATER_THAN_EQUAL;
            case "lt":
                return LESS_THAN;
            case "le":
                return LESS_THAN_EQUAL;
            default:
                return null;
        }
    }
}
