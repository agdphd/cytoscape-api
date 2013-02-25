package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

/** 
 * Interface for classes that deal with reading out and writing back 
 * <code>Tunable</code>s and their properties. TunableHandler classes
 * are provided to the system via {@link TunableHandlerFactory} services.
 * Different presentation mediums (e.g. Swing GUI, command line, etc.) 
 * will demand different TunableHandler and {@link TunableHandlerFactory}
 * implementations to display, parse, get, and set the necessary 
 * {@link Tunable} values. Each different type of object (String, Double,
 * {@link org.cytoscape.work.util.ListSingleSelection} will need its own
 * TunableHandler/{@link TunableHandlerFactory} pair to process
 * fields/methods of that type.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 */
public interface TunableHandler {

	/**
	 * Returns an object describing a field / get method annotated with 
	 * {@link Tunable} or null if no field / get method has been associated with this handler.
	 * @return an object describing a field / get method annotated with @Tunable 
	 * or null if no field has been associated with this handler
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException 
	 */
	Object getValue() throws IllegalAccessException, InvocationTargetException;

	/** 
	 * Attempts to set the value of the associated field or set method that has been
	 * annotated with {@link Tunable}.
	 * @param newValue the value to be written into field or set method annotated with {@link Tunable} 
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException 
	 */
	void setValue(final Object newValue) throws IllegalAccessException, InvocationTargetException;

	/**
	 * Returns the associated <code>Tunable</code>'s description.
	 * @return the associated <code>Tunable</code>'s description
	 */
	String getDescription();

	/**
	 * Returns the associated <code>Tunable</code>'s groups or nesting hierarchy.
	 * @return the associated <code>Tunable</code>'s groups or nesting hierarchy
	 */
	String[] getGroups();
	
	/**
	 * Returns the associated <code>Tunable</code>'s gravity value.
	 * @return the associated <code>Tunable</code>'s gravity value
	 */
	double getGravity();

	/**
	 * Returns true if the associated <code>Tunable</code> allows switching 
	 * of mutually exclusive nested children, else false.
	 * @return true if the associated <code>Tunable</code> allows switching 
	 * of mutually exclusive nested children, else false
	 */
	boolean controlsMutuallyExclusiveNestedChildren();

	/**
	 * Returns the name of the key that determines the selection of which controlled
	 * nested child is currently presented, or the empty string if
	 * controlsMutuallyExclusiveNestedChildren() returns false.
	 * @return the name of the key that determines the selection of which controlled
	 * nested child is currently presented, or the empty string if
	 * controlsMutuallyExclusiveNestedChildren() returns false.
	 */
	String getChildKey();

	/**
	 *  Returns the dependsOn property of the associated <code>Tunable</code>.
	 *  @return the dependsOn property of the tunable
	 */
	String dependsOn();

	/**
	 *  Returns a name representing a tunable property.
	 *  @return a name representing a tunable property
	 */
	String getName();

	/**
	 * Returns the name of the underlying class of the tunable followed by 
	 * a dot and the name of the tunable field or getter/setter root name.
	 * Please note that the returned String will always contain a single embedded dot.
	 * @return the name of the underlying class of the tunable followed by 
	 * a dot and the name of the tunable field or getter/setter root name.
	 */
	String getQualifiedName();

	/**
	 *  Returns the parsed result from <code>Tunable.getParams()</code>.
	 *  @return the parsed result from <code>Tunable.getParams()</code>
	 */
	Properties getParams();

	/**
	 * Updates an annotated object with the current value as retrieved from the 
	 * the user interface generated by this handler.
	 */
	void handle();

	/**
	 *  Returns the listenForChange property of the associated <code>Tunable</code>.
	 *  @return the listenForChange property of the tunable
	 */
	String[] listenForChange();

	/**
	 *  Returns the class type of the field or method annotated by <code>Tunable</code>.
	 *  @return the class type of the field or method annotated by <code>Tunable</code>.
	 */
	Class<?> getType();
}
