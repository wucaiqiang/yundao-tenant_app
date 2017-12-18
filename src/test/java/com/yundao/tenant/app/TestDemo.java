package com.yundao.tenant.app;

import org.junit.Test;

import com.yundao.tenant.app.util.FileSupport;

/**
 * @author jan
 * @create 2017-06-21 AM8:38
 **/
public class TestDemo {

    @Test
    public void test() {
        String ttt=FileSupport.getFileFormat("aaa.pdfa");
        System.out.println(ttt);
    }
}
