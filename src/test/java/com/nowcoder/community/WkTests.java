package com.nowcoder.community;

import java.io.IOException;

public class WkTests {

    public static void main(String[] args) {
        String cmd = "d:/wkhtmltopdf/bin/wkhtmltopdf https://www.nowcoder.com d:/work/data/wk-pdfs/2.pdf";
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println("ok");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
