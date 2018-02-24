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
	private UploadButton btnUpload;

	private DownloadButton btnDownload;

	private DeleteButton btnDelete;

	private LocalList localList;

	private RemoteList remoteList;

	public void registerUploadButton(UploadButton inp_ib) {
		btnUpload = inp_ib;
	}

	public void registerDownloadButton(DownloadButton inp_dnb) {
		btnDownload = inp_dnb;
	}

	public void registerDeleteButton(DeleteButton inp_db) {
		btnDelete = inp_db;
	}

	public void registerLocalList(LocalList inp_arl) {
		localList = inp_arl;
	}

	public void registerRemoteList(RemoteList inp_drl) {
		remoteList = inp_drl;
	}

	public void UploadItem() {

		int index = localList.getSelectedIndex();
		String selectedItem = localList.getSelectedValue().toString();
		((DefaultListModel) localList.getModel()).remove(index);

		((DefaultListModel) remoteList.getModel()).addElement(selectedItem);

		btnUpload.setEnabled(false);
		btnDelete.setEnabled(false);
		btnDownload.setEnabled(false);
	}

	public void DownloadItem() {
		int index = remoteList.getSelectedIndex();
		String selectedItem = remoteList.getSelectedValue().toString();
		((DefaultListModel) remoteList.getModel()).remove(index);

		((DefaultListModel) localList.getModel()).addElement(selectedItem);

		btnUpload.setEnabled(false);
		btnDelete.setEnabled(false);
		btnDownload.setEnabled(false);
	}

	public void DeleteItem() {
		int index = localList.getSelectedIndex();
		if (index >= 0) {
			((DefaultListModel) localList.getModel()).remove(index);
		}

		index = remoteList.getSelectedIndex();
		if (index >= 0) {
			((DefaultListModel) remoteList.getModel()).remove(index);
		}
		btnUpload.setEnabled(false);
		btnDelete.setEnabled(false);
		btnDownload.setEnabled(false);

	}

	public void LocalListSelect() {
		remoteList.setSelectedIndex(-1);
		btnUpload.setEnabled(true);
		btnDelete.setEnabled(true);
		btnDownload.setEnabled(false);
	}

	public void RemoteListSelect() {
		localList.setSelectedIndex(-1);
		btnUpload.setEnabled(false);
		btnDelete.setEnabled(true);
		btnDownload.setEnabled(true);
	}
}