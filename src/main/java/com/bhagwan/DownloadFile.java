package com.bhagwan;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
//import java.io.IOException;

public class DownloadFile extends MVCPortlet {

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response)
            throws IOException, PortletException {
        try {
            File file = new File("/home/beles/Desktop/XXX/download-file-portlet/src/main/resources/casefile.xlsx");

            System.out.println("NAME: "+file.getName());
            InputStream in = new FileInputStream(file);

            HttpServletResponse httpRes = PortalUtil.getHttpServletResponse(response);
            HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);

            ServletResponseUtil.sendFile(httpReq, httpRes, file.getName(), in, "application/download");
            in.close();

            response.setContentType("text/x-vcard");
            response.setProperty("Content-Disposition", "attachment; filename=/casefile.xlsx" );

            response.getPortletOutputStream().write(file.toString().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("#################################");
        System.out.println("OK!");
        super.serveResource(request,response);
    }

}