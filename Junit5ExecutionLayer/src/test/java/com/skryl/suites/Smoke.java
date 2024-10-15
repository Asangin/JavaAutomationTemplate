package com.skryl.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectPackages({
        "com.skryl.api",
        "com.skryl.ui",
        "com.skryl.unit"
})
@IncludeTags("smoke")
public class Smoke {
}