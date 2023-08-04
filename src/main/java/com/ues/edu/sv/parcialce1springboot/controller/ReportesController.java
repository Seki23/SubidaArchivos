package com.ues.edu.sv.parcialce1springboot.controller;

import com.ues.edu.sv.parcialce1springboot.service.GenerarReportesService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/api/reporte")
@RequiredArgsConstructor
public class ReportesController {

    private final GenerarReportesService service;
    @GetMapping("/pdf")
    public void listConsultaMedicoPorEspecialidadPdf(ModelAndView model, HttpServletResponse response)throws IOException {
        this.service.generarReporte(response);
    }

    @GetMapping("/excel")
    public void listConsultaMedico(ModelAndView model, HttpServletResponse response)throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=VentasTotales.xls";
        response.setHeader(headerKey, headerValue);

        this.service.generateExcel(response);
        response.flushBuffer();
    }


}
