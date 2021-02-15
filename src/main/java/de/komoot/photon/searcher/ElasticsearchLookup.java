package de.komoot.photon.searcher;

import com.vividsolutions.jts.geom.Point;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author svantulden
 */
public interface ElasticsearchLookup {
    GetResponse lookup(String placeId);
}
