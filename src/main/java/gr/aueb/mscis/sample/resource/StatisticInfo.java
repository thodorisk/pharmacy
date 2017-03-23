package gr.aueb.mscis.sample.resource;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class StatisticInfo is a utility class for JAX-RS implementation.
 */

@XmlRootElement
public class StatisticInfo {
	
	private double total;

	
	
	public StatisticInfo() {
	}


	public StatisticInfo(double total) {
		super();
		this.total = total;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
