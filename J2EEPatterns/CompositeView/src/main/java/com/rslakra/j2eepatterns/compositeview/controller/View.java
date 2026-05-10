package com.rslakra.j2eepatterns.compositeview.controller;

import java.util.HashMap;

/**
 * The internal representation of a view from the views.xml file
 */
public class View {
	/** the containers in this view, mapped by name */
	private HashMap containers;
	
	/** the template for this view */
	private String template;
	
	/**
	 * the internal representation of a container in a view
	 */
	class Container {
		/** the includes in this container, mapped by name */
		private HashMap includes;
		
		Container() {
			includes = new HashMap();
		}
		
		/**
		 * Add an include to this container
		 */
		void addInclude(String name, String url) {
			includes.put(name, url);
		}
		
		/**
		 * Get the url associated with a given include
		 */
		String getUrl(String name) {
			return (String) includes.get(name);
		}
	}
	
	/**
	 * Create a new view with the given template
	 */
	public View(String template) {
		containers = new HashMap();
		this.template = template;
	}
	
	/**
	 * Add an include to this view, given a container within the view,
	 * the name of the include and the associated url
	 */
	public void addInclude(String container, String name, String url) {
		// find the container
		Container c = (Container) containers.get(container);
		
		// if the container doesn't exist, create it
		if (c == null) {
			c = new Container();
			containers.put(container, c);
		}
		
		// add the include to the container
		c.addInclude(name, url);
	}
	
	/**
	 * Get the url associated with a named view
	 */
	public String getUrl(String container, String name) {
		// find the container
		Container c = (Container) containers.get(container);
		if (c == null) {
			return null;
		}
		
		// get the url
		return c.getUrl(name);
	}
	
	/**
	 * Determine if this view has a container with the given name
	 */
	public boolean hasContainer(String container) {
		return containers.containsKey(container);
	}
	
	/**
	 * Get the template associated with this view
	 */
	public String getTemplate() {
		return template;
	}
}