package com.ibm.app.cucumber.stepdefs;

import com.ibm.app.ArgosApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = ArgosApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
