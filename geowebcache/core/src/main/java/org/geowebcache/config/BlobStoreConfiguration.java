package org.geowebcache.config;

import java.util.List;

public interface BlobStoreConfiguration extends BaseConfiguration {

    /**
     * Retrieves currently configured BlobStores.
     * @return A List of {@link BlobStoreInfo}s currently configured via backend configuration providers.
     */
    List<BlobStoreInfo> getBlobStores();
}
