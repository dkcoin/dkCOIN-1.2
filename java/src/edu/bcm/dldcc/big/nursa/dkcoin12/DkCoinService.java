package edu.bcm.dldcc.big.nursa.dkcoin12;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.SOAPFaultException;

import edu.bcm.dldcc.big.nursa.dkcoin12.DkCoinService;
import edu.bcm.dldcc.big.nursa.dkcoin12.exceptions.DkCoinServiceNotRunning;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.Dkcoin;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.DkcoinPortType;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeCollection;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeCollectionResult;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeDeleteCollection;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeDeleteResource;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeResource;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeResourceResult;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeResourceSearchResult;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeResourceTypesResults;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSearchKeywordParams;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSearchResourceParams;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSearchResultEntity;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSession;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSourcesResults;

public class DkCoinService {
	private static DkCoinService instance = null;
	private Dkcoin service = null;
	private DkcoinPortType port;
	private Holder<String> sessionkey;
	private TypeSession myKey;
	private boolean running;

	private DkCoinService() {
		sessionkey = new Holder<String>();
		myKey = new TypeSession();
		running = false;
	}

	/**
	 * Static method for accessing the dkCOIN Service singleton
	 */
	public static DkCoinService getService() {
		if (instance == null) {
			instance = new DkCoinService();
		}
		return instance;
	}

	/**
	 * Open a connection to the dkCOIN service of your choice.
	 * 
	 * @param name User name for logging into dkCOIN
	 * @param password Password for logging into dkCOIN
	 * @param server Specifies which server you want to log into (i.e. Staging or Production)
	 * @throws MalformedURLException
	 */
	public void start(String name, String password, String server) throws MalformedURLException {
		if (running == false) {
			URL url = null;
			URL baseUrl;
			baseUrl = edu.bcm.dldcc.big.nursa.dkcoin12.soap.Dkcoin.class.getResource(".");
			url = new URL(baseUrl, server);
			service = new Dkcoin(url, new QName("urn:dkcoin", "dkcoin"));

			start(name, password);
		}
	}

	/**
	 * Open a connection to the staging dkCOIN service.
	 * 
	 * @param name User name for logging into dkCOIN
	 * @param password Password for logging into dkCOIN
	 */
	public void start(String name, String password) {
		if (running == false) {
			if (service == null) {
				service = new Dkcoin();
			}
			port = service.getDkcoinPort();

			Holder<String> result = new Holder<String>();
			List<String> ipAddresses = new Vector<String>();
			port.startSession(name, password, ipAddresses, result, sessionkey);
			myKey.setSessionkey(sessionkey.value);
			running = true;
		}
	}
	
	/**
	 * Stops the connection to the dkCOIN service
	 * 
	 * @return The result of ending the session
	 */
	public String stop() {
		if (running == true) {
			running = false;
			return port.endSession(sessionkey.value);
		} else {
			return "Not Currently Running";
		}
	}
	
	/**
	 * Performs a search given a set of parameters and returns the results
	 * 
	 * @param params A list of parameters
	 * @return Results of the list
	 * @throws DkCoinServiceNotRunning If the the service is not running
	 */
	public List<TypeSearchResultEntity> searchKeyword(Vector<TypeSearchKeywordParams> params) throws DkCoinServiceNotRunning {
		if (!running) {
			throw new DkCoinServiceNotRunning();
		}

		return port.searchKeyword(params);
	}
	
	/***
	 * 
	 */
	public List<TypeResourceSearchResult> searchResource(Vector<TypeSearchResourceParams> params) throws DkCoinServiceNotRunning {
		if (!running) {
			throw new DkCoinServiceNotRunning();
		}

		return port.searchResource(params);
	}
	
	/**
	 * Returns a list of sources that are currently supported by dkCOIN
	 * 
	 * @return A list of sources supported by dkCOIN
	 * @throws DkCoinServiceNotRunning If the the service is not running
	 */
	public TypeSourcesResults getSources() throws DkCoinServiceNotRunning {
		if (!running) {
			throw new DkCoinServiceNotRunning();
		}

		return port.getSources("");
	}
	
	/**
	 * Returns a list of resource types that are currently supported by dkCOIN
	 * 
	 * @return A list of resources supported by dkCOIN
	 * @throws DkCoinServiceNotRunning If the the service is not running
	 */
	public TypeResourceTypesResults getResourcesTypes() throws DkCoinServiceNotRunning {
		if (!running) {
			throw new DkCoinServiceNotRunning();
		}

		return port.getResourceTypes("");
	}

