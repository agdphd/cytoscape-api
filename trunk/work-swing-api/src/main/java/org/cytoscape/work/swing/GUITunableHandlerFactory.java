package org.cytoscape.work.swing;

import org.cytoscape.work.TunableHandlerFactory;

/**
 * A specialization fo TunableHandlerFactory for GUITunableHandler. 
 * @param <T>
 * @CyAPI.Spi.Interface
 */
public interface GUITunableHandlerFactory<T extends GUITunableHandler> extends TunableHandlerFactory<T> {

}
