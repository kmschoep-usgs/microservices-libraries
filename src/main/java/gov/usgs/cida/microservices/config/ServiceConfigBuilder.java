package gov.usgs.cida.microservices.config;

import gov.usgs.cida.microservices.config.ServiceConfig;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author isuftin
 */
public class ServiceConfigBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ServiceConfigBuilder.class);
	private String name = null; // required
	private int port = 0; //required
	private String id = null; // optional
	private long ttl = 0; // optiona;
	private String[] tags = null; //optional
	
	public ServiceConfig build() {
		ServiceConfig result = new ServiceConfig();
		
		if (StringUtils.isBlank(name)) {
			throw new IllegalStateException("Service name is required");
		}
		result.setName(name);
		
		if (port < 1) {
			throw new IllegalStateException("Service port is required to be a positive integer");
		}
		result.setPort(port);
		
		if (StringUtils.isBlank(id)) {
			result.setId(UUID.randomUUID().toString());
		} else {
			result.setId(id);
		}
		
		if (ttl < 0) {
			throw new IllegalStateException("TTL needs to a positive value");
		}
		result.setTtl(ttl);
		
		if (tags == null) {
			tags = new String[0];
		}
		result.setTags(tags);
		
		logger.debug("Created new service configuration: {}", result.toString());
		
		return result;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @param ttl the ttl to set
	 */
	public void setTtl(long ttl) {
		this.ttl = ttl;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}
}
