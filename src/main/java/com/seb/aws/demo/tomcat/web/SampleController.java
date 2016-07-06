/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seb.aws.demo.tomcat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seb.aws.demo.tomcat.service.HelloWorldService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SampleController {

	private static final Logger LOG = LoggerFactory.getLogger(SampleController.class);

	@Autowired
	private HelloWorldService helloWorldService;

	@RequestMapping("/")
	@ResponseBody
	public String helloWorld() {
		return this.helloWorldService.getHelloMessage();
	}

	@RequestMapping(value = "/flood/{flood}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> flood(@PathVariable(value = "flood") int flood) {
		LOG.info("Flood {}", flood);
		Map<Integer, Integer[]> squares = new HashMap<Integer, Integer[]>();
		for (int i = 0; i < flood; ++i) {
			squares.put(i, new Integer[] {i^2, i^3});
		}
		return new ResponseEntity<String>("Ca Flood x2/x3..." + squares.size(), HttpStatus.OK);
	}

}
