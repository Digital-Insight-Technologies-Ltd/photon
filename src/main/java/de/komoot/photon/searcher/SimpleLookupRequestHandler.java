package de.komoot.photon.searcher;

import de.komoot.photon.query.LookupRequest;
import de.komoot.photon.query.ReverseQueryBuilder;
import de.komoot.photon.query.ReverseRequest;
import de.komoot.photon.query.TagFilterQueryBuilder;

/**
 * @author svantulden
 */
public class SimpleLookupRequestHandler extends AbstractLookupRequestHandler<LookupRequest> implements LookupRequestHandler<LookupRequest> {
    public SimpleLookupRequestHandler(ElasticsearchLookup elasticsearchLookup) {
        super(elasticsearchLookup);
    }
}
