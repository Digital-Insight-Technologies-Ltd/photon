package de.komoot.photon.searcher;

import com.vividsolutions.jts.geom.Point;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

/**
 * @author svantulden
 */
public class SimpleElasticsearchLookup implements ElasticsearchLookup {
    private Client client;

    public SimpleElasticsearchLookup(Client client) {
        this.client = client;
    }

    @Override
    public GetResponse lookup(String placeId) {
        TimeValue timeout = TimeValue.timeValueSeconds(7);

        GetRequestBuilder builder = client.prepareGet().setIndex("photon").setType("place").setId(placeId);

        return builder.execute().actionGet();
    }
}
