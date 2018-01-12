package org.geowebcache.config;

import org.geowebcache.layer.TileLayer;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

/**
 * Applies default values to a layer that has not been fully initialized.
 *
 */
public interface DefaultingConfiguration extends BaseConfiguration {

    /**
     * TileLayerConfiguration objects lacking their own defaults can delegate to this
     * @param layer
     */
    void setDefaultValues(TileLayer layer);


    /**
     * Whether the configuration is capable of saving the provided tile layer.
     *
     * @param tl a tile layer to be added or saved
     * @return {@code true} if this configuration is capable of saving the given tile layer,
     *         {@code false} otherwise (usually this check is based on an instanceof check, as
     *         different configurations may be specialized on different kinds of layers).
     */
    boolean canSave(TileLayer tl);

    /**
     * Get all {@link TileLayer}s included in a DefaultingConfiguration.
     *
     * @return an unmodifiable list of layers, may be empty, but not null.
     */
    Collection<? extends TileLayer> getLayers();

    /**
     * Get the names of all TileLayers configured
     *
     * @return The set of all TileLayers configured. May be empty, but not null.
     */

    Set<String> getLayerNames();
    /**
     * Adds the given tile layer to this configuration, provided
     * {@link #canSave(TileLayer) canSave(tl) == true}.
     *
     * @param tl the tile layer to add to the configuration
     * @throws IllegalArgumentException
     *             if this configuration is not able of saving the specific type of layer given, or
     *             if any required piece of information is missing or invalid in the layer (for
     *             example, a missing or duplicated name or id, etc).
     */
    void addLayer(TileLayer tl) throws IllegalArgumentException;

    /**
     * Removes the given tile layer from this configuration
     * @param layerName name of the layer to remove
     * @throws IllegalArgumentException If this configuration is not able to remove the layer.
     * @throws NoSuchElementException If the layer with the specified name doesn't exist.
     */
    void removeLayer(String layerName) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Gets a single {@link TileLayer} from the configuration, using the layer's unique name as a key.
     *
     * @param layerName the layer name
     * @return the layer named {@code layerIdent} or {@code null} if no such layer exists in this
     *         configuration
     */
    Optional<TileLayer> getLayer(String layerName);

    /**
     * Replaces an existing tile layer of the same name with this tile layer.
     *
     * @param tl the modified tile layer. Its name must be the same as a tile layer that already exists.
     * @throws NoSuchElementException If no tile layer with a matching name exists.
     */
    void modifyLayer(TileLayer tl) throws NoSuchElementException;
}