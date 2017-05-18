package com.pearson.codingchallenge.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pearson.codingchallenge.bean.OrderByEnum;
import com.pearson.codingchallenge.bean.StoreData;
import com.pearson.codingchallenge.exception.ServiceException;
import com.pearson.codingchallenge.service.StoreService;

@RestController
@RequestMapping(value="/stores")
public class StoreController {

	@Autowired
	private StoreService storeService;
	private static Logger LOG = Logger.getLogger(StoreController.class);

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public StoreData getStoreById(@PathVariable String id) throws ServiceException {
		LOG.debug("getStoreById API called");
		return storeService.getStoreById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus( HttpStatus.OK )
	public List<StoreData> getStores(@RequestParam(required=false,defaultValue="CITY") String orderBy) throws ServiceException {
		OrderByEnum orderByEnum = OrderByEnum.getOrderByEnumFromCode(orderBy);
		if(orderByEnum==null){
			throw new IllegalArgumentException("Invalid value for orderBy parameter");
		}
		LOG.debug("getStores API called");
		return storeService.getStores(orderByEnum);
	}
	
	@RequestMapping( method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED) //201
	@ResponseBody
	public StoreData createStore(@RequestBody StoreData store) {
		LOG.debug("Create Store API called");
		return store;
	}
}
