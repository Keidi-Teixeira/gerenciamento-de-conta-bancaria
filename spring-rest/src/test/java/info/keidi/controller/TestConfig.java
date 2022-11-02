package info.keidi.controller;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
    basePackages = {
      "info.keidi.rest.controller",
      "info.keidi.rest.config",
      "info.keidi.rest.exception"
    })
class TestConfig {}
