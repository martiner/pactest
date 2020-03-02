package cz.geek.pactest;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PacController {

	@RequestMapping(path = "/", produces = "application/x-ns-proxy-autoconfig")
	@ResponseBody
	public String pac(@RequestParam(required = false) Integer waits, @RequestHeader MultiValueMap<String, String> headers) throws InterruptedException {

		log.info("Received request with waits={} headers={}", waits, headers);

		if (waits != null) {
			SECONDS.sleep(waits);
		}

		return "function FindProxyForURL(url, host)\n"
				+ "{\n"
				+ "return \"DIRECT\";\n"
				+ "}";
	}
}
