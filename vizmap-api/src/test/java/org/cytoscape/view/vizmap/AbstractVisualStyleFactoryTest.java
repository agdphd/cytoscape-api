package org.cytoscape.view.vizmap;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractVisualStyleFactoryTest {
	
	protected VisualStyleFactory factory;
		

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testVisualStyleFactory() {
		assertNotNull(factory);
		
		final String title = "original style";
		final VisualStyle style = factory.createVisualStyle(title);
		
		assertNotNull(style);
		
		final VisualStyle copyStyle = factory.createVisualStyle(style);
		assertNotNull(copyStyle);

		assertEquals(title, copyStyle.getTitle());
	}
}
