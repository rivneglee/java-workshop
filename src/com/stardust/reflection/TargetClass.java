package com.stardust.reflection;

import java.io.Serializable;

public class TargetClass implements Serializable {
    private static String aliasPrefix = "alias";

    public int calls;

    public TargetClass() {

    }

    public TargetClass(int initialCalls) {
        calls = initialCalls;
    }

    protected String getAlias(String name) {
        return getAliasPrefix() + ":" + name;
    }

    private static String getAliasPrefix() {
        return aliasPrefix;
    }

    private static class StaticInnerClass {

    }

    private class InnerClass {

    }
}


