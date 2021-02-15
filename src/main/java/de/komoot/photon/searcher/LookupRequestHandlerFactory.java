package de.komoot.photon.searcher;

import de.komoot.photon.searcher.LookupRequestHandler;
import de.komoot.photon.query.LookupRequest;
import de.komoot.photon.query.PhotonRequest;

/**
 * Created by Sachin Dole on 2/20/2015.
 */
public class LookupRequestHandlerFactory {

    private final ElasticsearchLookup elasticsearchLookup;

    public LookupRequestHandlerFactory(ElasticsearchLookup elasticsearchLookup) {
        this.elasticsearchLookup = elasticsearchLookup;
    }

    /**
     * Given a {@link PhotonRequest} create a
     * {@link PhotonRequestHandler handler} that can execute the elastic search
     * search.
     */
    public <R extends LookupRequest> LookupRequestHandler<R> createHandler(R request) {
        return (LookupRequestHandler<R>) new SimpleLookupRequestHandler(elasticsearchLookup);
    }
}
