package de.komoot.photon.query;

import com.vividsolutions.jts.geom.Point;
import spark.Request;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author svantulden
 */
public class LookupRequestFactory {
    protected static HashSet<String> m_hsRequestQueryParams = new HashSet<>(Arrays.asList("lang", "placeId"));
    private final RequestLanguageResolver languageResolver;

    public LookupRequestFactory(List<String> supportedLanguages, String defaultLanguage) {
        this.languageResolver = new RequestLanguageResolver(supportedLanguages, defaultLanguage);
    }

    public <R extends LookupRequest> R create(Request webRequest) throws BadRequestException {
        for (String queryParam : webRequest.queryParams()) {
            if (!m_hsRequestQueryParams.contains(queryParam))
                throw new BadRequestException(400, "unknown query parameter '" + queryParam + "'.  Allowed parameters are: " + m_hsRequestQueryParams);
        }

        String language = languageResolver.resolveRequestedLanguage(webRequest);

        String placeId = webRequest.queryParams("placeId");
        LookupRequest lookupRequest = new LookupRequest(placeId, language);
        return (R) lookupRequest;
    }
}
