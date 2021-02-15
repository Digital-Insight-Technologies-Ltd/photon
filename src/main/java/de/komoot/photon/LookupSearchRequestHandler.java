package de.komoot.photon;

import de.komoot.photon.query.BadRequestException;
import de.komoot.photon.query.LookupRequest;
import de.komoot.photon.query.LookupRequestFactory;
import de.komoot.photon.searcher.LookupRequestHandler;
import de.komoot.photon.searcher.LookupRequestHandlerFactory;
import de.komoot.photon.searcher.SimpleElasticsearchLookup;
import de.komoot.photon.utils.ConvertToGeoJson;
import org.elasticsearch.client.Client;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.RouteImpl;

import java.util.Arrays;
import java.util.List;

import static spark.Spark.halt;

/**
 * Created by Sachin Dole on 2/12/2015.
 */
public class LookupSearchRequestHandler<R extends LookupRequest> extends RouteImpl {
    private static final String DEBUG_PARAMETER = "debug";

    private final LookupRequestFactory photonRequestFactory;
    private final LookupRequestHandlerFactory requestHandlerFactory;
    private final ConvertToGeoJson geoJsonConverter;

    LookupSearchRequestHandler(String path, Client esNodeClient, String languages, String defaultLanguage) {
        super(path);
        List<String> supportedLanguages = Arrays.asList(languages.split(","));
        this.photonRequestFactory = new LookupRequestFactory(supportedLanguages, defaultLanguage);
        this.requestHandlerFactory = new LookupRequestHandlerFactory(new SimpleElasticsearchLookup(esNodeClient));
        this.geoJsonConverter = new ConvertToGeoJson();
    }

    @Override
    public String handle(Request request, Response response) {
        R lookupRequest = null;
        try {
            lookupRequest = photonRequestFactory.create(request);
        } catch (BadRequestException e) {
            JSONObject json = new JSONObject();
            json.put("message", e.getMessage());
            halt(e.getHttpStatus(), json.toString());
        }
        LookupRequestHandler<R> handler = requestHandlerFactory.createHandler(lookupRequest);
        JSONObject result = handler.handle(lookupRequest);
        if (result == null) {
            response.status(404);
            return "{}";
        }

        return result.toString();
    }
}