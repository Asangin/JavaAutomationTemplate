package com.skryl.suites;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectPackages("com.skryl.api")
//@IncludePackages({"com.example.app.moduleA", "com.example.app.moduleB"})
//@IncludeClassNamePatterns("^.*Tests?$")
//@ExcludeTags("qa")
public class SuiteDemo {
}