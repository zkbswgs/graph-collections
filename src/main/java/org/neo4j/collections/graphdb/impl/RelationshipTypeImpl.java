/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.collections.graphdb.impl;

import org.neo4j.collections.graphdb.EnhancedRelationshipType;
import org.neo4j.collections.graphdb.GraphDatabaseService;
import org.neo4j.collections.graphdb.Node;
import org.neo4j.collections.graphdb.PropertyType;
import org.neo4j.collections.graphdb.Relationship;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.PropertyContainer;

public class RelationshipTypeImpl extends NodeLikeImpl implements EnhancedRelationshipType{

	private final org.neo4j.graphdb.RelationshipType relType;
	private final GraphDatabaseService graphDb;
	private Node node = null;

	enum RelTypes implements org.neo4j.graphdb.RelationshipType{
		RELTYPE_SUBREF
	}
	
	RelationshipTypeImpl(org.neo4j.graphdb.RelationshipType relType, GraphDatabaseService graphDb){
		this.relType = relType;
		this.graphDb = graphDb;
	}
	
	public String name() {
		return relType.name();
	}

	public org.neo4j.graphdb.RelationshipType getRelationshipType(){
		return relType;
	}
	
	public GraphDatabaseService getGraphDatabaseExt() {
		return graphDb;
	}

	public GraphDatabaseService getGraphDatabase() {
		return graphDb;
	}

	
	public org.neo4j.graphdb.Node getNode(){
		if(node == null){
			Relationship subRefRel = getGraphDatabaseExt().getReferenceNodeExt().getSingleRelationshipExt(RelTypes.RELTYPE_SUBREF, Direction.OUTGOING);
			Node subRef = null;
			if(subRefRel == null){
				Node n = getGraphDatabaseExt().createNodeExt();
				getGraphDatabaseExt().getReferenceNode().createRelationshipTo(n, RelTypes.RELTYPE_SUBREF);
				subRef = n;
			}else{
				subRef = (Node)subRefRel.getEndRelationshipContainer();
			}
			if(subRef.hasProperty(relType.name())){
				node = getGraphDatabaseExt().getNodeByIdExt((Long)subRef.getProperty(relType.name()));
			}else{
				Node n = getGraphDatabaseExt().createNodeExt();
				subRef.setProperty(relType.name(),n.getId());
			}
		}
		return node;
	}


	@Override
	public Iterable<PropertyType<?>> getPropertyTypes() {
		return PropertyType.getPropertyTypes(this, getGraphDatabaseExt());
	}

	@Override
	public PropertyContainer getPropertyContainer() {
		return getNode();
	}
}
