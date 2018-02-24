/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.designpatterns.behavioral.mediator.ch1;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2005-05-24 03:05:01 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class LowerCaseTitle implements Title {
	// private String star;
	// private String lowerCaseTitle;
	private String title;
	private Mediator mediator;

	/**
	 * 
	 * @param title
	 * @param mediator
	 */
	public LowerCaseTitle(String title, Mediator mediator) {
		this.title = title.toLowerCase();
		this.mediator = mediator;
		this.mediator.changeTitle(this);
	}

	/**
	 * 
	 * @param title
	 * @see com.devamatre.designpatterns.behavioral.mediator.ch1.Title#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		this.title = title.toLowerCase();
		this.mediator.changeTitle(this);
	}

	public String getTitle() {
		return title;
	};

	// public LowerCaseTitle(String title, Mediator mediator)
	// {
	// setTitle(title);
	// resetTitle();
	// this.mediator = mediator;
	// this.mediator.setLowerCaseTitle(this);
	// }
	//
	// public LowerCaseTitle(Title title, Mediator mediator)
	// {
	// this(title.getTitle(), mediator);
	// }

	// public void resetTitle()
	// {
	// this.setLowercaseTitle(this.getTitle().toLowerCase());
	// }
	//
	// public void resetTitle(String title)
	// {
	// this.setTitle(title);
	// this.resetTitle();
	// }
	//
	// public void setSuperTitleLowercase()
	// {
	// this.setTitle(this.getLowercaseTitle());
	// mediator.changeTitle(this);
	// }
	//
	// public String getLowercaseTitle() {
	// return lowerCaseTitle;
	// }
	//
	// private void setLowercaseTitle(String lowerCaseTitle) {
	// this.lowerCaseTitle= lowerCaseTitle;
	// }
}
