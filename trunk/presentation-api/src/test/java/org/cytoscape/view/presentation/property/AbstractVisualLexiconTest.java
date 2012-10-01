package org.cytoscape.view.presentation.property;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualLexiconNode;
import org.cytoscape.view.model.VisualProperty;

public abstract class AbstractVisualLexiconTest {
	
	
	protected void testTree(VisualLexicon lexicon) throws Exception {
		final VisualProperty<NullDataType> root = lexicon.getRootVisualProperty();
		assertNotNull(root);
		assertEquals(lexicon.getRootVisualProperty(), root);
		
		// test common methods
		try{
			Collection<VisualProperty<?>> result = lexicon.getAllDescendants(null);
		} catch(Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
		try{
			Collection<VisualProperty<?>> result = lexicon.getAllDescendants(new DefaultVisualizableVisualProperty("test", "Test Visual Property", CyNode.class));
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		
		final VisualLexiconNode rootNode = lexicon.getVisualLexiconNode(root);
		assertNotNull(rootNode);
		assertEquals(root, rootNode.getVisualProperty());
		
		final Collection<VisualLexiconNode> children = rootNode.getChildren();
		
		assertFalse(0 == children.size());
		traverse(children, lexicon);
		
		// Test adding
		final DoubleVisualProperty dummyVP = new DoubleVisualProperty(new Double(10), BasicVisualLexicon.ARBITRARY_DOUBLE_RANGE, "DUMMY", "Dummy VP", CyNode.class);
		
		
		try {
			((BasicVisualLexicon) lexicon).addVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR, root);
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		try {
			((BasicVisualLexicon) lexicon).addVisualProperty(dummyVP, null);
		} catch(Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
		
		try {
			((BasicVisualLexicon) lexicon).addVisualProperty(dummyVP, dummyVP);
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		((BasicVisualLexicon) lexicon).addVisualProperty(dummyVP, root);
	}
	
	

	private void traverse(final Collection<VisualLexiconNode> vpSet, VisualLexicon lexicon) {

		Collection<VisualLexiconNode> children = vpSet;
		Collection<VisualLexiconNode> nextChildren = new HashSet<VisualLexiconNode>();

		for (VisualLexiconNode child : children) {
			final VisualLexiconNode parent = child.getParent();
			
			System.out.println(parent.getVisualProperty().getDisplayName()
						+ "\thas_child\t" + child.getVisualProperty().getDisplayName());
			

			for (final VisualLexiconNode nextCh : child.getChildren())
				assertEquals(child, nextCh.getParent());

			nextChildren.addAll(child.getChildren());
			
		}

		if (nextChildren.size() == 0)
			return;
		else
			traverse(nextChildren, lexicon);
	}
	
	
	
}
