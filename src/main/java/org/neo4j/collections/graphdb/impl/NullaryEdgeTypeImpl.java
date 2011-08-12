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
package org.neo4j.collections.graphdb.impl;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.collections.graphdb.BijectiveConnectionMode;
import org.neo4j.collections.graphdb.ConnectionMode;
import org.neo4j.collections.graphdb.ConnectorType;
import org.neo4j.collections.graphdb.DatabaseService;
import org.neo4j.collections.graphdb.Connector;
import org.neo4j.collections.graphdb.NullaryEdgeType;
import org.neo4j.graphdb.Node;

public class NullaryEdgeTypeImpl extends EdgeTypeImpl implements NullaryEdgeType{

	public NullaryEdgeTypeImpl(Node node) {
		super(node);
	}

	protected static Class<?> getImplementationClass(){
		try{
			return Class.forName("org.neo4j.collections.graphdb.impl.NullaryEdgeTypeImpl");
		}catch(ClassNotFoundException cce){
			throw new RuntimeException(cce);
		}
	}

	public static NullaryEdgeTypeImpl getOrCreateInstance(DatabaseService db){
		VertexTypeImpl vertexType = new VertexTypeImpl(getOrCreateByDescriptor(new TypeNodeDescriptor(db, "NullaryEdgeType", getImplementationClass())));
		return new NullaryEdgeTypeImpl(vertexType.getNode());
	}

	@Override
	public Set<Connector<?>> getConnectors() {
		Set<Connector<?>> roles = new HashSet<Connector<?>>();
		roles.add(getConnector());
		return roles;
	}

	@Override
	public <T extends ConnectionMode> Connector<T> getConnector(
			ConnectorType<T> edgeRoleType) {
		return new Connector<T>(edgeRoleType, this);
	}

	@Override
	public Connector<BijectiveConnectionMode> getConnector() {
		return new Connector<BijectiveConnectionMode>(NullaryConnectorTypeImpl.NullaryConnectorType.getOrCreateInstance(getDb()), this);
	}
	
}
