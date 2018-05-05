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
package com.devamatre.designpatterns.behavioral.mediator.ch4;

import javax.swing.DefaultListModel;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-19 10:16:35 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class Mediator {
	
	private UploadButton uploadButton;
	private DownloadButton downloadButton;
	private DeleteButton deleteButton;
	private LocalList localList;
	private RemoteList remoteList;
	
	/**
	 * 
	 * @param uploadButton
	 */
	public void registerUploadButton(UploadButton uploadButton) {
		this.uploadButton = uploadButton;
	}
	
	/**
	 * 
	 * @param downloadButton
	 */
	public void registerDownloadButton(DownloadButton downloadButton) {
		this.downloadButton = downloadButton;
	}
	
	/**
	 * 
	 * @param deleteButton
	 */
	public void registerDeleteButton(DeleteButton deleteButton) {
		this.deleteButton = deleteButton;
	}
	
	/**
	 * 
	 * @param localList
	 */
	public void registerLocalList(LocalList localList) {
		this.localList = localList;
	}
	
	/**
	 * 
	 * @param remoteList
	 */
	public void registerRemoteList(RemoteList remoteList) {
		this.remoteList = remoteList;
	}
	
	/**
	 * 
	 * @param enable
	 */
	private void enableOrDisableButtons(final boolean enable) {
		uploadButton.setEnabled(enable);
		deleteButton.setEnabled(enable);
		downloadButton.setEnabled(enable);
	}
	
	/**
	 * 
	 */
	public void uploadItem() {
		int index = localList.getSelectedIndex();
		((DefaultListModel) localList.getModel()).remove(index);
		
		String selectedItem = localList.getSelectedValue().toString();
		((DefaultListModel) remoteList.getModel()).addElement(selectedItem);
		
		enableOrDisableButtons(false);
	}
	
	/**
	 * 
	 */
	public void downloadItem() {
		int index = remoteList.getSelectedIndex();
		((DefaultListModel) remoteList.getModel()).remove(index);
		
		String selectedItem = remoteList.getSelectedValue().toString();
		((DefaultListModel) localList.getModel()).addElement(selectedItem);
		
		enableOrDisableButtons(false);
	}
	
	/**
	 * 
	 */
	public void deleteItem() {
		int index = localList.getSelectedIndex();
		if (index >= 0) {
			((DefaultListModel) localList.getModel()).remove(index);
		}
		
		index = remoteList.getSelectedIndex();
		if (index >= 0) {
			((DefaultListModel) remoteList.getModel()).remove(index);
		}
		
		enableOrDisableButtons(false);
	}
	
	/**
	 * 
	 */
	public void selectLocalList() {
		remoteList.setSelectedIndex(-1);
		uploadButton.setEnabled(true);
		deleteButton.setEnabled(true);
		downloadButton.setEnabled(false);
	}
	
	/**
	 * 
	 */
	public void selectRemoteList() {
		localList.setSelectedIndex(-1);
		uploadButton.setEnabled(false);
		deleteButton.setEnabled(true);
		downloadButton.setEnabled(true);
	}
}