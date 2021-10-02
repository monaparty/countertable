package me.monaparty.api;

import java.util.List;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;

public interface CounterpartyAPI {

    @JsonRpcMethod("get_issuances")
    List<GetIssuancesResult> getIssuances(
        @JsonRpcParam(value = "filters") Filter[] filterList,
        @JsonRpcParam(value = "filterop") String filterop,
        @JsonRpcParam(value = "order_by") String orderBy,
        @JsonRpcParam(value = "order_dir") String orderDir,
        @JsonRpcParam(value = "start_block") Long startBlock,
        @JsonRpcParam(value = "end_block") Long endBlock,
        @JsonRpcParam(value = "status") String status,
        @JsonRpcParam(value = "limit") Long limit,
        @JsonRpcParam(value = "offset") Long offset
        );

}