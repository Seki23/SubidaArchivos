package com.ues.edu.sv.parcialce1springboot.serviceImpl;

import com.ues.edu.sv.parcialce1springboot.service.IReporteService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReportesServicePDFImpl<T> implements IReporteService<T> {


    @Override
    public void generarReporte(InputStream stream, HttpServletResponse response, List<T> data) throws IOException {
        // TODO Auto-generated method stub
        try {
            final JasperReport report = JasperCompileManager.compileReport(stream);
            final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(data);
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Admin");
            final File imgLogo= ResourceUtils.getFile("classpath:images/logo.png");
            parameters.put("imgLogo",new FileInputStream(imgLogo));
            final JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, source);
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=App_report_en.pdf");
            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}