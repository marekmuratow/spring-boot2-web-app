package pl.entito.spring.boot2.demo.web.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PropertiesController {

	@Value("${book.version}")
	private String versionV1;

	private String versionV2;

	public PropertiesController(BookProperties properties) {
		this.versionV2 = properties.getVersion();
	}

	@GetMapping("/version")
	@ResponseBody
	private Book testVersionProperties() {

		String versionFromValue = "versionV1: " + versionV1;
		String versionFromConstructor = "versionV2: " + versionV2;

		return new Book(versionFromValue, versionFromConstructor);
	}

}
