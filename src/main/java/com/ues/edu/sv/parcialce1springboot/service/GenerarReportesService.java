package com.ues.edu.sv.parcialce1springboot.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface GenerarReportesService<T> {
    public void generarReporte(HttpServletResponse response)throws IOException;

    void generateExcel(HttpServletResponse response)throws IOException;

}
