package com.pearson.codingchallenge.service;

import java.util.List;

import com.pearson.codingchallenge.bean.OrderByEnum;
import com.pearson.codingchallenge.bean.StoreData;
import com.pearson.codingchallenge.exception.ServiceException;

/**
 * This interface has API's to retieve,create stores
 * @author swaonkar
 *
 */
public interface StoreService {
  /**
   * Get store data for given store id	
   * @param id
   * @return
 * @throws ServiceException 
   */
  StoreData getStoreById(final String id) throws ServiceException;
  
  /**
   * get all stores order by given store field
   * @param orderBy
   * @return
 * @throws ServiceException 
   */
  List<StoreData> getStores(OrderByEnum orderBy) throws ServiceException;
  
  /**
   * create store with given store data
   * @param store
   */
  void createStore(StoreData store);
 
}