	/**
	 * Add or update a collection in dkCOIN
	 * 
	 * @param collections A list of new or current collections that need to be updated or added
	 * @return The results of the attempted update or insertion of a new Collection
	 * @throws DkCoinServiceNotRunning If the the service is not running
	 */
	public List<TypeCollectionResult> updateCollection(Vector<TypeCollection> collections) throws DkCoinServiceNotRunning {
		if (!running) {
			throw new DkCoinServiceNotRunning();
		}
		List<TypeCollectionResult> results = null;
		int attempt = 0;
		while (results == null) {
			attempt++;
			System.out.println("Update Collection Attempt: " + attempt);

			try {
				results = port.updateCollection(myKey, collections);
				Thread.sleep(1000);
			} catch (SOAPFaultException e) {
				if (!e.getLocalizedMessage().equals("Failed Session Validation")) {
					throw e;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	/**
	 * Delete a current collection(s). 
	 * 
	 * @param collections A list of collections to delete
	 * @return The results of the attempted delete of collection or list of collections
	 * @throws DkCoinServiceNotRunning If the the service is not running
	 */
	public List<TypeCollectionResult> deleteCollection(Vector<TypeDeleteCollection> collections) throws DkCoinServiceNotRunning {
		if (!running) {
			throw new DkCoinServiceNotRunning();
		}
		List<TypeCollectionResult> results = null;
		int attempt = 0;
		while (results == null) {
			attempt++;
			System.out.println("Delete Collection Attempt: " + attempt);

			try {
				results = port.deleteCollection(myKey, collections);
				Thread.sleep(1000);
			} catch (SOAPFaultException e) {
				if (!e.getLocalizedMessage().equals("Failed Session Validation")) {
					throw e;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	/**
	 * Add or update a resource in dkCOIN
	 * 
	 * @param resources A list of resources that need to be inserted or updated
	 * @return The results of the attempted update or insertion of a resource
	 * @throws DkCoinServiceNotRunning If the the service is not running
	 */
	public List<TypeResourceResult> updateResource(Vector<TypeResource> resources) throws DkCoinServiceNotRunning {
		if (!running) {
			throw new DkCoinServiceNotRunning();
		}
		List<TypeResourceResult> results = null;
		int attempt = 0;

		while (results == null) {
			attempt++;
			System.out.println("Update Resource Attempt: " + attempt);

			try {
				results = port.updateResource(myKey, resources);
				Thread.sleep(1000);
			} catch (SOAPFaultException e) {
				if (!e.getLocalizedMessage().equals("Failed Session Validation")) {
					throw e;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	/**
	 * Add new data (gene, or ontology) to an existing resource
	 * @param reources
	 * @return The results of the attempted append of a resource
	 * @throws DkCoinServiceNotRunning If the the service is not running
	 */
	public List<TypeResourceResult> appendResource(Vector<TypeResource> reources) throws DkCoinServiceNotRunning {
		if (!running) {
			throw new DkCoinServiceNotRunning();
		}
		List<TypeResourceResult> results = null;
		int attempt = 0;

		while (results == null) {
			attempt++;
			System.out.println("Append Resource Attempt: " + attempt);

			try {
				results = port.appendResource(myKey, reources);
				Thread.sleep(1000);
			} catch (SOAPFaultException e) {
				if (!e.getLocalizedMessage().equals("Failed Session Validation")) {
					throw e;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	/**
	 * Deletes a resource or set of resources from dkCOIN
	 * 
	 * @param reources A list of resources that are to be deleted from dkCOIN
	 * @return The results of the attempted delete of a resource
	 * @throws DkCoinServiceNotRunning If the the service is not running
	 */
	public List<TypeResourceResult> deleteResouce(Vector<TypeDeleteResource> reources) throws DkCoinServiceNotRunning {
		if (!running) {
			throw new DkCoinServiceNotRunning();
		}
		List<TypeResourceResult> results = null;
		int attempt = 0;

		while (results == null) {
			attempt++;
			System.out.println("Delete Resource Attempt: " + attempt);

			try {
				results = port.deleteResource(myKey, reources);
				Thread.sleep(1000);
			} catch (SOAPFaultException e) {
				if (!e.getLocalizedMessage().equals("Failed Session Validation")) {
					throw e;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
}
