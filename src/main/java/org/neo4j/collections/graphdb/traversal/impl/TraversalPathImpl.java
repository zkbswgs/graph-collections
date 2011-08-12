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
package org.neo4j.collections.graphdb.traversal.impl;

import java.util.Iterator;
import java.util.LinkedList;

import org.neo4j.collections.graphdb.Connection;
import org.neo4j.collections.graphdb.TraversalPath;

public class TraversalPathImpl implements TraversalPath{

	private LinkedList<Connection<?>> connections;
	
	@Override
	public Iterator<Connection<?>> iterator() {
		return connections.iterator();
	}

	@Override
	public Connection<?> getFirstElement() {
		return connections.getFirst();
	}

	@Override
	public Connection<?> getLastElement() {
		return connections.getLast();
	}

	@Override
	public int length() {
		return connections.size();
	}
	
}
