package pl.entito.spring.boot2.demo.web.model;

import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("book")
@Validated
public class BookProperties {

	/**
	 * Book version "v.1.0"
	 */
	@Pattern(regexp = "v\\w\\.\\w")
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "BookProperties [version=" + version + "]";
	}

}
