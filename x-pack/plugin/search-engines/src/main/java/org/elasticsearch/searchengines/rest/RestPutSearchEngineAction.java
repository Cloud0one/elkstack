/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.searchengines.rest;

import org.elasticsearch.client.internal.node.NodeClient;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.action.RestToXContentListener;
import org.elasticsearch.searchengines.SearchEnginesPlugin;
import org.elasticsearch.searchengines.action.PutSearchEngineAction;

import java.io.IOException;
import java.util.List;

import static org.elasticsearch.rest.RestRequest.Method.PUT;

public class RestPutSearchEngineAction extends BaseRestHandler {

    @Override
    public String getName() {
        return "put_search_engine_action";
    }

    @Override
    public List<Route> routes() {
        return List.of(new RestHandler.Route(PUT, SearchEnginesPlugin.REST_BASE_PATH + "/{name}"));
    }

    @Override
    protected BaseRestHandler.RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
        PutSearchEngineAction.Request createEngineRequest = PutSearchEngineAction.Request.parseRestRequest(request);
        return channel -> client.execute(PutSearchEngineAction.INSTANCE, createEngineRequest, new RestToXContentListener<>(channel));
    }
}
