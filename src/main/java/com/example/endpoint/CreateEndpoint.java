package com.example.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.cash.CreateRequest;
import com.example.cash.CreateResponse;
import com.example.cash.CreateResponse.Return;
import com.example.cash.CreateResponse.Return.OperationActionResultEntity;
import com.example.cash.CreateResponse.Return.ResponseInfo;
import com.example.cash.ObjectFactory;

import lombok.extern.slf4j.Slf4j;

@Endpoint
@Slf4j
public class CreateEndpoint {

	private static final String NAMESPACE_URI="http://www.vtb24.ru/ApplicationObjectLibrary/MBANK/Requesters/CashOperationMBANKReqA/V1/CreateCashOperation_Request";
	
	@Autowired
	ObjectFactory factory;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createRequest")
	@ResponsePayload
	public CreateResponse createIbsoDocument(@RequestPayload CreateRequest request) {

		log.debug("createIbsoDocument rewuest accepted ");
		log.debug("ClientExtId: "+request.getRequest().getOperationEntity().getIdSys());
		log.debug("ClientExtId: "+request.getRequest().getOperationEntity().getClient().getClientExtId());

		CreateResponse response = new CreateResponse();
		
		Return createCreateResponseReturn = factory.createCreateResponseReturn();
		OperationActionResultEntity createCreateResponseReturnOperationActionResultEntity = factory.createCreateResponseReturnOperationActionResultEntity();
		ResponseInfo createCreateResponseReturnResponseInfo = factory.createCreateResponseReturnResponseInfo();
		createCreateResponseReturnOperationActionResultEntity.setOperJournalId("12345");
		createCreateResponseReturnOperationActionResultEntity.setErrorCode("100");
		createCreateResponseReturnOperationActionResultEntity.setErrorMessage("No Service provided");
		createCreateResponseReturnResponseInfo.setCorrelationId("000001");
		createCreateResponseReturn.setOperationActionResultEntity(createCreateResponseReturnOperationActionResultEntity);
		createCreateResponseReturn.setResponseInfo(createCreateResponseReturnResponseInfo);
		
		response.setReturn(createCreateResponseReturn);
				
		return response;
	}
	
}
