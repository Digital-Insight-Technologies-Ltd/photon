package de.komoot.photon.searcher;

import de.komoot.photon.query.LookupRequest;
import de.komoot.photon.query.ReverseRequest;
import org.json.JSONObject;

import java.util.List;

/**
 * @author svantulden
 */
public interface LookupRequestHandler<R extends LookupRequest> {
    JSONObject handle(R lookupRequest);
}
