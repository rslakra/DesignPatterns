package com.devamatre.j2eepatterns.resourcepool;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A generic resource pool implementation based on factories.
 */
public class ResourcePool {
	/** the factory */
	private ResourceFactory factory;
	
	/** counters */
	private int maxObjects;
	private int curObjects;
	private boolean quit;
	
	/** resources we have loaned out */
	private Set outResources;
	
	/** resources we have waiting */
	private List inResources;
	
	/**
	 * Create a new resource pool that generates objects using the
	 * provided factory, and keeps up to maxObjects in the pool.
	 */
	public ResourcePool(ResourceFactory factory, int maxObjects) {
		this.factory = factory;
		this.maxObjects = maxObjects;
		
		curObjects = 0;
		
		outResources = new HashSet(maxObjects);
		inResources = new LinkedList();
	}
	
	/**
	 * Retrieve a resource from the pool
	 */
	public synchronized Object getResource() throws Exception {
		while (!quit) {
			
			// first, try to return a resource from the pool
			if (!inResources.isEmpty()) {
				Object o = inResources.remove(0);
				
				// if the resource is invalid, create a replacement
				if (!factory.validateResource(o))
					o = factory.createResource();
				
				outResources.add(o);
				return o;
			}
			
			// next, create a new resource if we haven't
			// reached the limit yet
			if (curObjects < maxObjects) {
				Object o = factory.createResource();
				outResources.add(o);
				curObjects++;
				
				return o;
			}
			
			// if no resources are available, wait until one
			// is returned
			try {
				wait();
			} catch (Exception ex) {
			}
		}
		
		// pool is destroyed
		return null;
	}
	
	/**
	 * Return a resource to the pool.
	 */
	public synchronized void returnResource(Object o) {
		
		// Something is wrong. Just give up.
		if (!outResources.remove(o))
			return;
		
		inResources.add(o);
		notify();
	}
	
	/**
	 * Destroy the pool
	 */
	public synchronized void destroy() {
		quit = true;
		notifyAll();
	}
}
