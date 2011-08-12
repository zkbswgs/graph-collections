/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.collections.graphdb;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.RelationshipType;

public interface BinaryEdgeType extends EdgeType {
	
	public RelationshipType getRelationshipType();
	
	public BinaryEdge createEdge(Vertex startVertex, Vertex endVertex);
	
	public Iterable<BinaryEdge> getEdges(Vertex vertex);
	
	public Iterable<BinaryEdge> getEdges(Vertex vertex, Direction dir);
	
	public boolean hasEdge(Vertex vertex, Direction dir);
	
	public BinaryEdge getSingleBinaryEdge(Vertex vertex, Direction dir);
	
	public Connector<SurjectiveConnectionMode> getStartConnector();
	
	public Connector<SurjectiveConnectionMode> getEndConnector();
	
}
