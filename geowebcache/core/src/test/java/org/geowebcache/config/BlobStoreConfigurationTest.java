package org.geowebcache.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.io.FileUtils;
import org.geowebcache.grid.GridSetBroker;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 *
 */
public class BlobStoreConfigurationTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();
    private File configDir;
    private File configFile;
    private BlobStoreConfiguration config;

    @Before
    public void before() throws Exception {
        if(configFile==null) {
            // create a temp XML config
            configDir = temp.getRoot();
            configFile = temp.newFile(XMLConfiguration.DEFAULT_CONFIGURATION_FILE_NAME);
            // copy the example XML to the temp config file
            URL source = XMLConfiguration.class.getResource("geowebcache_190.xml");
            FileUtils.copyURLToFile(source, configFile);
        }
        // initialize the config with an XMLFileResourceProvider that uses the temp config file
        GridSetBroker gridSetBroker = new GridSetBroker(true, true);
        ConfigurationResourceProvider configProvider =
            new XMLFileResourceProvider(XMLConfiguration.DEFAULT_CONFIGURATION_FILE_NAME,
                (WebApplicationContext)null, configDir.getAbsolutePath(), null);
        config = new XMLConfiguration(null, configProvider);
        config.initialize(gridSetBroker);
    }

    @Test
    public void testBlobStoreConfigIsLoaded() throws Exception {
        // get the blobstores from the config (from test resource geowebcache_190.xml)
        final List<BlobStoreInfo> blobStores = config.getBlobStores();
        assertEquals("Unexpected number of BlobStoreInfo elements configured", 1, blobStores.size());
        // get the 1 configured BlobStoreInfo
        BlobStoreInfo info = blobStores.get(0);
        assertFalse("Unexpected BlobStoreInfo default setting", info.isDefault());
        assertFalse("Unexpected BlobStoreInfo enabled setting", info.isEnabled());
        assertTrue("Unexpected BlobeStoreInfo class type", FileBlobStoreInfo.class.isAssignableFrom(info.getClass()));
        // cast the info to a FileBlobStoreInfo
        final FileBlobStoreInfo fileInfo = FileBlobStoreInfo.class.cast(info);
        assertEquals("Unexpected FileBlobStoreInfo filesystem block size", 4096, fileInfo.getFileSystemBlockSize());
        assertEquals("Unexpected FileBlobStoreInfo location value", "/tmp/defaultCache", fileInfo.getBaseDirectory());
    }
}
