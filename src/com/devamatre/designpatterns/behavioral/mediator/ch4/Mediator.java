/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2018.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
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