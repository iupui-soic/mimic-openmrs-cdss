/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.cdss.extension.html;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.PatientDashboardTabExt;

/**
 * This class defines the tab that will appear on the patient dashboard with the
 * "cdss.title" tab name.
 */
public class CdssTab extends PatientDashboardTabExt {
	
	/**
	 * @see PatientDashboardTabExt#getMediaType()
	 */
	@Override
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}

	@Override
	public String getTabName() {
		return "cdss.title";
	}

	@Override
	public String getTabId() {
		return "cdss";
	}

	@Override
	public String getRequiredPrivilege() {
		return "Run Cdss";
	}

	@Override
	public String getPortletUrl() {
		return "cdssTab";
	}
	
}
