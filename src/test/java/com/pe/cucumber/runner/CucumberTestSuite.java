package com.pe.cucumber.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {"src/test/resources/feature"}, glue = {"com.pe.cucumber.definition"})
public class CucumberTestSuite {

	
	
}
