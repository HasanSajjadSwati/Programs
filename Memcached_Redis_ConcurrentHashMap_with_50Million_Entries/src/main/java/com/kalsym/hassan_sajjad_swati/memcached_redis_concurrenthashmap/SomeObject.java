
package com.kalsym.hassan_sajjad_swati.memcached_redis_concurrenthashmap;

import java.io.Serializable;

public class SomeObject implements Serializable {
    private String string;
    private boolean flag;
    private int integer;

    public SomeObject(String string, boolean flag, int integer)  {
        this.string = string;
        this.flag = flag;
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    @Override
    public String toString() {
        return "someObject{" + "string=" + string + ", flag=" + flag + ", integer=" + integer + '}';
    }
}
