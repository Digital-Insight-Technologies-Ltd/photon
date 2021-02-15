package de.komoot.photon.searcher;

import de.komoot.photon.query.LookupRequest;
import de.komoot.photon.query.PhotonRequest;
import de.komoot.photon.query.TagFilterQueryBuilder;
import de.komoot.photon.utils.ConvertToJson;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.json.JSONObject;

import java.util.List;

/**
 * @author svantulden
 */
public abstract class AbstractLookupRequestHandler<R extends LookupRequest> implements LookupRequestHandler<R> {
    private final ElasticsearchLookup elasticsearchLookup;

    public AbstractLookupRequestHandler(ElasticsearchLookup elasticsearchLookup) {
        this.elasticsearchLookup = elasticsearchLookup;
    }

    @Override
    public JSONObject handle(R lookupRequest) {
        GetResponse results = elasticsearchLookup.lookup(lookupRequest.getPlaceId());
        if (results.isExists()) {
            JSONObject resultJsonObjects = new ConvertToJson(lookupRequest.getLanguage()).convertGet(results);
            return resultJsonObjects;
        }
        else {
            return null;
        }
    }
}
