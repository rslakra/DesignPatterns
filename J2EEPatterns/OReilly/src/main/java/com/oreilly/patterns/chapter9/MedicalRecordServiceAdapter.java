// Example 9-5: MedicalRecordServiceAdapter.java
// Note: Required Apache SOAP to compile
// http://ws.apache.org/soap/index.html

package com.oreilly.patterns.chapter9;

import org.apache.soap.transport.http.SOAPHTTPConnection;
import org.apache.soap.encoding.soapenc.BeanSerializer;
import org.apache.soap.encoding.SOAPMappingRegistry;
import org.apache.soap.util.xml.QName;
import org.apache.soap.*;
import org.apache.soap.rpc.*;

import java.net.URL;
import java.util.*;

public class MedicalRecordServiceAdapter {

  public MedicalRecordServiceAdapter() {
    m_httpConnection = new SOAPHTTPConnection();
    m_smr = new SOAPMappingRegistry();

    BeanSerializer beanSer = new BeanSerializer();
    m_smr.mapTypes(Constants.NS_URI_SOAP_ENC, 
      new QName("http://chapter9/IMedicalRecordService.xsd",
        "chapter9_PatientRecord"), PatientRecord.class,
        beanSer, beanSer);
  }

  public String endpoint = 
    "http://service.hospital.org/records/MedicalRecordService";
  private SOAPHTTPConnection m_httpConnection = null;
  private SOAPMappingRegistry m_smr = null;

  public PatientRecord getMedicalRecord(Long patientID) 
    throws Exception {
    PatientRecord returnVal = null;

    URL endpointURL = new URL(endpoint);
    Call call = new Call();
    call.setSOAPTransport(m_httpConnection);
    call.setTargetObjectURI("chapter9.MedicalRecordService");
    call.setMethodName("getMedicalRecord");
    call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);

    Vector params = new Vector();
    params.addElement(new Parameter("patientID", 
      java.lang.Long.class, patientID, null));
    call.setParams(params);

    call.setSOAPMappingRegistry(m_smr);

    Response response = call.invoke(endpointURL, "");

    if (!response.generatedFault()) {
      Parameter result = response.getReturnValue();
      returnVal = (PatientRecord)result.getValue();
    }
    else {
      Fault fault = response.getFault();
      throw new SOAPException(fault.getFaultCode(), fault.getFaultString());
    }

    return returnVal;
  }

}
